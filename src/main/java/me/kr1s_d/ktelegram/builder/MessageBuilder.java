package me.kr1s_d.ktelegram.builder;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {
    private final List<String> lines;
    private String footer = "";

    private MessageBuilder() {
        this.lines = new ArrayList<>();
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public MessageBuilder appendLine(String str) {
        lines.add(str);
        return this;
    }

    public MessageBuilder setHeader(String str) {
        lines.add(0, str);
        return this;
    }

    public MessageBuilder setFooter(String str) {
        this.footer = str;
        return this;
    }

    public MessageBuilder defaultFooter() {
        this.footer = "⚙ | V1.0";
        return this;
    }

    public String getText() {
        lines.add(footer);
        return String.join("\n", lines);
    }
}
