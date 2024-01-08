package de.novatec.bpm.adapters.camunda;

import de.novatec.bpm.ports.EmailInputPort;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class EmailDelegate implements JavaDelegate {
    EmailInputPort emailInputPort;

    public EmailDelegate(EmailInputPort emailInputPort) {
        this.emailInputPort = emailInputPort;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String email = (String) delegateExecution.getVariable("email");
        String course = (String) delegateExecution.getVariable("course");

        if(delegateExecution.getCurrentActivityId().equals("sendLateRegistrationDelegate")){
            emailInputPort.sendMailLateRegistration((String) delegateExecution.getVariable("email"));

        } else if(delegateExecution.getCurrentActivityId().equals("sendRejectionDelegate")){
            String reasonExaminationOffice = (String) delegateExecution.getVariable("reasonExaminationOffice");
            emailInputPort.sendMailRejection(email, course, reasonExaminationOffice);

        } else if (delegateExecution.getCurrentActivityId().equals("sendConfirmationDelegate"))
            emailInputPort.sendMailConfirmation(email, course);
    }
}
