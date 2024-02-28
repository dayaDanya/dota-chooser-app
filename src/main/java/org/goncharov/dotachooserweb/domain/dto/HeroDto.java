package org.goncharov.dotachooserweb.domain.dto;

public class HeroDto {

    private int number;

    public HeroDto(int number) {
        this.number = number;
    }

    public HeroDto() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
