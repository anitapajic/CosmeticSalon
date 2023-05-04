package model;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;

import java.io.Serializable;

public class Worker extends Person implements Serializable {
    private Double salary;
    private EduCoef educoef;
    private Double yearsOfService;
    private Double bonus;

    public static Integer MIN_SALARY = 40000;

    public Worker(Integer id, String name, String lastname, Gender gender, String telephone, String address, String username, String password, Role role, Double salary, EduCoef educoef, Double yearsOfService, Double bonus) {
        super(id, name, lastname, gender, telephone, address, username, password, role);
        this.salary = salary;
        this.educoef = educoef;
        this.yearsOfService = yearsOfService;
        this.bonus = bonus;
    }

    public Worker(Double salary, EduCoef educoef, Double yearsOfService, Double bonus) {
        this.salary = salary;
        this.educoef = educoef;
        this.yearsOfService = yearsOfService;
        this.bonus = bonus;
    }
    public Worker(){}

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = countSalary(this.getCoefFromEduCoef(this.getEducoef()),this.getYearsOfService(), this.getBonus());
    }

    public EduCoef getEducoef() {
        return educoef;
    }

    public void setEducoef(EduCoef educoef) {
        this.educoef = educoef;
    }

    public Double getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(Double yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getCoefFromEduCoef(EduCoef educoef){
        double coef = 0.0;

        if(educoef.equals(EduCoef.FIRST)){
            coef = 1.0;
        }
        else if(educoef.equals(EduCoef.SECOND)){
            coef = 1.1;
        }
        else if(educoef.equals(EduCoef.THIRD)){
            coef = 1.2;
        }
        else if(educoef.equals(EduCoef.FOURTH)){
            coef = 1.3;
        }
        else if(educoef.equals(EduCoef.FIFTH)){
            coef = 1.4;
        }
        else if(educoef.equals(EduCoef.SIXTH)){
            coef = 1.5;
        }
        return coef;
    }

    public Double countSalary(Double coef, Double yearsOfService, Double bonus ){
        return coef * yearsOfService * MIN_SALARY + bonus;
    }

    @Override
    public String toString() {
        return  this.getId() + "|"+
                this.getName() + "|" +
                this.getLastname() + "|" +
                this.getGender() + "|" +
                this.getTelephone() + "|" +
                this.getAddress() + "|" +
                this.getUsername() + "|" +
                this.getPassword() + "|" +
                this.getRole() + "|" +
                salary + "|" +
                educoef + "|" +
                yearsOfService + "|" +
                bonus + "\n";
    }
}
