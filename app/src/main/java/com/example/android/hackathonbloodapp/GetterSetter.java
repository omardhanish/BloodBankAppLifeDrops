package com.example.android.hackathonbloodapp;

/**
 * Created by omar on 14-02-2017.
 */
public class GetterSetter  {

    private String yourName;
    private String yourPlace;
    private String phoneNumber;
    private String BloodGroupNeededUrg;
    private String hospitalName;
    private String typeUrgentOrVeruyUrgent;
    private String receiverorgiver;


    public String getReceiverorgiver() {
        return receiverorgiver;
    }

    public void setReceiverorgiver(String receiverorgiver) {
        this.receiverorgiver = receiverorgiver;
    }




    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getYourPlace() {
        return yourPlace;
    }

    public void setYourPlace(String yourPlace) {
        this.yourPlace = yourPlace;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroupNeededUrg() {
        return BloodGroupNeededUrg;
    }

    public void setBloodGroupNeededUrg(String bloodGroupNeededUrg) {
        BloodGroupNeededUrg = bloodGroupNeededUrg;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getTypeUrgentOrVeruyUrgent() {
        return typeUrgentOrVeruyUrgent;
    }

    public void setTypeUrgentOrVeruyUrgent(String typeUrgentOrVeruyUrgent) {
        this.typeUrgentOrVeruyUrgent = typeUrgentOrVeruyUrgent;
    }
}
