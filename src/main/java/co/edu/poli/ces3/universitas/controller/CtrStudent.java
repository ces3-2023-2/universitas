package co.edu.poli.ces3.universitas.controller;

import co.edu.poli.ces3.universitas.dto.DtoStudent;
import co.edu.poli.ces3.universitas.model.Student;

import java.sql.SQLException;

public class CtrStudent {
    private Student modelStudent;

    public CtrStudent(){
        modelStudent = new Student();
    }

    public DtoStudent addStudent(DtoStudent student){
        try {
            Student newStudent = modelStudent.create(student);
            return new DtoStudent(newStudent.getId(), newStudent.getDocument(), newStudent.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
