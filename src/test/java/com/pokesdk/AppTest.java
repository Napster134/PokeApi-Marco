package com.pokesdk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gen.Generation;
import com.poke.Pokemon;
import com.poke.Pokemons;

class PokeSdkTests {

    @Test
    void validateBulbasaurAttributesViaName() throws Exception {
        PokeSdk pokeSdk = new PokeSdk();
        Pokemon bulbasaur = pokeSdk.getPokemon("bulbasaur");
        assertEquals(bulbasaur.getName(), "bulbasaur");
        assertEquals(bulbasaur.getLocationAreaEncounters(), "https://pokeapi.co/api/v2/pokemon/1/encounters");
        assertEquals(bulbasaur.getBaseExperience(), 64.0);
        assertEquals(bulbasaur.getAbilities().get(0).getAbility().getName(), "overgrow");
        assertEquals(bulbasaur.getAbilities().get(1).getAbility().getName(), "chlorophyll");
    }

    @Test
    void validateBulbasaurAttributesViaId() throws Exception {
        PokeSdk pokeSdk = new PokeSdk();
        Pokemon bulbasaur = pokeSdk.getPokemon("1");
        assertEquals(bulbasaur.getName(), "bulbasaur");
        assertEquals(bulbasaur.getLocationAreaEncounters(), "https://pokeapi.co/api/v2/pokemon/1/encounters");
        assertEquals(bulbasaur.getBaseExperience(), 64.0);
        assertEquals(bulbasaur.getAbilities().get(0).getAbility().getName(), "overgrow");
        assertEquals(bulbasaur.getAbilities().get(1).getAbility().getName(), "chlorophyll");
    }

    @Test
    void validategen2AttributesViaName() throws Exception {
        PokeSdk pokeSdk = new PokeSdk();
        Generation gen = pokeSdk.getGeneration("2");
        assertEquals(gen.getName(), "generation-ii");
        assertEquals(gen.getMainRegion().getName(), "johto");
        assertEquals(gen.getAbilities().size(), 0);
        assertEquals(gen.getMoves().get(0).getName(), "sketch");
    }

    @Test
    void validatePagination() throws Exception {
        PokeSdk pokeSdk = new PokeSdk();
        PokePages pokePages = new PokePages(pokeSdk);
        Pokemons currPageData = pokePages.getCurrentPageData();
        while (pokePages.hasNext()) {
            currPageData = pokePages.next();
        }
        currPageData = pokePages.previous();
        String lastPageResult = currPageData.getResults().get(0).getName();
        assertEquals(lastPageResult, "palafin-hero");

        if (pokePages.hasPrevious()) {
            currPageData = pokePages.previous();
            assertEquals(currPageData.getResults().get(0).getName(), "samurott-hisui");
        }
    }
}