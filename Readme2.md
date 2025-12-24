# Inleiding

Je hebt in de vorige opdracht de architectuur van de VinylShop applicatie compleet gemaakt door een controller-service-repository patroon te implementeren.
Ook heb je de data gestructureerd, zodat je de communicatie met de buitenwereld en met de database gescheiden houdt. Optioneel hebt je zelfs interne modellen gemaakt.  
In deze opdracht ga je nog drie entiteiten aan de casus toevoegen, je gaat voor al deze entiteiten nog eens oefenen om de complete architectuur uit te werken en datamodellen te maken. 
Daarnaast ga je ook alle entiteiten met elkaar koppelen door middel van relaties. Ook de entiteiten die je al had, zul je daarvoor moeten aanpassen. 

Heb je de opdracht van vorige week niet gemaakt of niet af gekregen, dan kun je de [voorbeeld uitwerkingen](https://github.com/hogeschoolnovi/backend-springboot-vinylshop-modellen-uitwerkingen) clonen.

>Waarschuwing: Dit is een grote opdracht. 


# Opdrachtbeschrijving

Je gaat in deze opdracht verder werken aan de opdracht die je vorige week gemaakt hebt.
Je gaat de collectie entiteiten fors uitbreiden door de volgende entiteiten te maken die allen overerven van de `BaseEntity`: 
- AlbumEntity
- ArtistEntity
- StockEntity

Je gaat relaties leggen tussen deze entiteiten, zodat het er als volgt uit ziet:

![Klassendiagram.png](image/Klassendiagram.png)

Aleen de `AlbumCover` bewaren we nog even voor de volgende les.

# Randvoorwaarden

- Zorg dat je mappenstructuur aan de Maven voorwaarden voldoet
- Je mappen structuur heeft ten minste de packages: 
  - controllers
  - repositories
  - services
  - dtos
  - entities
- Je hebt vijf controllers:
    - GenreController
    - PublisherController
    - AlbumController
    - ArtistController
    - StockController
- Je hebt vijf services:
    - GenreService
    - PublisherService
    - AlbumService
    - ArtistService
    - StockService
- Je hebt vijf repositories:
    - GenreRepository
    - PublisherRepository
    - AlbumRepository
    - ArtistRepository
    - StockRepository
- Je hebt zes entiteiten:
    - BaseEntity
    - GenreEntity
    - PublisherEntity
    - AlbumEntity
    - ArtistEntity
    - StockEntity
- Je hebt voor alle vijf entiteiten ten minste twee DTO's en voor Album een extra DTO:
  - ...RequestDTO
  - ...ResponseDTO
  - AlbumExtendedResponseDTO
- Je hebt vier mappers
  - GenreDTOMapper
  - GenreEntityMapper
  - PublisherDTOMapper
  - PublisherEntityMapper
  
- Je hebt de juiste instellingen in je application.properties om de database connectie goed tot stand te brengen.
- Je hebt de juiste dependencies aan het project gekoppeld.
- Je hebt minimaal één record van alle entiteiten in je data.sql staan.
- Je hebt een export van Postman met up-to-date requests voor alle entiteiten, inclusief het koppelen van relaties.

# Stappenplan

## Stap 1 (Basis Entiteiten)

Maak eerst de kale `AlbumEntity`, `ArtistEntity` en `StockEntity` zonder relaties, die voegen we later toe.

De AlbumEntity ziet er (voor nu) zo uit: 
```java
@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {
    private String title;
    private int releaseYear;
//    Getters, setters en constructors
}
```

De ArtistEntity zo: 
```java
@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {
    private String name;
    private String biography;
//    Getters, setters en constructor
}
```

en de StockEntity zo:
```java
@Entity
@Table(name = "stock")
public class StockEntity extends BaseEntity {
    private String condition;
    private double price;
//    Getters, setters en constructors
}
```

## Stap 2 (Architectuur)

Voeg voor al deze entiteiten ook een Controller, Service en Repository toe. 

> Tip: het is makkelijk om onderaan de architectuur te beginnen, omdat een repository alleen een entity nodig heeft, maar een service heeft een repository nodig en een controller heeft een service nodig.

Je mag voor nu basis repositories aanmaken, zoals: 
```java
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

}
```

In stap 4 zul je hier wat extra functionaliteiten aan toevoegen, maar dat kan pas nadat je in stap 3 de relaties hebt gelegd.

Zorg dat elke controllers ten minste de volgende endpoints hebben: 
- GET (all)
- GET (one)
- POST
- PUT
- DELETE

Het is hierbij belangrijk dat je een `Stock` altijd aan een `Album` koppelt. Gebruik in je `StockController` daarom de volgende annotatie:
```java
@RequestMapping("/albums/{albumId}/stock")
```

Weet je niet meer hoe je de service, controller en repository maakt, kijk dan nog eens bij de vorige opdracht.

### Tips
Het is natuurlijk lastig om de controller en service te maken, zonder daar meteen de DTO en de mappers bij te maken. Het is daarom handig om alvast een basis DTO en mapper voor elke entiteit te maken. Let er daarbij op dat we in stap 5 pas de relaties aan die DTO's gaan toevoegen en dat natuurlijk ook in de mapper verwerkt moet worden.

Probeer met één functionaliteit te beginnen, bijvoorbeeld de "get-all". Van daaruit kun je verder bouwen met de volgende functionaliteit. 

Het lijkt veel werk, dat is het ook, maar als je het systematisch aanpakt, kun je het stap voor stap oplossen.

## Stap 3 (Relaties)
Je hebt al de basis opzet van de klassen gemaakt, maar deze opdracht gaat over relaties.

Voeg relaties toe in de entiteiten. Doe dat op de volgende manier:
- Een Album heeft 1 publisher, een Publisher heeft meerdere Albums
- Een Album heeft meerdere Stocks (noem deze variabele "stockItems", want "stock" is al meervoud), een Stock heeft 1 Album
- Een Album heeft meerdere Artists, een Artist heeft meerdere Albums
- Een Album heeft 1 Genre, een Genre heeft geen Albums (dit is een unidirectionele relatie)

Gebruik de `@OneToMany`, `@ManyToOne` en `@ManyToMany` annotaties. 
Vergeet ook niet de `mappedBy` op de juiste plekken in te vullen.

## Stap 4 (Repositories)


Nu je relaties hebt gelegd tussen entiteiten, mag je (sommige) repositories extra functionaliteiten geven boven op de standaard functionaliteiten van JPA:


**StockRepository**
Deze repository krijgt 3 extra functies:
```java
Optional<StockEntity> findByIdAndAlbumId(Long id, Long albumId);

void deleteByIdAndAlbumId(Long id, Long albumId);

List<StockEntity> findByAlbumId(Long albumId);
```
**ArtistRepository**
Deze repository krijgt 1 extra functie:
```java
    List<ArtistEntity> findArtistsByAlbumsId(@Param("albumId") Long albumId);

```

**AlbumRepository**
Deze repository krijgt drie extra functionaliteit:

```java
        List<AlbumEntity> findByStockItemsNotEmpty();

        List<AlbumEntity> findByStockItemsEmpty();
        
        List<AlbumEntity> findByGenre_Id(Long genreId);
```


## Stap 5 (Data)
Vul de data.sql met data voor je nieuwe entiteiten. 
Voeg daar ook relaties aan toe. 

Relaties kun je op 2 manieren in de data.sql zetten:
- Meteen in het initiële **INSERT** statement. Let er dan wel op dat je de **INSERT**s in de goede volgorde zet. Je kunt geen "banaan" aan een specifieke "fruitschaal" toevoegen als die "fruitschaal" nog niet bestaat.
- Je kunt er ook voor kiezen om de **INSERT** statements simpel te houden, zonder relaties, en de relaties met **UPDATE** statements later toe te voegen. De volgorde is hier minder belangrijk, omdat alle Primary Keys al gemaakt zijn.

Voorbeeld van een Stock met een Album: 
```sql
insert into stock (condition, price, album_id)
values ('good', 19.95, 1);
```

Voorbeeld van een update:
```sql
UPDATE Albums
SET genre_id = (
    SELECT id FROM genres WHERE name = 'House'
)
WHERE title = 'Hakkuhbar';

```

Start je applicatie op en kijk in je database of alle Foreign Keys en koppeltabellen goed gemaakt zijn en of alle dat er in staat.

> Wil je het dynamisch maken, probeer dan geen "hardcoded" foreign keys in je data.sql te zetten, maar zet daarvoor in de plaats een select statement tussen haakjes.

## Stap 6 (DTO's) 

Nu je de relaties in je entiteiten hebt, is je database in orde, maar je API nog niet. Wanneer je jouw API aanspreekt via PostMan, krijg je nog niet de data over relaties te zien. Niet alle entiteiten hoeven alle relaties te laten zien, dat zou al snel erg ingewikkeld worden.


### Request DTO's
- `AlbumRequestDto` bevat een verplicht "title"-veld dat 3 tot 100 tekens lang moet zijn en een "releaseYear" veld dat tussen 1877 en 2100 moet liggen. Het bevat verder een "genreId" en "publisherId". De relatie met Artist en Stock gaan we op een andere manier doen.
- `ArtistRequestDto` bevat een verplicht "name" veld en een optioneel "biography" veld.
- `StockRequestDto` bevat een optionele "condition" en verplichte "price" welk een positief komma getal moet zijn.

### Response DTO's
- `AlbumResponseDto` bevat naast de "id", "title" en "releaseYear", ook een complete Genre en Publisher.
- Maak daarnaast ook nog een `AlbumExtendedResponseDto` die van `AlbumResponseDto` overerft en daar een lijst van Stock aan toevoegt.
- `ArtistResponseDto` bevat "id", "name" en "biography".
- `StockResponseDto` bevat "id", "condition", "price".

> Let er op dat "entiteiten" alleen in de communicatie met de database thuis horen.

### Mappers 
Zorg dat de `AlbumDTOMapper` en de `ArtistDTOMapper` de relaties meenemen in de vertaling. 
Let op dat je deze vertaling alleen doet als er een relatie is, anders krijg je NullPointerExceptions, in het bijzonder bij de vertaling van Entity naar DTO.  
Als je een relatie moet mappen, maak dan gebruik van Dependency Injection om de desbetreffende mapper aan te spreken, dat scheelt je dubbele code.

> Tip: gebruik eventueel Jackson annotaties in je DTO's om het schoner te houden.

Maak ook een `AlbumExtendedDTOMapper`, die de `AlbumExtendedResponseDto` returned. Je kunt dit doen door gewoon een nieuwe Mapper te maken, maar idealiter doe je dit door AlbumDtoMapper te extenden, aangezien alleen de "mapToDto" methode nodig is en niet de "mapToEntity". 

Omdat `AlbumExtendedResponseDto` overerft van `AlbumResponseDto`, is het mogelijk om ook overerving toe te passen op de `AlbumExtendedDTOMapper`. Je overschrijft dan `mapToDto`. Daarin roep je als eerst `super.mapToDto` aan, maar die uitkomst "cast" je dan naar een `AlbumExtendedResponseDto`, zodat je er nog de stockItems aan toe kunt voegen: 
```java
AlbumExtendedResponseDTO result = (AlbumExtendedResponseDTO) super.mapToDto(model);
```

Alternatief kun je in de `AlbumDTOMapper` gebruik maken van een generic method die je voor de "mapToDto" gebruikt in zowel de `AlbumDtoMapper` als de `AlbumExtendedDtoMapper`: 
```java
public <D extends AlbumResponseDTO> D mapToDto(AlbumEntity model, D target);
```
In dat geval ziet de mapToDto er zo uit: 
```java
  @Override
    public AlbumResponseDTO mapToDto(AlbumEntity model) {
        return mapToDto(model, new AlbumResponseDTO());
    }
```

## Stap 7 (Functionaliteit)

Je hebt nu de Entiteiten en DTO's voor Album, Artist, Stock, Genre en Publisher. Je hebt ook Controllers, Services en Repositories voor al deze modellen, met basis "CRUD" functionaliteiten. 

Je zult merken dat je nu nog geen relaties kunt leggen via de API (alleen nog in de data.sql). Wanneer je nu een Album opvraagt via PostMan, zie je dat Genre en Publisher altijd null zijn. Daar gaan we verandering in brengen door de functionaliteit te updaten. 

### Genre en Publisher
De Genre en Publisher gaan we aan de Album toevoegen via de POST en/of PUT mapping. Daarmee maak je het mogelijk om direct bij het aanmaken van een nieuw Album de Genre en Publisher mee te geven, of het later toe te voegen (in twee stappen). 

Pas de `albumService.createAlbum` aan, zodat je vóór de `repository.save` eerst de GenreEntity en dan de PublisherEntity aan de AblbumEntity toevoegt. 

Hoe kom je aan die entities?

Daar kun je het best een helper methode voor maken, zoals: 
```java
 private GenreEntity getGenreEntity(long genreId){
    return genreRepository.findById(genreId).orElseThrow(() -> new EntityNotFoundException("genre " + genreId + " not found"));
}
```

Vergeet niet om eerst te controleren of de RequestDto wel een genreID of een publisherId bevat. Als dat niet zo is, dan hoef je namelijk niks te doen.

Implementeer deze functionaliteit op dezelfde manier in de `updateAlbum` functie. Aangezien PUT er van uit gaat dat je een compleet object update, hoe je hier niet te checken of de RequestDto wel bepaalde items heeft. Die verantwoordelijkheid ligt bij PUT bij de gebruiker.

### Stock

We hebben al een functionaliteit om een Stock aan een Album toe te voegen. Dat is namelijk de POST mapping in de StockController. 

We hebben nog geen functionaliteit om te zien welke Stocks een Album heeft. Laten we die maken door de `@GetMapping("/{id}")` mapping in `AlbumController` aan te passen naar `public ResponseEntity<AlbumExtendedResponseDTO> getAlbumById(@PathVariable Long id)`.  
Hier maak je dus gebruik van de `AlbumExtendedResponseDto` met die extra "List<Stock>". 

Om dit mogelijk te maken, zul je ook de `albumService.findAlbumById(id)` moeten aanpassen zodat deze gebruik maakt van de `AlbumExtendedDTOMapper`. 

### Artist

De artist gaan we aan Album toevoegen via een eigen API endpoint. 

Maak de volgende endpoints om de Artist te linken en te unlinken van een specifiek album.

```java
  @PostMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> linkArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumService.linkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> unlinkArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumService.unlinkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }
```

Zorg dat je dit ook in de service goed uitwerkt. Onthoud dat Album de eigenaar van deze relatie is.

### Extra 

Als laatste is het ook leuk om wat extra functionaliteit te maken om: 
- alle Artiesten van een Album op te halen
- alle Albums met Stock op te halen.

#### Alle Artiesten van een Album
Maak in de `AlbumController` een methode:
```java
@GetMapping("/{id}/artists")
public ResponseEntity<List<ArtistResponseDTO>> linkArtist(@PathVariable Long id){
    List<ArtistResponseDTO> artists = artistService.getArtistsForAlbum(id);
    return ResponseEntity.ok(artists);
}
```

Zoals je ziet maakt deze methode gebruik van de `ArtistService`, dus
maak de methode `artistService.getArtistsForAlbum`.

Deze service-methode maakt gebruik van de repository-methode `artistRepository.findArtistsByAlbumsId`. 

#### Alle Albums met een Stock
Het is voor je winkel personeel ook best handig om te weten welke albums op voorraad zijn en welke niet, dus laten we daar ook en methode voor maken. 

We gaan dit "makkelijk" oplossen door aan de bestaande "get all albums" methode in de `AlbumController` een optionele parameter toe te voegen: 
```java
@RequestParam(required = false) Boolean stock
```

Als die parameter niet `null` is, dan roepen we de `albumService.getAlbumsWithStock` aan.
Als dit parameter wel `null` is, dan roepen we gewoon `albumService.findAllAlbums` aan.

De `albumService.getAlbumsWithStock` returned een "List<AlbumResponseDTO>" en ontvangt een "Boolean stock" als input. Zorg dat de functie alle albums MET stock returned als de stock-boolean TRUE is en alle albums ZONDER stock returned als de stock-boolean FALSE is.


## Stap 8 (Delete)

Misschien heb je inmiddels al je API getest in PostMan en heb je al gemerkt dat je in je console een error krijgt zoals deze: 

```terminaloutput
org.postgresql.util.PSQLException: ERROR: update or delete on table "albums" violates foreign key constraint...
```

Deze error krijg je omdat er in een andere tabel nog een Foreign Key staat die naar het object verwijst dat jij probeert te verwijderen. Dat gaat niet. Je moet er dus voor zorgen dat die relatie eerst verbroken wordt, voordat je het object kunt verwijderen. We gaan per entiteit bekijken hoe je dat probleem kunt oplossen: 

### Publisher
De publisher heeft een relatie met Album waarbij Album de eigenaar van de relatie is.

Deze is makkelijk te verbreken, omdat Publisher en Album een bidirectionele relatie hebben (in PublisherEntity staat een List<AlbumEntity>). Je kunt:
- in `PublisherService.deletePublisher` eenvoudig de te-verwijderen-publisher uit de `PublisherRepository` halen 
- vervolgens door de `publisher.getAlbums()` loopen 
- van alle albums de publisher op `null` zetten met de setter
- en dan dat album saven met `albumRepository.save`

Als je dat gedaan hebt, kun je veilig de publisher deleten.

### Genre
De Genre heeft een relatie met Album waarbij Album de eigenaar van de relatie is.

In `GenreService.deleteGenre` is het iets lastiger, omdat Genre een unidirectionele relatie met Album heeft. Je kunt dus niet de Albums uit Genre halen met een getter. Je kunt wel:
- door `albumRepository.findByGenre_Id` heen loopen 
- alle Genre's op `null` zetten met de setter
- en dat vervolgens saven met `albumRepository.save`

Vergeet daarna niet om de Genre nog te deleten uit de GenreRepository.

### Album
De Album heeft een relatie met Stock waarbij Stock de eigenaar van de relatie is.
We gaan niet zomaar stocks verwijderen, als die er zijn. Dan zouden we inkomsten mislopen.
Nee, we gaan er voor zorgen dat we geen Album kunnen verwijderen als deze nog Stock heeft.

Om dit probleem op te lossen, moet je in `AlbumService.deleteAlbum` eerst de te-verwijderen-album uit de repository halen.

Daarna ga je controleren of dit album stocks heeft of niet (is de stocks lijst leeg of niet). 

Als die lijst leeg is, mag je het album verwijderen. Er is dan ook geen conflict met relaties. 

Als de lijst niet leeg is, dan zou er een conflict zijn, maar we mogen het Album dan ook niet verwijderen. We hoeven dat conflict daarom niet op te lossen.

### Stock en Artist
Deze modellen hebben geen conflict met de delete functionaliteit.






