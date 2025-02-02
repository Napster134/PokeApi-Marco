package com.pokesdk;
import java.net.http.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poke.Pokemon;
import com.poke.Pokemons;
import com.gen.Generation;
import com.gen.Generations;

public class PokeSdk {
    String hostApi = "https://pokeapi.co/api/v2/";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    // PUBLIC METHODS

    public Pokemon getPokemon(String idOrName) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(queryApi("pokemon", idOrName), Pokemon.class);
    }

    public Pokemons getPokemons() throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(queryApi("pokemon"), Pokemons.class);
    }

    public Pokemons getPokemons(int offset, int limit) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        String filter = "pokemon/" + "?offset="+ offset +"&limit="+ limit;
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(queryApi(filter), Pokemons.class);
    }

    public Generation getGeneration(String idOrName) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(queryApi("generation", idOrName), Generation.class);
    }

    public Generations getGenerations() throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(queryApi("generation"), Generations.class);
    }

    // END PUBLIC

    private String queryApi(String endpoint, String idOrName) throws IOException, InterruptedException {
        try {
            endpoint = hostApi + "/" + endpoint + "/" + Integer.parseInt(idOrName);
        } catch (NumberFormatException e) {
            endpoint = hostApi + "/" + endpoint + "/" + idOrName;
        }
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .GET()
            .build();
        HttpResponse<String> response;
        response = httpClient.send(request, BodyHandlers.ofString());
        return response.body();
    }

    private String queryApi(String endpoint) throws IOException, InterruptedException {
        return queryApi(endpoint, "");
    }
}