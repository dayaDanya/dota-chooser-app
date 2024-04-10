package org.goncharov.dotachooserweb.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "hero")
public class Hero {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;
    @Column(name = "image_link")
    private String imageLink;

    public Hero(String name, Category category, String imageLink) {
        this.name = name;
        this.category = category;
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
