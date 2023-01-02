package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.service.appUserService.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@PropertySource("classpath:application.properties")
public class AppUserController {
    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/account/{id}")
    public ResponseEntity<AppUser> getUserByAccountId(@PathVariable Long id) {
        AppUser appUser = appUserService.findByAccount(id);
        if (appUser == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appUser);
    }
}
