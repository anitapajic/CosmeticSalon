package model;

import model.Enum.EduCoef;
import model.Enum.Gender;
import model.Enum.Role;
import model.Enum.TreatmentType;

import java.util.ArrayList;
import java.util.List;

public class Cosmetician extends Worker{
    private List<TreatmentType> treatmentsType = new ArrayList<>();
    private List<Treatment> treatments = new ArrayList<>();

    public Cosmetician(String name, String lastname, Gender gender, String telephone, String address, String username, String password, Role role, Double salary, EduCoef educoef, Double yearsOfService, Double bonus, List<TreatmentType> treatmentsType, List<Treatment> treatments) {
        super(name, lastname, gender, telephone, address, username, password, role, salary, educoef, yearsOfService, bonus);
        this.treatmentsType = treatmentsType;
        this.treatments = treatments;
    }

    public Cosmetician(Double salary, EduCoef educoef, Double yearsOfService, Double bonus, List<TreatmentType> treatmentsType, List<Treatment> treatments) {
        super(salary, educoef, yearsOfService, bonus);
        this.treatmentsType = treatmentsType;
        this.treatments = treatments;
    }
    public Cosmetician(){
        super();
    }

    public List<TreatmentType> getTreatmentsType() {
        return treatmentsType;
    }

    public void setTreatmentsType(List<TreatmentType> treatmentsType) {
        this.treatmentsType = treatmentsType;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }



    public void addTreatment(Treatment treatment){
        this.treatments.add(treatment);
    }

    public void deleteTreatment(Treatment treatment){
        this.treatments.remove(treatment);
    }

    public void updateTreatment(Treatment treatment){
        for (Treatment t: this.treatments) {
            if(t.getId().equals(treatment.getId())){
                this.treatments.remove(t);
                this.treatments.add(treatment);
            }
        }
    }



}
