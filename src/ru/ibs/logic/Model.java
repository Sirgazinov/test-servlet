package ru.ibs.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();

    private final Map<Integer,User> model;

    public static Model getInstance(){
        return instance;
    }

    public Model(){
        model = new HashMap<>();

        model.put(1, new User("Ivan", "Ivanov", 12345));
        model.put(2, new User("Stepan", "Stepanov", 54321));
        model.put(3, new User("Petr", "Petrov", 77777));
    }

    public void add(User user, int id){
        model.put(id,user);
    }

    public Map<Integer,User> getModel(){
        return model;
    }
}
