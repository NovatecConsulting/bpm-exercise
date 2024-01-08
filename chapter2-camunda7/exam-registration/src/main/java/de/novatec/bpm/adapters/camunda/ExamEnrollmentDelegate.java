package de.novatec.bpm.adapters.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ExamEnrollmentDelegate implements JavaDelegate {
   @Override
    public void execute(DelegateExecution execution) {
        execution.setVariable("examEnrollment", true);
    }
}
