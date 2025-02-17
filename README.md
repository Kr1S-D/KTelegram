**Repository**

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
**Dependency**

```xml
<dependency>
    <groupId>com.github.Kr1S-D</groupId>
    <artifactId>KTelegram</artifactId>
    <version>1.0.0</version>
</dependency>
```

Esempio di chat Handler:
```java
public class MessageHandler implements UpdateHandler {
private final List<String> authenticated = new ArrayList<>();

    @Override
    public boolean isAccepted(Update update) {
        return update.message() != null && update.message().text() != null;
    }

    @Override
    public void onUpdateReceived(ITelegramBot bot, Update update) {
        String text = update.message().text();
        String[] args = update.message().text().split("\\s+");
        User user = update.message().from();
        long chatID = update.message().chat().id();
        String username = user.username();

        if (text.equals("/chatid")) {
            bot.sendMessage(chatID, "✅ Il chatID di questa chat è " + chatID);
            return;
        }

        if (text.equals("/autenticate") && username.equals("Kr1S_D")) {
            bot.sendMessage(chatID, "✅ ");
            authenticated.add(username);
            return;
        }

        if (!authenticated.contains(username))
            System.out.println("[" + username + "]" + " [not autenticated] " + Arrays.toString(args));
        if (!authenticated.contains(username)) {
            bot.sendMessage("❌ No access to this bot, do /autenticate password!", chatID);
            return;
        }

        System.out.println("[" + username + "]" + Arrays.toString(args));
        try {
            text = args[0];
        }catch (Exception e) {
            return;
        }

        try {
            if (text.equals("/stats")) {
                String message = MessageBuilder.builder()
                        .setHeader(Emoticon.STATISTICS + " | *Statistiche*:")
                        .appendLine("")
                        .appendLine(Emoticon.FLOPPY_DISK + " Used RAM: " )
                        .appendLine(Emoticon.FLOPPY_DISK + " Total RAM: " )
                        .appendLine("")
                        .appendLine(Emoticon.USER + " Autenticati: " + authenticated.toString())
                        .defaultFooter()
                        .getText();
                bot.sendMessage(chatID, message);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String grabStrings(int from, String[] s) {
        StringBuilder sb = new StringBuilder();

        int current = 0;
        int checks = Arrays.asList(s).subList(from, s.length).size();
        for(String ss : Arrays.asList(s).subList(from, s.length)){
            current++;
            sb.append(ss);
            if(current != checks) sb.append(" ");
        }

        return sb.toString();
    }
}
```
Esempio di ButtonHandler:
