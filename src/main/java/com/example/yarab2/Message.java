package com.example.yarab2;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private profile sender;
    private String message;
    private LocalDateTime timestamp;

    public Message(profile sender, String message) {
        this.sender = sender;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public profile getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return   "Message from " + sender.getUsername() + ": " + message + " [" + timestamp + "]";
    }
}