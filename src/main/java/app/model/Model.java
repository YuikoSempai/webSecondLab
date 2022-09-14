package app.model;

import app.entities.Data;
import app.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();

    private List<Data> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(Data data) {
        model.add(data);
    }

    public List<Data> list() {
        return new ArrayList<>(model);
    }
}