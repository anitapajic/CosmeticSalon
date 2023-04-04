package model;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;

public class Menager extends Worker {

    public Menager(String name, String lastname, Gender gender, String telephone, String address, String username, String password, Role role, Double salary, EduCoef educoef, Double yearsOfService, Double bonus) {
        super(name, lastname, gender, telephone, address, username, password, role, salary, educoef, yearsOfService, bonus);
    }

    public Menager(Double salary, EduCoef educoef, Double yearsOfService, Double bonus) {
        super(salary, educoef, yearsOfService, bonus);
    }

    public Menager() {
    }
}
