package com.backend.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Abhinav Bhardwaj
 * Date: 12/10/22
 * Time: 15:02
 */


@Repository
public interface UserJPARepo extends JpaRepository<UserObject, Integer> {
    //List<UserObject> findByName(String Username);
    List<UserObject> findByUserId(int userId);
    List<UserObject> findByCitizenId(String citizenId);
}