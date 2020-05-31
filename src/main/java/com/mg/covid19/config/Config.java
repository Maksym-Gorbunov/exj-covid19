package com.mg.covid19.config;

import com.mg.covid19.model.Mapper;
import com.mg.covid19.rest.helper.ResponseTransformer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Mapper myModelMapper() {
        return new Mapper();
    }

    //@LoadBalanced   // need to get all names of registered clients from Eureka server, service name instead of 'localhost'
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ResponseTransformer responseTransformer() {
        return new ResponseTransformer();
    }

}
