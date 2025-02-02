
## Pokemon SDK 

This project includes an SDK for use against the Pokemon API @ https://pokeapi.co/api/v2/
The SDK allows users to query the following 4 APIs: 

 - GET `https://pokeapi.co/api/v2/pokemon/{id or name}/`
 - GET `https://pokeapi.co/api/v2/pokemon`
 - GET `https://pokeapi.co/api/v2/generation/{id or name}/`
 - GET `https://pokeapi.co/api/v2/generation`

 A pagination class for Pokemon results is included as well.

## Requirments

- Java JDK 21
- Apache Maven 3.9.9


## How to use

Begin by creating a connection to the PokeAPI server.

```
            PokeSdk pokeSdk = new PokeSdk();
```

Feel free to review all public methods within `PokeSdk.java` located @ `src\main\java\com\pokesdk`
This will give you a feel for the available methods and their return types.

To get a specific Pokemon: 

```
                Pokemon Bulbasaur = pokeSdk.getPokemon("bulbasaur");
```
or 

```
                Pokemon Bulbasaur = pokeSdk.getPokemon("1");
```

You may use type-completion to get your Pokemons attributes. 
The attributes order/style is mapped to the JSON reponse that the API would return. 
For example, to print all of Bulbasaur's abilities, simply:

```
            for (int i = 0; i < bulbasaur.getAbilities().size(); i++){
                System.out.println("Buba Ability: " + bulbasaur.getAbilities().get(i).getAbility().getName());
            }
```

The `/pokemon/` API returns many pokemon results so Pagination is supported for this API. 
To use pagination, create a PokePages object: 
```
            PokePages pokePages = new PokePages(pokeSdk);
```

View the results of the current page: 
```
            Pokemons currentPageData = pokePages.getCurrentPageData();

            for (int i = 0; i < currentPageData.getResults().size(); i++){
                System.out.println("Page results: " + currPageData.getResults().get(i).getName());
            }
```

You may also move to the next or previous pages:
Note, `.next()` and `.previous()` will return an object of type `Pokemons` which is a page consisting of up to 20 Pokemons: 

```
            pokePages.next()
```

```
            pokePages.previous()
```

To loop through the pages, simply: 

```
            while (pokePages.hasNext()) {
                currentPageData = pokePages.next();
            }
```


## Dependancies 

* jsonschema2pojo

    This project uses [jsonschema2pojo](https://github.com/joelittlejohn/jsonschema2pojo/) to covert JSON Schema objects to POJO's. This is used is to create an easy to navigate/stricly-typed PokeAPI SDK.

    Currently, Schemas have been created for 4 API's within the PokeApi. 

    To create your own Schema, please follow these directions: 

    1. Navigate to one of the PokeApi endpoints and copy the JSON response.
    2. Navigate to any JSON to JSON Schema site or feel free to use a library for this purpose if you'd like.
        For the existing schemas, this site was used to generate them: [transform.tools/json-to-json-schema](https://github.com/joelittlejohn/jsonschema2pojo/wiki/Getting-Started)
    3. Copy the generated schema into a new file within this project.
        Place these files within `src/main/java/com/pokesdk/resources/`
    4. Now, run `mvn package`. Upon comletion, you will find your generated POJOs at: `target\generated-sources\jsonschema2pojo`
    5. You may now import a class from the generated sources and use it as needed within your main project.

## Useful pages:
  * [Documentation for the Maven plugin](https://joelittlejohn.github.io/jsonschema2pojo/site/1.2.2/generate-mojo.html)
