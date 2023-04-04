package model;

import model.Enum.TreatmentStatus;
import model.Enum.TreatmentType;

import java.time.LocalDateTime;
import java.util.List;

public class Treatment {
    private Integer id;
    private String name;
    private TreatmentType type;
    private Cosmetician cosmetician;
    private Double price;
    private Integer duration;
    private Client client;
    private TreatmentStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Treatment(Integer id, String name, TreatmentType type, Cosmetician cosmetician, Double price, Integer duration, Client client, TreatmentStatus status, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cosmetician = cosmetician;
        this.price = price;
        this.duration = duration;
        this.client = client;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Cosmetician getCosmetician() {
        return cosmetician;
    }

    public void setCosmetician(Cosmetician cosmetician) {
        this.cosmetician = cosmetician;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TreatmentStatus getStatus() {
        return status;
    }

    public void setStatus(TreatmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
