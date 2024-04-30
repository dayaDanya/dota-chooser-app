package org.goncharov.dotachooserweb.dto;

import org.goncharov.dotachooserweb.domain.Category;
import org.goncharov.dotachooserweb.domain.Hero;

public class HeroesDto {
    private int id;
    private String name;
    private Category category;
    private byte[] image;

    public HeroesDto(Hero hero, byte[] image) {
        this.id = hero.getId();
        this.name = hero.getName();
        this.category = hero.getCategory();
        this.image = image;
    }
}
