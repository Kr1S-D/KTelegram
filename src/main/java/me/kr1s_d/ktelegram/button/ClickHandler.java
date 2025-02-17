package me.kr1s_d.ktelegram.button;

import com.pengrad.telegrambot.model.Update;
import me.kr1s_d.ktelegram.ITelegramBot;
import me.kr1s_d.ktelegram.TelegramImpl;

public interface ClickHandler {

    String getCallBackData();

    void onClick(ITelegramBot bot, Update update, long chatID);
}
