package com.example.FirstProject.Service;

import com.example.FirstProject.Pojo.WeatherApiResponse;
import com.example.FirstProject.Pojo.WeatherRequest;
import com.example.FirstProject.Pojo.WeatherRequest.Location;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherService {

    private final String BaseUrl = "http://api.weatherapi.com/v1/current.json?key=API_KEY&aqi=yes";
    @Value("${weather_service_api_key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherApiResponse getWeather(String city){
        String finalUrl = BaseUrl.replace("API_KEY", apiKey) + "&q=" + city;
        ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherApiResponse.class);
        if(response.getStatusCode().is2xxSuccessful()){
            log.info("Weather Response Status Code : {} and Body: {}", response.getStatusCode(), response.getBody());
            return response.getBody();
        }
        else{
            log.error("Weather Response Status Code : {} and Message: {}", response.getStatusCode(),  response.getBody());
            return null;
        }
    }


    public void showWeatherInBulk(String[] cities){
        String finalUrl = BaseUrl.replace("API_KEY", apiKey) + "&q=bulk";
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {
            locations.add(
                    new Location(
                            cities[i],
                            "city-" + i
                    )
            );
        }

        WeatherRequest requestBody = new WeatherRequest(locations);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WeatherRequest> entity =
                new HttpEntity<>(requestBody, headers);
        log.info("Weather Request Body : {}", entity.getBody());
        ResponseEntity<String> response = restTemplate.exchange(finalUrl, HttpMethod.POST, entity, String.class);
        log.info("Weather Response Status Code : {} and Body: {}", response.getStatusCode(), response.getBody());
    }
}
