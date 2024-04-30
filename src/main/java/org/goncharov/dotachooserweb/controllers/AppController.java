package org.goncharov.dotachooserweb.controllers;

import com.dotachooser.grpc.Choosing;
import com.dotachooser.grpc.ChoosingServiceGrpc;
import io.grpc.ManagedChannel;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.goncharov.dotachooserweb.domain.Hero;
import org.goncharov.dotachooserweb.dto.HeroesToChooseDto;
import org.goncharov.dotachooserweb.services.ChoosingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final ChoosingService choosingService;

    private final ManagedChannel managedChannel;

    private ChoosingServiceGrpc.ChoosingServiceBlockingStub stub;

    @Autowired
    public AppController(ChoosingService choosingService, ManagedChannel managedChannel) {
        this.choosingService = choosingService;
        this.managedChannel = managedChannel;
    }

    @PostConstruct
    private void initializeStub() {
        stub = ChoosingServiceGrpc.newBlockingStub(managedChannel);
    }

    @GetMapping("/heroes")
    public ResponseEntity<?> getHeroes(){
        try {
            return ResponseEntity.ok(choosingService.findAll());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError()
                    .build();
        }
    }

    @PostMapping("/choosing")
    public ResponseEntity<?> choose(@Valid @RequestBody HeroesToChooseDto dto) {
        System.out.println("Получили dto: " + dto.toString());
        var response = stub.choosing(Choosing.Heroes.newBuilder()
                .addAllList(choosingService.sumList(dto.getEnemyTeam(), dto.getMyTeam()))
                .build());
        try {
            Hero hero = choosingService.findById(response.getChoice());
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
