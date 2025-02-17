package me.kr1s_d.ktelegram.handler.error;

import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.TelegramException;

public class ErrorHandler implements ExceptionHandler {
    @Override
    public void onException(TelegramException e) {
        e.printStackTrace();
    }
}
