package service;

import repository.TreatmentsRepository;

public class TreatmentService {

    private TreatmentsRepository treatmentsRepository;

    public TreatmentService(TreatmentsRepository treatmentsRepository){
        this.treatmentsRepository = treatmentsRepository;
    }


    public TreatmentsRepository getTreatmentsRepository() {
        return treatmentsRepository;
    }

    public void setTreatmentsRepository(TreatmentsRepository treatmentsRepository) {
        this.treatmentsRepository = treatmentsRepository;
    }
}
