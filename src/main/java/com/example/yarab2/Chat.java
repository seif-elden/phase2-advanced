package com.example.yarab2;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {
    private ArrayList<Message> messages;
    private profile participantOne;
    private profile participantTwo;

    public Chat(profile participantOne, profile participantTwo) {
        this.participantOne = participantOne;
        this.participantTwo = participantTwo;
        this.messages = new ArrayList<>();
    }
    public void sendMessage(profile sender,String content) {
        Message msg = new Message(sender, content);
        messages.add(msg);
        db.update(HelloApplication.getNetworking().getprofiles());
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }

    public profile getParticipantTwo() {
        return participantOne;
    }

    public profile getParticipantOne() {
        return participantTwo;
    }
}
