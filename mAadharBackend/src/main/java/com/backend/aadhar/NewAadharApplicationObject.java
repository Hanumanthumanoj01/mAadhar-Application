package com.backend.aadhar;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 13/10/22
 * Time: 09:57
 */

@Entity
public class NewAadharApplicationObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @Column(unique = true)
    private String citizenId;

    private String dateOfApplication;

    private String passportId;
    private String currentStatus;
    private String typeOfApplication;
    private boolean isIssued;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(String dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getTypeOfApplication() {
        return typeOfApplication;
    }

    public void setTypeOfApplication(String typeOfApplication) {
        this.typeOfApplication = typeOfApplication;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public void setDateOfTheApplication (){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.dateOfApplication = String.valueOf(formatter.format(date));
    }
}
