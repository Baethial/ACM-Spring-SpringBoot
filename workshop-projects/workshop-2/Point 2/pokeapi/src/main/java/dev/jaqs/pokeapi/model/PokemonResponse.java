package dev.jaqs.pokeapi.model;

import java.util.List;

/**
 * Mapeo parcial del JSON de https://pokeapi.co/api/v2/pokemon/{name}
 * Campos requeridos por el enunciado: name, id, weight, height, abilities
 */
public class PokemonResponse {

    private String name;
    private int id;
    private int weight; // viene en hectograms
    private int height; // decimeters

    private List<AbilitySlot> abilities;

    public String getName() { return name; }
    public int getId() { return id; }
    public int getWeight() { return weight; }
    public int getHeight() { return height; }
    public List<AbilitySlot> getAbilities() { return abilities; }

    // ---- clases anidadas para el JSON de abilities ----
    public static class AbilitySlot {
        private NamedResource ability;

        public NamedResource getAbility() { return ability; }
    }

    public static class NamedResource {
        private String name;
        private String url;

        public String getName() { return name; }
        public String getUrl() { return url; }
    }
}
