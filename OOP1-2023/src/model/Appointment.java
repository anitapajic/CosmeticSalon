package model;

import model.Enum.TreatmentStatus;
import model.Enum.TreatmentType;

import java.time.LocalDateTime;
import java.util.List;

public class Appointment extends Treatment {

    private String client;
    private Integer cosmeticianId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TreatmentStatus status;

    public Appointment(Integer id, String name, TreatmentType type, Double price, Integer duration, List<Integer> cosmeticians, String client, Integer cosmeticianId, LocalDateTime startTime, LocalDateTime endTime, TreatmentStatus status) {
        super(id, name, type, price, duration,cosmeticians);
        this.client = client;
        this.cosmeticianId = cosmeticianId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Appointment(String client, Integer cosmeticianId, LocalDateTime startTime, LocalDateTime endTime, TreatmentStatus status) {
        this.client = client;
        this.cosmeticianId = cosmeticianId;
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

    public Integer getCosmeticianId() {
        return cosmeticianId;
    }

    public void setCosmeticianId(Integer cosmeticianId) {
        this.cosmeticianId = cosmeticianId;
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

    @Override
    public String toString() {
        String date = getStartTime().getDayOfMonth()+"."+getStartTime().getMonth()+"."+(getStartTime().getYear())+"." + " " + getStartTime().getHour() + ":" + getStartTime().getMinute();
        String date2 = getEndTime().getDayOfMonth()+"."+getEndTime().getMonth()+"."+(getEndTime().getYear())+"." + " " + getEndTime().getHour() + ":" + getEndTime().getMinute();

        return  this.getId() + "|"+
                this.getName() + "|" +
                this.getType() + "|" +
                this.getPrice() + "|" +
                this.getDuration() + "|" +
                this.getComesticians() + "|"+
                this.getClient() + "|" +
                this.getCosmeticianId() + "|" +
                date + "|" +
                date2 + "|" +
                this.getStatus() + "\n";

    }
}
