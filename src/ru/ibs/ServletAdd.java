package ru.ibs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.ibs.logic.Model;
import ru.ibs.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    AtomicInteger counter = new AtomicInteger(4);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name, surname, salary);
//        model.add(user, counter.getAndIncrement());
//
//        pw.print("<html>" +
//                "<h3>Пользователь " + name + " " + surname + " с зарплатой " + salary + " успешно создан." +
//                "<a href=\"addUser.html\">Создать нового пользователя</a></br>" +
//                "<a href=\"index.jsp\">Домой</a>" +
//                "</html>");
//
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JsonObject jsonObject = gson.fromJson(String.valueOf(stringBuffer),JsonObject.class);

        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsInt();

        request.setCharacterEncoding("UTF-8");

        User user = new User(name, surname, salary);
        model.add(user, counter.getAndIncrement());

        response.setContentType("application/json; charset=utf-8");

        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getModel()));
    }
}
