package app.model;

import app.entities.Data;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model instance = new Model();

    private List<Data> model;

    private Model() {
        model = new ArrayList<>();
    }

    public static Model getInstance() {
        return instance;
    }

    public void add(Data data) {
        model.add(data);
    }

    public List<Data> list() {
        return new ArrayList<>(model);
    }

    public Data getLastData() {
        if (model.size() == 0) {
            return null;
        } else {
            return model.get(model.size() - 1);
        }
    }
    public void clear(){
        model = new ArrayList<>();
    }
}