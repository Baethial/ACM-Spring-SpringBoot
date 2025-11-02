package dev.jaqs.pokeapi.dto;

import java.util.List;

public class SlimPokemonDto {
    private String name;
    private int weight;
    private List<String> abilities;

    public SlimPokemonDto(String name, int weight, List<String> abilities) {
        this.name = name;
        this.weight = weight;
        this.abilities = abilities;
    }

    public String getName() { return name; }
    public int getWeight() { return weight; }
    public List<String> getAbilities() { return abilities; }
}
