package org.goncharov.dotachooserweb.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChoosingService {
    private final String all = "Abaddon, Alchemist, Ancient Apparition, Anti-Mage, Arc Warden, Axe, Bane, Batrider, Beastmaster, Bloodseeker, Bounty Hunter, Brewmaster, Bristleback, Broodmother, Centaur Warrunner, Chaos Knight, Chen, Clinkz, Clockwerk, Crystal Maiden, Dark Seer, Dark Willow, Dawnbreaker, Dazzle, Death Prophet, Disruptor, Doom, Dragon Knight, Drow Ranger, Earth Spirit, Earthshaker, Elder Titan, Ember Spirit, Enchantress, Enigma, Faceless Void, Grimstroke, Gyrocopter, Hoodwink, Huskar, Invoker, Io, Jakiro, Juggernaut, Keeper of the Light, Kunkka, Legion Commander, Leshrac, Lich, Lifestealer, Lina, Lion, Lone Druid, Luna, Lycan, Magnus, Marci, Mars, Medusa, Meepo, Mirana, Monkey King, Morphling, Muerta, Naga Siren, Nature's Prophet, Necrophos, Night Stalker, Nyx Assassin, Ogre Magi, Omniknight, Oracle, Outworld Destroyer, Pangolier, Phantom Assassin, Phantom Lancer, Phoenix, Primal Beast, Puck, Pudge, Pugna, Queen of Pain, Razor, Riki, Rubick, Sand King, Shadow Demon, Shadow Fiend, Shadow Shaman, Silencer, Skywrath Mage, Slardar, Slark, Snapfire, Sniper, Spectre, Spirit Breaker, Storm Spirit, Sven, Techies, Templar Assassin, Terrorblade, Tidehunter, Timbersaw, Tinker, Tiny, Treant Protector, Troll Warlord, Tusk, Underlord, Undying, Ursa, Vengeful Spirit, Venomancer, Viper, Visage, Void Spirit, Warlock, Weaver, Windranger, Winter Wyvern, Witch Doctor, Wraith King, Zeus";

    private final Map<String, Integer> names = new HashMap<>();

    private final Map<Integer, String> nums = new HashMap<>();

    //метод пост конструкт, добавляющий в map

    @PostConstruct
    private void initialize(){
        String[] array = all.split(", ");
        for(int i = 0; i < array.length ; i++){
            names.put(array[i], i+1);
            nums.put(i+1, array[i]);
        }
    }

    public Map<Integer, String> findAll(){
        return nums;
    }
    // метод переводящий из чисел в имена
    public String toName(int num){
        return nums.get(num);
    }

    // метод переводящий из имен в числа
    public int toNum(String name){
        return names.get(name);
    }
}
