package com.backend.aadhar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 13/10/22
 * Time: 10:27
 */

@Service
public class AadharApplicationServices {
    @Autowired
    AadharApplicationJPARepo aadharApplicationRepo;

    public NewAadharApplicationObject newApplication(NewAadharApplicationObject application) {
        return aadharApplicationRepo.save(application);
    }

    public List<NewAadharApplicationObject> findByApplicationId(int applicationId) {
        return  aadharApplicationRepo.findByApplicationId(applicationId);
    }

    public List<NewAadharApplicationObject> findByAllApplications() {
        return  aadharApplicationRepo.findAll();
    }
    public void deleteApprovedApplication(int applicationId) {
        List<NewAadharApplicationObject> approvedApplication = aadharApplicationRepo.findByApplicationId(applicationId);
        aadharApplicationRepo.delete(approvedApplication.get(0));
    }
}
