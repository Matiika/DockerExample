package com.notificationApp.notificationKafka.dto;

public class Msg {

    private String message;

    public Msg() {
    }

    public Msg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
