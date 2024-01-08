package de.novatec.bpm.adapters.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Sets the actualDate as an ProcessVariable
 * (is called at the Start of the Process - see ExecutionListener at the StartEvent)
 * */
@Component
public class StartExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        delegateExecution.setVariable("actualDate", LocalDateTime.now());
    }
}
