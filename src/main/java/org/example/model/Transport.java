package org.example.model;

public class Transport {
    private long id;
    private String model;
    private String number;
    private int city_id;

    public Transport() {}

    public Transport(long id, String model, String number, int city_id) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.city_id = city_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
