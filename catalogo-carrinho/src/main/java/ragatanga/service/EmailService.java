package ragatanga.service;


public interface EmailService {
    void send(String to, String subject, String body);
}