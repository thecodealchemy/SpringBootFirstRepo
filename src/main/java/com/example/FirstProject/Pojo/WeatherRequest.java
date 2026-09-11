package com.example.FirstProject.Pojo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class WeatherRequest {

    private List<Location> locations;

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class Location {
        private String q;
        private String custom_id;
    }
}


