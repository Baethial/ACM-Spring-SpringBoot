package dev.jaqs.pokeapi.controller;

import dev.jaqs.pokeapi.dto.SlimPokemonDto;
import dev.jaqs.pokeapi.model.PokemonResponse;
import dev.jaqs.pokeapi.service.PokemonService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Endpoints:
 * - GET /api/pokemon/{name}                -> JSON con name, id, weight, height, abilities (mapeo parcial)
 * - GET /api/pokemon/{name}?view=slim      -> versión slim (nombre, peso, habilidades)
 * - GET /api/pokemon/{name}/basic          -> alias directo de la vista slim
 */
@RestController
@RequestMapping(value = "/api/pokemon", produces = MediaType.APPLICATION_JSON_VALUE)
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/{name}")
    public Mono<?> getPokemon(
            @PathVariable @NotBlank String name,
            @RequestParam(name = "view", required = false) String view
    ) {
        if ("slim".equalsIgnoreCase(view)) {
            return service.getSlimPokemon(name);
        }
        return service.getRawPokemon(name);
    }

    @GetMapping("/{name}/basic")
    public Mono<SlimPokemonDto> getBasic(@PathVariable @NotBlank String name) {
        return service.getSlimPokemon(name);
    }
}
