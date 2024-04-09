package org.goncharov.dotachooserweb.dto;

import java.util.List;

public class HeroesDto {
     private List<Integer> myTeam;
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
