package me.kr1s_d.ktelegram.button;

public class ButtonHandler {
    private final ClickHandler clickHandler;

    public ButtonHandler(ClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public static ButtonHandler newWith(ClickHandler handler) {
        return new ButtonHandler(handler);
    }

    public boolean isAccepted(String callData) {
        return clickHandler.getCallBackData().equalsIgnoreCase(callData);
    }

    public ClickHandler getClickHandler() {
        return clickHandler;
    }
}
