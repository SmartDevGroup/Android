package com.example.vitaliy.sweethome;

public class Socket {
    String status;
    String temp;
    String name;

    public Socket(String status, String temp,String name)
    {
        this.name = name;
        this.status = status;
        this.temp = temp;
    }

    public String getStatus() {
        return status;
    }

    public String getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }
}