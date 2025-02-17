package me.kr1s_d.ktelegram.handler;

import com.pengrad.telegrambot.model.Update;
import me.kr1s_d.ktelegram.ITelegramBot;
import me.kr1s_d.ktelegram.TelegramImpl;

public interface UpdateHandler {
    boolean isAccepted(Update update);

    void onUpdateReceived(ITelegramBot bot, Update update);
}
