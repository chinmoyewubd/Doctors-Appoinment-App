package com.example.doctorsappointment;

public class Doctor {
    String id;
    String name;
    String speciality;

    String shr;
    String ehr;
    String contact;

    public Doctor(){

    }
    public Doctor(String id, String name, String speciality,String shr,String ehr,String contact){
        this.id = id;
        this.name = name;
        this.speciality = speciality;

        this.shr = shr;
        this.ehr = ehr;

        this.contact =contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getEhr() {
        return ehr;
    }

    public void setEhr(String ehr) {
        this.ehr = ehr;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String toString(){return id+","+name+","+speciality+","+shr+","+ehr+","+contact;}
}