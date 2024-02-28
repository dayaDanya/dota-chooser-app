package org.goncharov.dotachooserweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.goncharov.dotachooserweb.domain.dto.HeroDto;
import org.goncharov.dotachooserweb.services.ChoosingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class AppController {

    private final RestTemplate restTemplate;
    private final ChoosingService choosingService;

    private final ObjectMapper objectMapper;

    @Autowired
    public AppController(RestTemplate restTemplate, ChoosingService choosingService, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.choosingService = choosingService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("map", choosingService.findAll());
        return "main";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/choose")
    public String choose(@RequestParam("choice") String choice,
                         Model model) {
        System.out.println("вызвано, получили " + choice);
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8000")
                .path("/ml")
                .queryParam("choice", choice)
                .build();
        String url = uriComponents.toUriString();

        String response = restTemplate.getForObject(url, String.class);
        try {
            HeroDto dto =  objectMapper.readValue(response, HeroDto.class);
            model.addAttribute("hero", choosingService.toName(dto.getNumber()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "hero";
    }
}
