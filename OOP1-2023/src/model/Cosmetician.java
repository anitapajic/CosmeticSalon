package model;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;
import java.util.ArrayList;
import java.util.List;

public class Cosmetician extends Worker {

    public Cosmetician(Integer id, String name, String lastname, Gender gender, String telephone, String address, String username, String password, Role role, Double salary, EduCoef educoef, Double yearsOfService, Double bonus, List<Appointment> appointments) {
        super(id, name, lastname, gender, telephone, address, username, password, role, salary, educoef, yearsOfService, bonus);
    }

    public Cosmetician(Double salary, EduCoef educoef, Double yearsOfService, Double bonus) {
        super(salary, educoef, yearsOfService, bonus);
    }
    public Cosmetician(){
        super();
    }


}
