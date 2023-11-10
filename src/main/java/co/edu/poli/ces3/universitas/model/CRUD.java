package co.edu.poli.ces3.universitas.model;

import co.edu.poli.ces3.universitas.dto.DtoStudent;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD {
    Student create(DtoStudent stduent) throws SQLException;

    public ArrayList<Student> all();

    public Student findById(int id);

    public Student update(DtoStudent stduent);

    public int delete(int id);
}
