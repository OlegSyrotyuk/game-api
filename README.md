# game-api

Обширная api нашего проекта для создания режимов.

## Команды

Для создания команд в нашей апи используются аннотации и любовь Дмитрия Смирнова.
Создаем новый класс (название класса формата CommandИмя команды с большой буквы. Если в названии больше одного слова, то
начинаем новое слово также с большой).
Вешаем на класс аннотацию `@Command`. В параметрах указываем имя команды и минимальную группу пользователя. По стандарту
group = PermissionGroup.USER.
На этом уже можно закончить, но лучше указать описание и использование команды.
Для описания мы используем аннотацию `@Description`.
В значение аннотации пишем описание команды с большой буквы. Точку в конце не ставим.
Устанавливаем использование через аннотацию `@Usage`.
В значение пишем название команды со слешем, и в скобочках пишем аргументы если такие есть.
Для добавления команде алиасов используем аннотацию `@Aliases`.
Аннотацией `@OnlyFor` можно указать кому можно использовать команду, пользователю или консоли.
Если аннотация отсутствует то, команду можно использовать как из консоли, так и от игрока.
Пример:

```
    @Command("example")
    @Description("Команда для примера")
    @Usage("/example <пользователь>")
    @Aliases({"ex", "пример"})
    @OnlyFor(Sender.PLAYER)
    public class CommandHelp
```

С аннотациями для класса мы закончили.

#### Создаем обработчик команды.

Делаем публичный метод и называем его `handle`, и вешаем на него аннотацию `@CommandHandler`.
На самом деле не имеет значения как назвать метод, но по стандарту лучше назвать именно так.
В параметры метода мы добавляем обязательно первым CommandSender, а вторым String[].
Пример:

```
    @CommandHandler
    public void handle(CommandSender sender, String[] args) {
        
    }
```

В этом методе просто пишем обработку команды.

Теперь немного поинтереснее.

#### Команды с парсингом аргументов.

Создаем метод точно также как прошлый раз, но теперь вместо String[] args, мы указываем объект который хотим получить и
помечаем его аннотацией `@Argument`.
Пример:

```
    @CommandHandler
    public void handle(CommandSender sender, @Argument User target) {

    }
```

В данном примере если мы в игре используем команду /example без аргументов,
то нам выведет сообщение `Недостаточно аргументов!` и обработка завершится.
В качестве аргумента в данном случае используется ник игрока, объект пользователя которого вы хотите получить.
Под капотом это очень просто, обработчик команд просто исполняет
метод `GameApi.getUserManager().get(переданный аргумент);`.

В качестве аргументов можно использовать не все объекты, а лишь те к которым есть парсер.
Объекты которые можно использовать без написания парсеров:
`Integer, Long, Float, Double, Boolean, String, String[], User, Player, Realm, PermissionGroup, GameMode.`

#### Подкоманды.

В нашей api предусмотренно использование подкоманд.
В этом случае мы не делаем метод с аннотацией `@CommandHandler`.
Создаем публичный метод и называем его handleИмя подкоманды с большой буквы и вешаем на него аннотацию `@Subcommand`.
Из себя она представляет то же самое что и `@Command`. Мы указываем название и минимальную группу.
Задаем подкоманде описание, использование и алиасы теми же аннотациями, что и над классом.

В параметры к методу делаем также само как в примере с простой командой.
Пример подкоманды:

```
    @Subcommand("accept")
    @Description("Принять приглашение в тусовку")
    @Usage("/party accept <пользователь>")
    public void handleAccept(CommandSender sender, @Argument User target) {
        User user = GameApi.getUserManager().get(sender.getName());
        if (user.getParty() != null && user.getParty().isExists()) {
            sender.sendMessage(ChatUtil.prefixed(Prefix.PARTY, "&cВы уже состоите в тусовке."));
            return;
        }
        PartyImpl party = (PartyImpl) target.getParty();
        if (party != null && party.isExists() && party.hasInvite(sender.getName(), target.getName())) {
            party.accept(sender.getName());
        } else {
            sender.sendMessage(ChatUtil.prefixed(Prefix.PARTY, "&cУ вас нет приглашений в тусовку."));
        }
    }
```

Таких подкоманд в классе можно делать сколько угодно.
При использовании команды без каких либо аргументов (подкоманд), в чат выведется сообщение со всеми доступными
подкомандами и их описанием.

#### Регистрация команды.

Теперь когда мы сделали свою команду, нам нужно её зарегистрировать.
Для этого мы используем `GameApi.getCommandManager().registerCommand(CommandExample.class);`.
Поздравляю! Наша команда создана и зарегистрирована.
Теперь она появится в команде /help.

#### Парсеры аргументов.

Для того чтобы сделать свой парсер объекта, необходимо создать новый класс с именем формата Имя объектаParser и
наследоваться от интерфейса `ArgumentParser`.
В методе `parse` прописываем получение объекта.
Пример:

```
public class UserParser implements ArgumentParser<User> {

    @Override
    public User parse(String object, CommandSender sender) {
        User user = GameApi.getUserManager().get(object);
        if (user == null) {
            sender.sendMessage(ChatUtil.prefixed(Prefix.COMMANDS, "&cПользователь &b%s&c ранее не играл на проекте.", object));
        }
        return user;
    }
}
```

Далее регистрируем парсер `GameApi.getCommandManager().registerArgumentParser(UserParser.class);`.
Поздравляю! Наш парсер готов к использованию.
ВАЖНО! Парсеры должны быть зарегистрированы раньше чем команды.
