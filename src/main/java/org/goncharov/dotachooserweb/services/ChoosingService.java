package org.goncharov.dotachooserweb.services;

import org.goncharov.dotachooserweb.domain.Hero;
import org.goncharov.dotachooserweb.repositories.HeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

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
    public List<Integer> sumList(List<Integer> enemyTeam, List<Integer> myTeam){
        return Stream.concat(enemyTeam.stream(), myTeam.stream()).toList();
    }
}
