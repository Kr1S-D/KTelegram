package me.kr1s_d.ktelegram.handler;

import com.pengrad.telegrambot.model.Update;
import me.kr1s_d.ktelegram.ITelegramBot;
import me.kr1s_d.ktelegram.TelegramImpl;

import java.util.ArrayList;
import java.util.List;

public class HandlerPipeline {
    private final List<UpdateHandler> handlerList;

    public HandlerPipeline() {
        this.handlerList = new ArrayList<>();
    }

    public void fireUpdate(ITelegramBot bot, Update update) {
        handlerList.forEach(a -> {
            if(!a.isAccepted(update)) return;
            a.onUpdateReceived(bot, update);
        });
    }

    public HandlerPipeline appendHandler(UpdateHandler handler) {
        handlerList.add(handler);
        return this;
    }
}
