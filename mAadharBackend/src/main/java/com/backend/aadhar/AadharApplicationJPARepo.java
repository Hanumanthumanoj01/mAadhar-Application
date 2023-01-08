package com.backend.aadhar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 13/10/22
 * Time: 10:23
 */

@Repository
public interface AadharApplicationJPARepo extends JpaRepository<NewAadharApplicationObject, Integer> {
    List<NewAadharApplicationObject> findByApplicationId(int applicationId);
    List<NewAadharApplicationObject> findAll();
}
