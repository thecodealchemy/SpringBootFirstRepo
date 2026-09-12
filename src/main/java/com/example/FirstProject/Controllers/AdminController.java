package com.example.FirstProject.Controllers;

import com.example.FirstProject.Cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @Autowired
    private AppCache appCache;

    @GetMapping("cache_reset")
    public ResponseEntity<String> clearCache(){
        appCache.init();
        return new ResponseEntity<>("Cache is Reset", HttpStatus.OK);
    }
}
