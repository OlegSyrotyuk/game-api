package net.stickmix.game.api.event;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
public class GameEventManager {

    private final static Map<Class<GameEvent>, Set<Handler>> HANDLERS = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static void register(IGameListener listener) {
        Class<? extends IGameListener> clazz = listener.getClass();
        Class<GameEvent> eventClass = GameEvent.class;
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getParameterCount() != 1 || !method.isAnnotationPresent(GameEventHandler.class))
                continue;
            Class<?> parameterClass = method.getParameterTypes()[0];
            if (!eventClass.isAssignableFrom(parameterClass))
                continue;
            Set<Handler> handlers = HANDLERS.computeIfAbsent((Class<GameEvent>) parameterClass, cls -> Collections.synchronizedSet(new TreeSet<>()));
            GameEventHandler annotation = method.getAnnotation(GameEventHandler.class);
            handlers.add(new Handler(annotation.priority(), annotation.ignoreCancelled(), constructConsumer(listener, method)));
        }
    }

    public static void call(GameEvent event) {
        Set<Handler> handlers = HANDLERS.get(event.getClass());
        if (handlers == null)
            return;
        GameCancellableEvent cancellableEvent = event instanceof GameCancellableEvent ? (GameCancellableEvent) event : null;
        for (Handler handler : handlers) {
            if (handler.ignoreCancelled && cancellableEvent != null && cancellableEvent.isCancelled())
                continue;
            try {
                handler.consumer.accept(event);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static Consumer<GameEvent> constructConsumer(IGameListener listener, Method method) {
        try {
            MethodHandles.Lookup lookup = constructLookup(listener.getClass());
            return (Consumer<GameEvent>) LambdaMetafactory.metafactory(
                    lookup,
                    "accept",
                    MethodType.methodType(Consumer.class, listener.getClass()),
                    MethodType.methodType(void.class, Object.class),
                    lookup.unreflect(method),
                    MethodType.methodType(void.class, method.getParameterTypes()[0])
            ).getTarget().invoke(listener);
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    private static MethodHandles.Lookup constructLookup(Class<?> owner) throws Exception {
        Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class);
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(owner);
        } finally {
            constructor.setAccessible(false);
        }
    }

    private static class Handler implements Comparable<Handler> {

        private final static AtomicInteger ID = new AtomicInteger();

        private final int id;
        private final byte priority;
        private final boolean ignoreCancelled;
        private final Consumer<GameEvent> consumer;

        Handler(byte priority, boolean ignoreCancelled, Consumer<GameEvent> consumer) {
            this.id = ID.incrementAndGet();
            this.priority = priority;
            this.ignoreCancelled = ignoreCancelled;
            this.consumer = consumer;
        }

        @Override
        public int compareTo(Handler handler) {
            int delta = this.priority - handler.priority;
            return delta == 0 ? this.id - handler.id : delta;
        }

    }

}
