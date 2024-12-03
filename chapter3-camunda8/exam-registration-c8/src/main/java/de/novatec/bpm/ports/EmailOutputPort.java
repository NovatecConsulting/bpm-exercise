package de.novatec.bpm.ports;

public interface EmailOutputPort {
    void sendMail(String email, String subject, String text);

}
