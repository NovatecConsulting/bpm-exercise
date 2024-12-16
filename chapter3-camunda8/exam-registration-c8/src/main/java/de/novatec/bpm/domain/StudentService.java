package de.novatec.bpm.domain;

import de.novatec.bpm.ports.StudentInputPort;
import de.novatec.bpm.ports.StudentOutputPort;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentInputPort  {
    private final StudentOutputPort studentOutputPort;

    public StudentService(StudentOutputPort studentOutputPort) {
        this.studentOutputPort = studentOutputPort;
    }
    @Override
    public boolean checkIfStudentExists(String email) {
        return studentOutputPort.checkIfStudentExists(email);
    }
}
