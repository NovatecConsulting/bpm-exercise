package de.novatec.bpm.adapters.camunda;

import de.novatec.bpm.ports.StudentInputPort;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class StudentDelegate implements JavaDelegate {
    StudentInputPort studentInputPort;
    public StudentDelegate(StudentInputPort StudentInputPort) {
        this.studentInputPort = StudentInputPort;
    }

    @Override
    public void execute(DelegateExecution execution) {
        // Retrieve process variables from Camunda execution
        String email = (String) execution.getVariable("email");

        // Execute the use case
        boolean studentExists = studentInputPort.checkIfStudentExists(email);

        // Set process variable based on the result
        execution.setVariable("studentExists", studentExists);
    }
}
