package org.goncharov.dotachooserweb.controllers;

import org.goncharov.dotachooserweb.domain.Hero;
import org.goncharov.dotachooserweb.services.ChoosingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class AppController {

    private final RestTemplate restTemplate;
    private final ChoosingService choosingService;
    @Autowired
    public AppController(RestTemplate restTemplate, ChoosingService choosingService) {
        this.restTemplate = restTemplate;
        this.choosingService = choosingService;
    }

    @GetMapping("/main")
    public ResponseEntity<?> main(){
        return new ResponseEntity<>(choosingService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/choose")
    public ResponseEntity<?> choose(@RequestParam("choice") String choice){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8000")
                .path("/ml")
                .queryParam("choice", choice)
                .build();
        String url = uriComponents.toUriString();
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            Hero hero = choosingService.findById(Integer.parseInt(exchange.getBody()));
            return new ResponseEntity<>(hero, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
