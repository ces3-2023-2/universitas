package co.edu.poli.ces3.universitas.model;

import co.edu.poli.ces3.universitas.dto.DtoStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends Conexion implements CRUD{
    public int id;

    protected String document;

    private String name;

    public Student(int id, String document, String name){
        this.id = id;
        this.document = document;
        this.name = name;
    }

    public Student(String document){
        this.document = document;
    }

    public Student() {

    }

    public int getId(){
        return this.id;
    }


    private void setId(int id){
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "El estudiante se llama: " + this.name +
                " su documento es: " + this.document;
    }

    @Override
    public Student create(DtoStudent student) throws SQLException {
        Connection cnn = this.getConexion();
        if(cnn != null) {
            String sql = "INSERT INTO user(document, name) VALUES('"+student.getDocument()+"', '"+student.getName()+"')";
            this.document = student.getDocument();
            this.name = student.getName();
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                this.id = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
               cnn.close();
            }
            return this;
        }
        return null;
    }

    @Override
    public ArrayList<Student> all() {
        return null;
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public Student update(DtoStudent stduent) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
