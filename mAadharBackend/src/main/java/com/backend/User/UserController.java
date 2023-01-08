package com.backend.User;

import com.backend.aadhar.NewAadharApplicationObject;
import com.backend.aadhar.AadharApplicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 12/10/22
 * Time: 15:01
 */

@RestController
@RequestMapping("/AadharApp/citizens")
@CrossOrigin(origins = "http://localhost:4200/", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    AadharApplicationServices aadharApplicationServices;


    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody UserObject user){
        try {
            UserObject res = userServices.addUser(user);

            if(res != null) {
                return new ResponseEntity<Object>(res, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<Object>("There is some issue, please try again later.", HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<Object>("Facing some issue while trying to create your account, please try with different username.", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/logIn")
    public ResponseEntity<Object> signIn(@RequestBody UserLoginObject auth) {

        System.out.println(auth.getCitizenId() + " " + auth.getPassword());

        try {
            List<UserObject> user =  userServices.findByCitizenId(auth.getCitizenId());
            if(user.size() != 0) {
                if(user.get(0).getMobile().equals(auth.getPassword())){
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Incorrect password.",HttpStatus.UNAUTHORIZED);
                }
            }
            else {
                return new ResponseEntity<>("Incorrect citizenId.",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            System.out.println("exception is : " + e);
            return new ResponseEntity<Object>("There is some issue, please try again later.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/dashboard")
    public ResponseEntity<Object> dashboard(@RequestBody UserObject signedInUser) {
        try {
            List<UserObject> user =  userServices.findByCitizenId(signedInUser.getCitizenId());

            if(user.size() != 0) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Incorrect citizenId.",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            System.out.println("exception is : " + e);
            return new ResponseEntity<Object>("There is some issue, please try again later.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/issueAadhar")
    public ResponseEntity<Object> issueAadhar(@RequestBody NewAadharApplicationObject newApplication){
        newApplication.setDateOfTheApplication();
        System.out.println(newApplication.getCitizenId());
        try {
            NewAadharApplicationObject res = aadharApplicationServices.newApplication(newApplication);

            if(res != null) {
                List<UserObject> user =  userServices.findByCitizenId(newApplication.getCitizenId());
                if(user.size() != 0) {
                    user.get(0).setAadharApplied(true);
                    user.get(0).setPassportId(newApplication.getPassportId());
                    userServices.updateUser(user.get(0));   // updating the user entity that applied for aadhar
                }
                return new ResponseEntity<Object>(res, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<Object>("Facing some issue while trying to submit your new Application, please try after some time.", HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<Object>("There is some issue, may be you have already applied for aadhar.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateAadhar")
    public ResponseEntity<Object> updateAadhar(@RequestBody NewAadharApplicationObject newApplication){
        newApplication.setDateOfTheApplication();
        System.out.println(newApplication.getCitizenId());
        try {
            NewAadharApplicationObject res = aadharApplicationServices.newApplication(newApplication);

            if(res != null) {
                List<UserObject> user =  userServices.findByCitizenId(newApplication.getCitizenId());
                if(user.size() != 0) {
                    user.get(0).setAadharApplied(true);
                    userServices.updateUser(user.get(0));   // updating the user entity that applied for aadhar
                }
                return new ResponseEntity<Object>(res, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<Object>("Facing some issue while trying to submit your update Application, please try after some time.", HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<Object>("There is some issue, may be you have already applied for aadhar.", HttpStatus.BAD_REQUEST);
        }
    }


}
