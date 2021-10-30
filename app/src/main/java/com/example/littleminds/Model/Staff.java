package com.example.littleminds.Model;

public class Staff {
    private String name, tp, password;

    public Staff()
    {

    }

    public Staff(String name, String tp, String password) {
        this.name = name;
        this.tp = tp;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
