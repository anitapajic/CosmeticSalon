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

    public Client(Person u) {
        this.setId(u.getId());
        this.setName(u.getName());
        this.setLastname(u.getLastname());
        this.setGender(u.getGender());
        this.setTelephone(u.getTelephone());
        this.setAddress(u.getAddress());
        this.setUsername(u.getUsername());
        this.setPassword(u.getPassword());
    }

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

    @Override
    public String toString() {
        return  this.getId() + "|"+
                this.getName() + "|" +
                this.getLastname() + "|" +
                this.getGender() +
                this.getTelephone() + "|" +
                this.getAddress() + "|" +
                this.getUsername() + "|" +
                this.getPassword() + "|" +
                this.getCardStatus() + "|"+
                this.getCardValue() + '\n';
    }
}
