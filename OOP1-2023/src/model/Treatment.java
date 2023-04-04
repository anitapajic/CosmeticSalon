package model;

import model.Enum.TreatmentType;


public class Treatment {
    private Integer id;
    private String name;
    private TreatmentType type;
    private Double price;
    private Integer duration;
    private String comestician;



    public Treatment(Integer id, String name, TreatmentType type, Double price, Integer duration, String cosmetician) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.duration = duration;
        this.comestician = cosmetician;
    }

    public Treatment(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreatmentType getType() {
        return type;
    }

    public void setType(TreatmentType type) {
        this.type = type;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getComestician() {
        return comestician;
    }

    public void setComestician(String comestician) {
        this.comestician = comestician;
    }
}
