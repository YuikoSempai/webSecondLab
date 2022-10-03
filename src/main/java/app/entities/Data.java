package app.entities;

import java.util.Objects;

public class Data {
    private String x;
    private String y;
    private String r;
    private String status;

    public Data(String x, String y, String r, String status) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
    }

    @Override
    public String toString() {
        return  "x=" + x  +
                " y=" + y  +
                " r=" + r  +
                " status=" + status;
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

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getStatus() {
        return status;
    }
}
