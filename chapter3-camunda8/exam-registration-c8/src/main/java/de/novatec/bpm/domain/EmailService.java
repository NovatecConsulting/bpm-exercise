package de.novatec.bpm.domain;

import de.novatec.bpm.ports.EmailInputPort;
import de.novatec.bpm.ports.EmailOutputPort;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailInputPort {
    private final EmailOutputPort emailOutputPort;

    public EmailService(EmailOutputPort emailOutputPort) {
        this.emailOutputPort = emailOutputPort;
    }

    @Override
    public void sendMailLateRegistration(String email){
        this.emailOutputPort.sendMail(
                email,
                EmailConstants.EMAIL_LATEREGISTRATION_SUBJECT,
                EmailConstants.EMAIL_LATEREGISTRATION_TEXT
        );
    }
    @Override
    public void sendMailConfirmation(String email, String course){
        this.emailOutputPort.sendMail(
                email,
                EmailConstants.EMAIL_CONFIRMATION_SUBJECT + course,
                EmailConstants.EMAIL_CONFIRMATION_TEXT + course
        );
    }

    @Override
    public void sendMailRejection(String email, String course, String reasonExaminationOffice){
        this.emailOutputPort.sendMail(
                email,
                EmailConstants.EMAIL_REJECTION_SUBJECT + course,
                EmailConstants.EMAIL_REJECTION_TEXT + reasonExaminationOffice
        );
    }
}