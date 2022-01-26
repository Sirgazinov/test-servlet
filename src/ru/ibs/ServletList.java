package ru.ibs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.ibs.logic.Model;
import ru.ibs.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter pw = response.getWriter();
//
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        if(id == 0){
//            pw.print("<html>" +
//                    "<h3>Доступные пользователи</h3></br>"+
//                    "ID пользователя: "+
//                    "<ul>");
//            for(Map.Entry<Integer, User> map: model.getModel().entrySet()){
//                pw.print("<li>"+map.getKey()+"</li>"+
//                        "<ul>"+
//                        "<li>Имя: " + map.getValue().getName()+ "</li>"+
//                        "<li>Фамилия: " + map.getValue().getSurname()+ "</li>"+
//                        "<li>Зарплата: " + map.getValue().getSalary()+ "</li>"+
//                        "</ul>");
//            }
//            pw.print("</ul>"+
//                    "<a href=\"index.jsp\">Домой</a>"+
//                    "</html>");
//        } else if(id > 0){
//            if(id> model.getModel().size()){
//                pw.print("<html>" +
//                        "<h3>Такого пользователя нет</h3>" +
//                        "</br>" +
//                        "<a href=\"index.jsp\">Домой</a>"+
//                        "</html>");
//            }
//            pw.print("<html>" +
//                    "<h3>Запрошенный пользователь</h3>" +
//                    "</br>" +
//                    "Имя: "+ model.getModel().get(id).getName()+"</br>" +
//                    "Фамилия: "+ model.getModel().get(id).getSurname()+"</br>" +
//                    "Зарплата: "+ model.getModel().get(id).getSalary()+"</br>" +
//                    "<a href=\"index.jsp\">Домой</a>"+
//                    "</html>");
//        } else {
//            pw.print("<html>" +
//                    "<h3>ID должен быть больше нуля</h3>" +
//                    "</br>" +
//                    "<a href=\"index.jsp\">Домой</a>"+
//                    "</html>");
//        }
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        if(id == 0){
            pw.print(gson.toJson(model.getModel()));
        } else {
            pw.print(gson.toJson(model.getModel().get(id)));
        }
    }
}
