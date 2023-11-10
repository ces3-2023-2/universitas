package co.edu.poli.ces3.universitas.servlet;

import co.edu.poli.ces3.universitas.controller.CtrStudent;
import co.edu.poli.ces3.universitas.dto.DtoStudent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "studentServlet", value = "/student")
public class StudentServlet extends MyServlet {
    private String message;
    private ArrayList<DtoStudent> students;

    private GsonBuilder gsonBuilder;

    private Gson gson;
    public void init() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        students = new ArrayList<>();

        DtoStudent student1 = new DtoStudent();
        student1.id = 10;
        student1.setName("pedro");
        student1.setDocument("454543545");

        students.add(student1);

        for (int i = 0; i < students.size(); i++)
        {
            System.out.println(students.get(i));
        }
        message = "Hello Poli!!!";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body =  this.getParamsFromPost(req);
        CtrStudent ctr = new CtrStudent();
        DtoStudent std = new DtoStudent(
                body.get("id").getAsInt(),
                body.get("document").getAsString(),
                body.get("name").getAsString()
        );
        DtoStudent newStudent = ctr.addStudent(std);

        out.print(gson.toJson(newStudent));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String studentId = request.getParameter("studentId");
        PrintWriter out = response.getWriter();

        if(studentId == null) {
            out.println(gson.toJson(students));
        }else {
            DtoStudent studentSearch = null;
            for (DtoStudent s: students) {
                if (s.getId() == Integer.parseInt(studentId)){
                    studentSearch = s;
                    break;
                }
            }
            out.println(gson.toJson(studentSearch));
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        switch (method){
            case "PATCH":
                this.doPatch(req, resp);
                break;
            default:
                super.service(req, resp);
        }

    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("************");
        System.out.println("Entro al metodo patch!!!");
        System.out.println("************");
    }
    public void destroy() {
    }
}