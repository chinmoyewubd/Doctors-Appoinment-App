package com.example.doctorsappointment;

public class Appointment {

    // Declaring the fields
    String doctoid;
    String patientid;
    String patientname;
    String address;
    String patientcontact;
    long date;

    // Default constructor
    public Appointment() {
    }

    // Constructor to initialize the fields
    public Appointment(String doctoid, String patientid, String patientname, String address, String patientcontact, long date) {
        this.doctoid = doctoid;
        this.patientid = patientid;
        this.patientname = patientname;
        this.address = address;
        this.patientcontact = patientcontact;
        this.date = date;
    }

    // Getter and Setter methods (optional, for encapsulation)
    public String getDoctoid() {
        return doctoid;
    }

    public void setDoctoid(String doctoid) {
        this.doctoid = doctoid;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPatientcontact() {
        return patientcontact;
    }

    public void setPatientcontact(String patientcontact) {
        this.patientcontact = patientcontact;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
