package co.edu.poli.ces3.universitas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion {
    // Librería de MySQL
    private String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    private String database = "universitas";

    // Host
    private String hostname = "localhost";

    // Puerto
    private String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public String username = "omesa";

    // Clave de usuario
    public String password = "omesa2023!";

    private Connection conn = null;


    public Conexion(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("***********");
            System.out.println("Conexion exitosa a base de datos " + database);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    abstract int suma(int a, int b);
}
