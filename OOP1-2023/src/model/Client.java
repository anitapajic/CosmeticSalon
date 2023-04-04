package model;

import model.Enum.Gender;
import model.Enum.LoyalityCardStatus;
import model.Enum.Role;

public class Client extends Person {
    private LoyalityCardStatus cardStatus;
    private Double cardValue;

    public Client(Integer id, String name, String lastname, Gender gender, String telephone, String address, String username, String password, Role role, LoyalityCardStatus cardStatus, Double cardValue) {
        super(id, name, lastname, gender, telephone, address, username, password, role);
        this.cardStatus = cardStatus;
        this.cardValue = cardValue;
    }

    public Client(LoyalityCardStatus cardStatus, Double cardValue) {
        this.cardStatus = cardStatus;
        this.cardValue = cardValue;
    }
    public Client(){}

    public LoyalityCardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(LoyalityCardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Double getCardValue() {
        return cardValue;
    }

    public void setCardValue(Double cardValue) {
        this.cardValue = cardValue;
    }

}
