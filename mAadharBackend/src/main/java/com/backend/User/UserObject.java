package com.backend.User;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 12/10/22
 * Time: 14:48
 */


@Entity
public class UserObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;


    private String userFullName;
    private String email;
    private String gender;
    private String address;

    private String mobile;

    @Column(unique = true)
    private String citizenId;

    private boolean aadharApplied;  // to chech whether a user is aadhar holder or not
    private String passportId;
    private String issueDate;

    private final int role;
    // 0 for normal user and 1 for admin

    public UserObject() {
        this.role = 0;
        this.citizenId = generateCitizenId();
        this.aadharApplied = false;
    }


    public int getUserId() {
        return userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public boolean isAadharApplied() {
        return aadharApplied;
    }

    public void setAadharApplied(boolean aadharApplied) {
        this.aadharApplied = aadharApplied;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate() {
        addIssueDate();
    }

    public int getRole() {
        return role;
    }


    private String generateCitizenId() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date citizenId = new Date();

//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, +7);
//        Date todate1 = cal.getTime();
//        String fromdate = formatter.format(todate1);
//        System.out.println(formatter.format(citizenId) + " " + fromdate);

        return formatter.format(citizenId);
    }

    private void addIssueDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date issueDate = new Date();
        this.issueDate = formatter.format(issueDate);
    }
}