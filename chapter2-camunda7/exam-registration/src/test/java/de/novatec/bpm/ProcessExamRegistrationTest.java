package de.novatec.bpm;

import de.novatec.bpm.adapters.camunda.EmailDelegate;
import de.novatec.bpm.adapters.camunda.StudentDelegate;
import de.novatec.bpm.ports.EmailInputPort;
import de.novatec.bpm.ports.StudentInputPort;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Date;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProcessExamRegistrationTest {

    @Autowired
    private RuntimeService runtimeService;

    @Mock
    StudentInputPort studentInputPort;

    @Mock
    EmailInputPort emailInputPort;

    @BeforeEach
    public void setUp() {
        Mocks.register("studentDelegate", new StudentDelegate(studentInputPort));
        Mocks.register("emailDelegate", new EmailDelegate(emailInputPort));
    }

    @Test
    public void studentDoesNotExist() {
        var variables = Variables.createVariables()
                .putValue("email", "email@email.de")
                .putValue("course", "Test Course A");

        when(studentInputPort.checkIfStudentExists(anyString())).thenReturn(false);

        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey("exam-registration", variables);

        assertThat(processInstance).hasPassed("studentExistsDelegate");
        assertThat(processInstance).hasPassed("EndEvent_StudentDoesNotExist");
        assertThat(processInstance).isEnded();

        assertThat(processInstance).hasVariables("actualDate", "studentExists", "email", "course");
    }

    @Test
    public void studentDoesExistNotInRegistrationPeriodAndNoLateRegistrationAccepted() {

        when(studentInputPort.checkIfStudentExists(anyString())).thenReturn(true);

        var variables = Variables.createVariables()
                .putValue("email", "email@email.de")
                .putValue("course", "Test Course A")
                .putValue("studentExists", true)
                .putValue("actualDate", LocalDateTime.now())
                .putValue("isRegistrationPeriod", false);

        ProcessInstance processInstance = this.runtimeService
                .createProcessInstanceByKey("exam-registration")
                .startAfterActivity("registrationPeriodCheckDMN")
                .setVariables(variables)
                .execute();

        assertThat(processInstance).hasPassed("sendLateRegistrationDelegate");

        assertThat(processInstance).isWaitingAt("userTaskLateRegistration");
        complete(task(), withVariables("submitLateRegistration", true,
                "reasonStudent", "example Reason"));

        assertThat(processInstance).isWaitingAt("userTaskLateRegistrationCheck");
        complete(task(), withVariables("lateRegistrationAccepted", false,
                "reasonExaminationOffice", "example Reason - not Accepted"));

        assertThat(processInstance).hasPassed("sendRejectionDelegate");

        assertThat(processInstance).hasPassed("End_Rejection");
        assertThat(processInstance).isEnded();

        assertThat(processInstance).hasVariables("actualDate", "studentExists", "email", "isRegistrationPeriod",
                "reasonStudent", "reasonExaminationOffice", "lateRegistrationAccepted", "submitLateRegistration");

    }

    @Test
    public void studentDoesExistNotInRegistrationPeriodAndNoLateRegistrationIsRequested() {

        when(studentInputPort.checkIfStudentExists(anyString())).thenReturn(true);

        var variables = Variables.createVariables()
                .putValue("email", "email@email.de")
                .putValue("course", "Test Course A")
                .putValue("studentExists", true)
                .putValue("actualDate", LocalDateTime.now())
                .putValue("isRegistrationPeriod", false);


        ProcessInstance processInstance = this.runtimeService
                .createProcessInstanceByKey("exam-registration")
                .startAfterActivity("registrationPeriodCheckDMN")
                .setVariables(variables)
                .execute();

        assertThat(processInstance).hasPassed("sendLateRegistrationDelegate");

        assertThat(processInstance).isWaitingAt("userTaskLateRegistration");
        execute(job(findId("after 7 days")));

        assertThat(processInstance).hasPassed("EndEvent_NoSubmitForLateRegistrationAfter7Days");
        assertThat(processInstance).isEnded();

        assertThat(processInstance).hasVariables("actualDate", "studentExists", "email", "isRegistrationPeriod");

    }
    @Test
    public void studentDoesExistInRegistrationPeriod() {

        when(studentInputPort.checkIfStudentExists(anyString())).thenReturn(true);

        var variables = Variables.createVariables()
                .putValue("email", "email@email.de")
                .putValue("course", "Test Course A")
                .putValue("isRegistrationPeriod", true);

        ProcessInstance processInstance = this.runtimeService
                .createProcessInstanceByKey("exam-registration")
                .setVariables(variables)
                .execute();

        assertThat(processInstance).hasPassed("examEnrollmentDelegate");

        assertThat(processInstance).hasPassed("sendConfirmationDelegate");

        assertThat(processInstance).hasPassed("End_Confirmation");
        assertThat(processInstance).isEnded();

        assertThat(processInstance).hasVariables("actualDate", "studentExists", "email", "isRegistrationPeriod");

    }
}

