package org.goncharov.dotachooserweb.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class HeroesDto {
    @NotEmpty(message = "\"myTeam\" list must not be empty")
    @Size(min = 4, max = 4, message = "\"myTeam\" list's size has to equal 4")
    private List<Integer> myTeam;
    @NotEmpty(message = "\"enemyTeam\" list must not be empty")
    @Size(min = 4, max = 4, message = "\"enemyTeam\" list's size has to equal 4")
    private List<Integer> enemyTeam;

    public List<Integer> getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(List<Integer> myTeam) {
        this.myTeam = myTeam;
    }

    public List<Integer> getEnemyTeam() {
        return enemyTeam;
    }

    public void setEnemyTeam(List<Integer> enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    @Override
    public String toString() {
        return "HeroesDto{" +
                "myTeam=" + myTeam +
                ", enemyTeam=" + enemyTeam +
                '}';
    }
}
