package org.goncharov.dotachooserweb.controllers;

import org.goncharov.dotachooserweb.services.ChoosingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class AppController {

    private final RestTemplate restTemplate;
    private final ChoosingService choosingService;
    @Autowired
    public AppController(RestTemplate restTemplate, ChoosingService choosingService) {
        this.restTemplate = restTemplate;
        this.choosingService = choosingService;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("map", choosingService.findAll());
        return "index";
    }
    @PostMapping("/choose")
    public String choose(@ModelAttribute("nums") List<Integer> nums){
        StringBuilder str = new StringBuilder();
        for (int i : nums)
            str.append(i).append(" ");
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8081")
                .path("/ml")
                .queryParam("str", str.toString())
                .build();
        String url = uriComponents.toUriString();

        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}
