package me.kr1s_d.ktelegram;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import me.kr1s_d.ktelegram.handler.HandlerPipeline;
import me.kr1s_d.ktelegram.handler.error.ErrorHandler;
import me.kr1s_d.ktelegram.button.CallBackHandler;

public class TelegramImpl implements ITelegramBot {
    public static boolean DEBUG = false;

    private final TelegramBot bot;
    private final HandlerPipeline pipeline;

    public TelegramImpl(String botToken) {
        bot = new TelegramBot(botToken);

        this.pipeline = new HandlerPipeline();
        pipeline.appendHandler(new CallBackHandler());
    }

    @Override
    public void start() {
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                pipeline.fireUpdate(this, update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, new ErrorHandler(), new GetUpdates());
    }
    @Override
    public void stop() {
        bot.removeGetUpdatesListener();
        bot.shutdown();
    }
    @Override
    public BaseResponse sendMessage(String message, long chatID) {
        SendMessage msg = new SendMessage(chatID, message);
        return execute(msg);
    }

    @Override
    public BaseResponse sendMessage(long chatID, String message) {
        return sendMessage(message, chatID);
    }

    @Override
    public void editMessage(String message, long chatID, int messageID) {
        EditMessageText editMessageText = new EditMessageText(chatID, messageID, message)
                .parseMode(ParseMode.Markdown)
                .disableWebPagePreview(true);

        bot.execute(editMessageText);
    }

    @Override
    public void deleteMessage(long chatID, int messageID) {
        DeleteMessage del = new DeleteMessage(chatID, messageID);
        bot.execute(del);
    }

    @Override
    public void playAction(long chatID, ChatAction action) {
        SendChatAction chatAction = new SendChatAction(chatID, action);
        bot.execute(chatAction);
    }
    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> BaseResponse execute(BaseRequest<T, R> method) {
        return bot.execute(method);
    }
    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(T request, Callback<T, R> callback) {
        bot.execute(request, callback);
    }
    @Override
    public HandlerPipeline getPipeline() {
        return pipeline;
    }
}