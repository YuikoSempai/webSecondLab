package app.entities;

import java.util.Objects;

public class Data {
    private String x;
    private String y;
    private String r;

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }

    public Data(String x, String y, String r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(x, data.x) && Objects.equals(y, data.y) && Objects.equals(r, data.r);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r);
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getR() {
        return r;
    }
}
