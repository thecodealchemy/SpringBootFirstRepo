package com.example.FirstProject.Pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherApiResponse {

    public Current current;

    @Getter
    @Setter
    @ToString
    public static class AirQuality {
        public double pm2_5;
        public double pm10;
    }

    @Getter
    @Setter@ToString
    public static class Current {
        public String last_updated;
        public double temp_c;
        public double temp_f;
        public AirQuality air_quality;
    }
}


