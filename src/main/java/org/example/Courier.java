package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private String login;
    private String password;
    private String firstName;
    public Courier(String login, String password, String firstName ) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // конструктор без параметров
    public Courier() {
    }
    public String getLogin() {
        return login;
    }

    public static Courier random() {
        return new Courier(RandomStringUtils.randomAlphabetic(5, 15), "P@ssw0rd123", "Sparrow");
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}


