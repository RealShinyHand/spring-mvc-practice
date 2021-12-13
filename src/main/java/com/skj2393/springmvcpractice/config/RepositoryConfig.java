package com.skj2393.springmvcpractice.config;

import com.skj2393.springmvcpractice.controller.repository.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public  EventRepository eventRepository(){
        return new EventRepository() {};
    }
}
