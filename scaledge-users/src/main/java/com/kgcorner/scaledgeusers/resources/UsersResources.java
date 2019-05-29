package com.kgcorner.scaledgeusers.resources;

/*
Description : Users Resource class, which defines all available end points
Author: kumar
Created on : 27/5/19
*/

import com.kgcorner.scaledge.util.Constants;
import com.kgcorner.scaledge.util.Strings;
import com.kgcorner.scaledgeusers.dao.entity.User;
import com.kgcorner.scaledgeusers.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersResources extends UsersExceptionAware{

    @Autowired
    private UsersService service;

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        if(Strings.isNullOrEmpty(userId)) {
            throw new IllegalArgumentException("Invalid UserId");
        }
        return service.getUser(userId);
    }

    @GetMapping(value = "/health", produces = Constants.PRODUCES_APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    String getHealth() {
        return "Healthy";
    }

    /**
     * Registers given user
     * @param user
     * @return registered user
     */
    @PostMapping("/users")
    public  User registerUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    @PutMapping("/users/{followerId}/follows/{followingId}")
    public void followUser(@PathVariable("followerId") String followerId
        , @PathVariable("followingId") String followingId) {
        service.followUser(followerId, followingId);
    }

}