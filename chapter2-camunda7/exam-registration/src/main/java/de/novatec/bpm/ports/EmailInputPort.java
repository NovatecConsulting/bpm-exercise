package de.novatec.bpm.ports;

public interface EmailInputPort {
    void sendMailLateRegistration(String email);
    void sendMailConfirmation(String email, String course);
    void sendMailRejection(String email, String course, String reasonExaminationOffice);
}
