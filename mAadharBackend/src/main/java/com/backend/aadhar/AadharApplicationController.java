package com.backend.aadhar;


import com.backend.User.UserObject;
import com.backend.User.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 13/10/22
 * Time: 10:06
 */

@RestController
@RequestMapping("/AadharApp")
@CrossOrigin(origins = "http://localhost:4200/", methods = {RequestMethod.GET, RequestMethod.POST})
public class AadharApplicationController {
    @Autowired
    AadharApplicationServices applicationServices;

    @Autowired
    UserServices userServices;

    @GetMapping("/admin/applications")
    public ResponseEntity<List<NewAadharApplicationObject>> allApplications () {
        try {
            List<NewAadharApplicationObject> ApplicationsList = new ArrayList<NewAadharApplicationObject>();
            applicationServices.findByAllApplications().forEach(ApplicationsList::add);

            if(ApplicationsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(ApplicationsList,HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/admin/applications/approvenewAadharApplication")
    public ResponseEntity<Object> approveApplication (@RequestBody NewAadharApplicationObject application) {
        try {
            int applicationId = application.getApplicationId();

            List<NewAadharApplicationObject> applicationForApproval =  applicationServices.findByApplicationId(applicationId);

            if(applicationForApproval.size() != 0) {

                String citizenId = applicationForApproval.get(0).getCitizenId();

                List<UserObject> user =  userServices.findByCitizenId(citizenId);
                System.out.println("****************************");
                System.out.println("ctz " + user.get(0));
                System.out.println("****************************");
                user.get(0).setIssueDate();
                userServices.updateUser(user.get(0));   // updating the user entity with the issued date

                applicationServices.deleteApprovedApplication(applicationId);   // deleting the approved application

                return new ResponseEntity<>("Application approved successfully.",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Incorrect Application Id.",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            System.out.println("exception is : " + e);
            return new ResponseEntity<Object>("There is some issue, please try again later.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

//    @PutMapping("/citizens/applications/updateAadhar")
//    public ResponseEntity<Object> approveUpdateAadharApplication (@RequestBody UserObject userNewDetails) {
//        try {
//            int applicationId = application.getApplicationId();
//            List<NewAadharApplicationObject> applicationForApproval =  applicationServices.findByApplicationId(applicationId);
//
//            if(applicationForApproval.size() != 0) {
//
//                String citizenId = applicationForApproval.get(0).getCitizenId();
//                List<UserObject> user =  userServices.findByCitizenId(citizenId);
//                user.get(0).setIssueDate();
//                userServices.updateUser(user.get(0));
//
//                applicationServices.deleteApprovedApplication(applicationId);
//
//                return new ResponseEntity<>("Application approved successfully.",HttpStatus.OK);
//            }
//            else {
//                return new ResponseEntity<>("Incorrect Application Id.",HttpStatus.BAD_REQUEST);
//            }
//        }
//        catch (Exception e) {
//            System.out.println("exception is : " + e);
//            return new ResponseEntity<Object>("There is some issue, please try again later.", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
}