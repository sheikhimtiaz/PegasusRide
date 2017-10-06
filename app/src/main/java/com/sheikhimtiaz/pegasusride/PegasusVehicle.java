package com.sheikhimtiaz.pegasusride;

/**
 * Created by sheikhimtiaz on 11/5/2016.
 */

public class PegasusVehicle {

    private String ID;
    private String model;
    private String code;
    private String brand;

    private String description;

    public PegasusVehicle(){

    }

    public PegasusVehicle(String ID, String model, String code, String brand, String description){
        this.ID=ID;
        this.model=model;
        this.code=code;
        this.brand=brand;
        this.description=description;
    }

    public String getID() {
        return ID;
    }

    public String getModel() {
        return model;
    }

    public String getCode() {
        return code;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }
}
