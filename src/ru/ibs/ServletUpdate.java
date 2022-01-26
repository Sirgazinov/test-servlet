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

@WebServlet(urlPatterns = "/update")
public class ServletUpdate extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsInt();

        request.setCharacterEncoding("UTF-8");

        User user = new User(name, surname, salary);
        model.getModel().replace(id,user);

        response.setContentType("application/json; charset=utf-8");

        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getModel()));
    }
}
