package org.goncharov.dotachooserweb.repositories;

import org.goncharov.dotachooserweb.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepo extends JpaRepository<Hero, Integer> {
}
