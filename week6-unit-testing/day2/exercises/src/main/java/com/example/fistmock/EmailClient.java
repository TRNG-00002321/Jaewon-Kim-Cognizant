package com.example.fistmock;

public interface EmailClient {
    void send(String to, String subject, String body);
}
