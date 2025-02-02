package com.pokesdk;
import java.io.IOException;
import com.poke.Pokemons;

public class PokePages {
    private int offset = 20;
    private int limit = 20;
    private int currentPageOffset = 0;
    private boolean nextPageExists = false;
    private boolean previousPageExists = false;
    private Pokemons currentPageData;
    PokeSdk connection;

    PokePages(PokeSdk connection) {
        this.connection = connection;
        try {
            currentPageData = connection.getPokemons();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if(currentPageData.getNext() == null)
            nextPageExists = false;
        else
            nextPageExists = true;
    }

    public Pokemons getCurrentPageData() {
        return currentPageData;
    }

    public Boolean hasNext() {
        return nextPageExists;
    }

    public Boolean hasPrevious() {
        return previousPageExists;
    }

    public Pokemons next() {
        try {
            if (currentPageData.getNext() == null) {
                nextPageExists = false;
                return null;
            } else {
                currentPageData = connection.getPokemons(currentPageOffset += offset, limit);
                previousPageExists = true; 
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return currentPageData;
    }

    public Pokemons previous() throws Exception {
        try {
            if (currentPageData.getPrevious() == null) {
                previousPageExists = false;
                return null;
            } else {
                currentPageData = connection.getPokemons(currentPageOffset -= offset, limit);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return currentPageData;
    }
}