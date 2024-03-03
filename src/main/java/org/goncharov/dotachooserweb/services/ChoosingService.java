package org.goncharov.dotachooserweb.services;

import jakarta.annotation.PostConstruct;
import org.goncharov.dotachooserweb.domain.Hero;
import org.goncharov.dotachooserweb.repositories.HeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChoosingService {

    private final HeroRepo heroRepo;

    @Autowired
    public ChoosingService(HeroRepo heroRepo) {
        this.heroRepo = heroRepo;
    }


    public List<Hero> findAll() {
        return heroRepo.findAll();
    }
    public Hero findById(int id){
        return heroRepo.findById(id).orElseThrow(() -> new RuntimeException("Hero with id = : " + id + " not found!"));
    }
}
