package model;

import model.Enum.TreatmentStatus;
import model.Enum.TreatmentType;

import java.time.LocalDateTime;

public class Appointment extends Treatment {

    private String client;
    private String cosmetician;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TreatmentStatus status;

    public Appointment(Integer id, String name, TreatmentType type, Double price, Integer duration, String cosmetician0, String client, String cosmetician, LocalDateTime startTime, LocalDateTime endTime, TreatmentStatus status) {
        super(id, name, type, price, duration,cosmetician0);
        this.client = client;
        this.cosmetician = cosmetician;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Appointment(String client, String cosmetician, LocalDateTime startTime, LocalDateTime endTime, TreatmentStatus status) {
        this.client = client;
        this.cosmetician = cosmetician;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
    public Appointment(){}

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCosmetician() {
        return cosmetician;
    }

    public void setCosmetician(String cosmetician) {
        this.cosmetician = cosmetician;
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

    public TreatmentStatus getStatus() {
        return status;
    }

    public void setStatus(TreatmentStatus status) {
        this.status = status;
    }
}
