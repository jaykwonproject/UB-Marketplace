package com.ubmarketplace.app.controller;

import com.ubmarketplace.app.dto.ProfileUpdateResponse;
import com.ubmarketplace.app.dto.RegisterRequest;
import com.ubmarketplace.app.dto.ProfileUpdateRequest;
import com.ubmarketplace.app.dto.RegisterResponse;
import com.ubmarketplace.app.manager.UserManager;
import com.ubmarketplace.app.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class ProfileUpdateController {
    final UserManager userManager;

    @Autowired
    public ProfileUpdateController(UserManager userManager){
        this.userManager = userManager;
    }

    @RequestMapping(value = "/api/profileupdate", method = RequestMethod.POST)
    private ProfileUpdateResponse received(@RequestBody ProfileUpdateRequest profileupdateRequest){
        log.info(String.format("Recovering profile update request from %s", profileupdateRequest.getUsername()));


        User update_user = userManager.updateUser(profileupdateRequest.getUsername(), profileupdateRequest.getPassword(), profileupdateRequest.getDisplayname());

        return ProfileUpdateResponse.builder().user(update_user).build();
    }
}