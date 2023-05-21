package com.example.weatherapi;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("main")
    Pojo MainObject;


    // Getter Methods

    public Pojo getMain() {
        return MainObject;
    }

    // Setter Methods

    public void setMain(Pojo mainObject) {
        this.MainObject = mainObject;
    }
    @SerializedName("wind")
    Wind WindObject;

    public Wind getWindObject() {
        return WindObject;
    }

    public void setWindObject(Wind windObject) {
        WindObject = windObject;
    }
}
