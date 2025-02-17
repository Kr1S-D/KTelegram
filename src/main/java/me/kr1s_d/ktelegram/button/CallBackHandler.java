package me.kr1s_d.ktelegram.button;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import me.kr1s_d.ktelegram.ITelegramBot;
import me.kr1s_d.ktelegram.button.ButtonHandler;
import me.kr1s_d.ktelegram.handler.UpdateHandler;

import java.util.ArrayList;
import java.util.List;

public class CallBackHandler implements UpdateHandler {
    private static final List<ButtonHandler> buttons = new ArrayList<>();

    public static void registerButton(ButtonHandler clickHandler) {
        buttons.add(clickHandler);
    }

    @Override
    public boolean isAccepted(Update update) {
        return update.callbackQuery() != null;
    }

    @Override
    public void onUpdateReceived(ITelegramBot bot, Update update) {
        CallbackQuery query = update.callbackQuery();
        String callData = query.data();
        long chatId = update.callbackQuery().message().chat().id();

        buttons.removeIf(b -> {
            if(b.isAccepted(callData)) {
                b.getClickHandler().onClick(bot, update, chatId);
                return true;
            }

            return false;
        });
    }
}
