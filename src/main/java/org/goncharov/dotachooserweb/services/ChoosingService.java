package org.goncharov.dotachooserweb.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.goncharov.dotachooserweb.domain.Hero;
import org.goncharov.dotachooserweb.dto.HeroesDto;
import org.goncharov.dotachooserweb.repositories.HeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ChoosingService {

    private final HeroRepo heroRepo;

    @Autowired
    public ChoosingService(HeroRepo heroRepo) {
        this.heroRepo = heroRepo;
    }


    public Map<Integer, HeroesDto> findAll() {
        try {
            var heroes = heroRepo.findAll();
            Map<Integer, HeroesDto> heroesWithImg = new HashMap<>();
            for (var hero : heroes) {
                var curImg = getImg(hero.getImageLink());
                heroesWithImg.put(hero.getId(), new HeroesDto(hero, curImg));
            }
            return heroesWithImg;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Hero findById(int id) {
        return heroRepo.findById(id).orElseThrow(() -> new RuntimeException("Hero with id = : " + id + " not found!"));
    }

    public List<Integer> sumList(List<Integer> enemyTeam, List<Integer> myTeam) {
        return Stream.concat(enemyTeam.stream(), myTeam.stream()).toList();
    }

    private byte[] getImg(String imagePath) {
        try {
            return Files.readAllBytes(Path.of(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
