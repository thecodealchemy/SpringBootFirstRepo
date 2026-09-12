package com.example.FirstProject.Controllers;

import com.example.FirstProject.Entity.User;
import com.example.FirstProject.Pojo.WeatherApiResponse;
import com.example.FirstProject.Service.UserService;
import com.example.FirstProject.Service.WeatherService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        try {
            log.info("Add user called for: {}", user.getUsername());
            User savedUser = userService.addNewUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{username}/greet")
    public String greetUser(@PathVariable String username){
        String greeting = "Hey " + username;
        WeatherApiResponse response = weatherService.getWeather("Mumbai");
        if(response!=null){
            greeting = greeting + " " + response.getCurrent().air_quality;
        }
        return greeting;
    }

    @GetMapping("showCitiesStatus")
    public String showCities(){
        return weatherService.showWeatherInBulk(new String[]{"Mumbai", "Delhi", "Calcutta"});
    }

    @GetMapping("{username}/emailOpt")
    public String emailOpt(@PathVariable String username ,@RequestBody boolean flag){
        userService.changeEmailOptStatus(username, flag);
        return "Changed to :" + flag;
    }
}
