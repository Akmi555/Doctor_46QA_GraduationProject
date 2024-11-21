package com.doctor.model;

public class User {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String password;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contact{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');


        sb.append('}');
        return sb.toString();
    }


    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }



    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
