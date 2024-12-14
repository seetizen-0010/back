package com.seetizen.backend.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {

    private final List<String> messages = new ArrayList<>();

    public void saveMessage(String message) {
        messages.add(message);
    }

    public List<String> getAllMessages() {
        return new ArrayList<>(messages);
    }
}
