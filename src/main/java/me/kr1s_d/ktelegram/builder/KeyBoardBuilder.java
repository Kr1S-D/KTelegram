package me.kr1s_d.ktelegram.builder;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import me.kr1s_d.ktelegram.ITelegramBot;
import me.kr1s_d.ktelegram.button.ButtonHandler;
import me.kr1s_d.ktelegram.button.CallBackHandler;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardBuilder {
    private final SendMessage message;

    private KeyBoardBuilder(long chatID, String text) {
        this.message = new SendMessage(chatID, text)
                .parseMode(ParseMode.Markdown)
                .disableWebPagePreview(true)
                .disableNotification(true);
    }

    public static KeyBoardBuilder builder(long chatID, String text) {
        return new KeyBoardBuilder(chatID, text);
    }

    public KeyBoardBuilder setKeyBoardMarkup(InlineKeyboardMarkup keyboardMarkup) {
        message.replyMarkup(keyboardMarkup);
        return this;
    }

    public SendMessage build() {
        return message;
    }

    public void send(ITelegramBot bot) {
        bot.execute(message);
    }

    public static class ReplyKeyBoardBuilder {
        InlineKeyboardMarkup inlineKeyboardMarkup;
        List<InlineKeyboardButton> currentList = new ArrayList<>();

        public ReplyKeyBoardBuilder() {
            this.inlineKeyboardMarkup = new InlineKeyboardMarkup();
        }

        public static ReplyKeyBoardBuilder builder() {
            return new ReplyKeyBoardBuilder();
        }

        public ReplyKeyBoardBuilder addButton(String text, ButtonHandler clickHandler) {
            InlineKeyboardButton button = new InlineKeyboardButton(text);
            currentList.add(button);
            button.callbackData(clickHandler.getClickHandler().getCallBackData());
            CallBackHandler.registerButton(clickHandler);
            return this;
        }

        public ReplyKeyBoardBuilder addNewLine(String text, ButtonHandler clickHandler) {
            inlineKeyboardMarkup.addRow(currentList.toArray(new InlineKeyboardButton[0]));
            InlineKeyboardButton button = new InlineKeyboardButton(text);

            currentList = new ArrayList<>();
            currentList.add(button);

            button.callbackData(clickHandler.getClickHandler().getCallBackData());
            CallBackHandler.registerButton(clickHandler);
            return this;
        }

        public InlineKeyboardMarkup build() {
            inlineKeyboardMarkup.addRow(currentList.toArray(new InlineKeyboardButton[0]));
            return inlineKeyboardMarkup;
        }
    }
}
