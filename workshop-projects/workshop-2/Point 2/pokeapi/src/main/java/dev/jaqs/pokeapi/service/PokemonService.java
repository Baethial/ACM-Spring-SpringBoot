package dev.jaqs.pokeapi.service;

import dev.jaqs.pokeapi.dto.SlimPokemonDto;
import dev.jaqs.pokeapi.model.PokemonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final WebClient pokeWebClient;

    public PokemonService(WebClient pokeWebClient) {
        this.pokeWebClient = pokeWebClient;
    }

    public Mono<PokemonResponse> getRawPokemon(String name) {
        return pokeWebClient.get()
                .uri(uri -> uri.path("/pokemon/{name}").build(name.toLowerCase()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        r -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon no encontrado")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        r -> Mono.error(new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Error en PokeAPI")))
                .bodyToMono(PokemonResponse.class);
    }

    public Mono<SlimPokemonDto> getSlimPokemon(String name) {
        return getRawPokemon(name)
                .map(p -> new SlimPokemonDto(
                        p.getName(),
                        p.getWeight(),
                        p.getAbilities().stream()
                                .map(a -> a.getAbility().getName())
                                .collect(Collectors.toList())
                ));
    }
}
