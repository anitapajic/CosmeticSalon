package model;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;

import java.io.Serializable;

public class Receptionist extends Worker {
    public Receptionist(Integer id, String name, String lastname, Gender gender, String telephone, String address, String username, String password, Role role, Double salary, EduCoef educoef, Double yearsOfService, Double bonus) {
        super(id, name, lastname, gender, telephone, address, username, password, role, salary, educoef, yearsOfService, bonus);
    }

    public Receptionist(Double salary, EduCoef educoef, Double yearsOfService, Double bonus) {
        super(salary, educoef, yearsOfService, bonus);
    }

    public Receptionist() {
    }

}
