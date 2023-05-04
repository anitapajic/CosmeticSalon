package model;

import java.time.LocalTime;

public class Salon {
    private String name;
    private String workHours;

    public Salon(){};

    public Salon(String name, String workHours) {
        this.name = name;
        this.workHours = workHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
}
