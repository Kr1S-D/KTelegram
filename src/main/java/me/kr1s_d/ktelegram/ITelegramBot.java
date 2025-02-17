package me.kr1s_d.ktelegram;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import me.kr1s_d.ktelegram.handler.HandlerPipeline;

public interface ITelegramBot {
    void start();

    void stop();

    BaseResponse sendMessage(String message, long chatID);

    BaseResponse sendMessage(long chatID, String message);

    void editMessage(String message, long chatID, int messageID);

    void deleteMessage(long chatID, int messageID);

    void playAction(long chatID, ChatAction action);

    <T extends BaseRequest<T, R>, R extends BaseResponse> BaseResponse execute(BaseRequest<T, R> method);

    <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(T request, Callback<T, R> callback);

    HandlerPipeline getPipeline();
}
