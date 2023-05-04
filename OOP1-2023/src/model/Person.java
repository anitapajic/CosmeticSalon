package model;

import model.Enum.Gender;
import model.Enum.Role;

import java.io.Serializable;

public class Person  {
    private Integer id;
    private String name;
    private String lastname;
    private Gender gender;
    private String telephone;
    private String address;
    private String username;
    private String password;
    private Role role;

    public Person(Integer id, String name, String lastname, Gender gender, String telephone, String address, String username, String password, Role role) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.telephone = telephone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public Person(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return  id + "|"+
                name + "|" +
                lastname + "|" +
                gender + "|" +
                telephone + "|" +
                address + "|" +
                username + "|" +
                password + "|" +
                role + '\n';
    }
}
