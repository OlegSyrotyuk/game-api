package net.stickmix.game.api.event;

/**
 * События могут быть обработаны (а их методы-обработчики могут быть помечены GameEventHandler'ом) только
 * внутри классов, реализующих данный интерфейс. Не забудьте, что для работы необходимо не только имплементировать
 * этот интерфейс, но также зарегистрировать его в GameEventManager'е.
 * Created by RINES on 03.06.2018.
 */
public interface IGameListener {}