package com.pokesdk;

import com.poke.Pokemon;
import com.poke.Pokemons;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gen.Generation;

public class App {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, InterruptedException {

        // Feel free to give the API a try. Here's some sample code:
        PokeSdk pokeSdk = new PokeSdk();


        // SEE POKEMON ABILITIES
        Pokemon bulbasaur = pokeSdk.getPokemon("bulbasaur");
        for (int i = 0; i < bulbasaur.getAbilities().size(); i++) {
            System.out.println("Buba Ability: " + bulbasaur.getAbilities().get(i).getAbility().getName());
        }


        // SEE GENERATION DETAILS
        Generation gen = pokeSdk.getGeneration("generation-i");

        System.out.println(gen.getName());
        System.out.println(gen.getMainRegion().getName());
        System.out.println(gen.getAbilities().size());
        System.out.println(gen.getMoves().get(0).getName());


        // PAGINATION EXAMPLE
        PokePages pokePages = new PokePages(pokeSdk);
        Pokemons currentPage = pokePages.getCurrentPageData();
        while (pokePages.hasNext()) {
            currentPage = pokePages.next();
            System.out.println(currentPage.getResults().get(0).getName());
        }

    }
}
