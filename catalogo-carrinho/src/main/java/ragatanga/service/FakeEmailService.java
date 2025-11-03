package ragatanga.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FakeEmailService implements EmailService {
    private final List<String> sent = new ArrayList<>();


    @Override
    public void send(String to, String subject, String body) {
        sent.add(to + "|" + subject + "|" + body);
    }


    public List<String> getSent() { return Collections.unmodifiableList(sent); }
}