package ru.ibs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculate")
public class ServletCalculator extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
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

        String a = jsonObject.get("a").getAsString();
        String b = jsonObject.get("b").getAsString();
        String math = jsonObject.get("math").getAsString();

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        String expression = a + math + b;

        pw.print(gson.toJson(new CalculateResult(getResult(expression))));
    }

    private Object getResult(String expression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Object result = null;

        try {
            result = engine.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }
}
