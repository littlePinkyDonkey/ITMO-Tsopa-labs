package andrei.teplyh.service;

public interface MailService {
    void send(String address, String subject, String message);
}
