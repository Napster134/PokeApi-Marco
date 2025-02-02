package com.pokesdk;
import com.poke.Pokemon;
import com.poke.Pokemons;
import com.gen.Generation;

public class App 
{
    public static void main( String[] args )
    {        
        try {
            PokeSdk pokeSdk = new PokeSdk();

            Pokemon bulbasaur = pokeSdk.getPokemon("bulbasaur");
            System.out.println("poke = " + bulbasaur.getName());
            System.out.println("poke = " + bulbasaur.getLocationAreaEncounters());
            System.out.println("poke = " + bulbasaur.getBaseExperience());
            System.out.println("poke = " + bulbasaur.getAbilities().get(0).getAbility().getName());
            System.out.println("poke = " + bulbasaur.getAbilities().get(1).getAbility().getName());

            for (int i = 0; i < bulbasaur.getAbilities().size(); i++){
                System.out.println("Buba Ability: " + bulbasaur.getAbilities().get(i).getAbility().getName());
            }


            Generation gen = pokeSdk.getGeneration("generation-i");
            System.out.println("gen = " + gen.getName());
            System.out.println("gen = " + gen.getMainRegion().getName());
            System.out.println("gen = " + gen.getAbilities().size());
            System.out.println("gen = " + gen.getMoves().get(0).getName());

            PokePages pokePages = new PokePages(pokeSdk);
            Pokemons currPageData = pokePages.getCurrentPageData();
            
            while (pokePages.hasNext()) {
                System.out.println(currPageData.getResults().get(0).getName());
                currPageData = pokePages.next();
            }
            if(pokePages.hasPrevious()) {
                currPageData = pokePages.previous();
                System.out.println(currPageData.getResults().get(0).getName());
            }
            while(pokePages.hasPrevious()) {
                System.out.println(currPageData.getResults().get(0).getName());
                currPageData = pokePages.previous();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
