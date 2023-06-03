package model;

import model.Enum.TreatmentType;

import java.util.List;


public class Treatment {
    private Integer id;
    public String name;
    private TreatmentType type;
    private Double price;
    private Integer duration;
    private List<Integer> comesticians;



    public Treatment(Integer id, String name, TreatmentType type, Double price, Integer duration, List<Integer> cosmeticians) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.duration = duration;
        this.comesticians = cosmeticians;
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

    public List<Integer> getComesticians() {
        return comesticians;
    }

    public void setComesticians(List<Integer> comesticians) {
        this.comesticians = comesticians;
    }

    @Override
    public String toString() {
        return  this.getId() + "|"+
                this.getName() + "|" +
                this.getType() + "|" +
                this.getPrice() + "|" +
                this.getDuration() + "|" +
                this.getComesticians() + "\n";

    }
}
