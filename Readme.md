Inleiding
Je hebt in de vorige opdracht een GenreController met CRUD operaties voor een tijdelijke Genre entiteit. De GenreController maakte gebruik van een tijdelijke GenreService om Genres op te slaan in een mock-database.

In deze opdracht ga je een echte GenreService maken die samenwerkt met een echte GenreRepository en gebruik maakt van een echte database. Je gaat ook een echte GenreEntiteit maken die je in die database gaat opslaan en bewerken.

Heb je de opdracht van vorige week niet gemaakt of niet af gekregen, dan kun je de voorbeeld uitwerkingen clonen.

Opdrachtbeschrijving
Je gaat in deze opdracht verder werken aan de opdracht die je vorige week gemaakt hebt.

Je gaat de GenreController uitbreiden met een werkende GenreService en GenreRepository. Daarnaast ga je ook een PublisherController met een PublisherService en een PublisherRepository maken.

Naast deze architecturale klassen heb je uiteraard ook entiteiten nodig Dat zijn de GenreEntity en de PublisherEntity, beide gebaseerd op de BaseEntity.

De repository is de klasse die communiceert met de database, maar dan moet er wel een database geconfigureerd zijn. Dat ga je doen in de application.properties. Daarvoor heb je ook een extra dependency nodig in de pom.xml.

Randvoorwaarden
Zorg dat je mappenstructuur aan de Maven voorwaarden voldoet
Je mappen structuur heeft ten minste de packages:
controllers
entities
repositories
services
Je hebt twee controllers:
GenreController
PublisherController
Je hebt twee service:
GenreService
PublisherService
Je hebt twee repositories:
GenreRepository
PublisherRepository
Je hebt drie entiteiten:
GenreEntity
PublisherEntity
BaseEntity
Je hebt de juiste instellingen in je application.properties om de database connectie goed tot stand te brengen.
Je hebt de juiste dependencies aan het project gekoppeld.
Je hebt minimaal één record van beide entiteiten in je data.sql staan.
Je hebt een export van Postman met up-to-date requests voor beide entiteiten
Stappenplan
Stap 1 (packages)
Je hebt vorige week al de juiste maven structuur geïmplementeerd. Zorg dat je naast de controller, service en entities packages, ook een repositories package hebt.

Stap 2 (dependencies)
Je hebt de volgende dependencies nodig:

spring-boot-starter-data-jpa: Deze heb je nodig om repositories en entities te kunnen maken met JPA.
postgresql: Deze heb je nodig om verbinding te kunnen leggen met de database.
Je kunt de exacte notatie voor deze dependencies vinden op https://mvnrepository.com

Stap 3 (application.properties)
Zet de juiste instellingen in de application.properties. Kijk in EdHub om te zien welk je ook alweer nodig hebt.

Denk er aan dat je wachtwoord publiek op github komt te staan, dus kies een wachtwoord als "password" en niet het wachtwoord dat je ook voor je Instagram gebruikt.

Stap 4 (entities)
Je gaat twee entities aanmaken die er als volgt uitzien:

GenreEntity

id
createDate
editDate
name
description
PublisherEntity

id
createDate
editDate
name
address
contactDetails
Je zult misschien al opgemerkt hebben dat de id, createDate en editDate in beide klassen voorkomen. In de java cursus heb je geleerd dat de juiste OOP oplossing dan is om overerving toe te passen. Dat is in SpringBoot in dit geval ook zo. (Je ziet dat name toevallig ook in deze beide entiteiten staat, maar de entiteiten die je er volgende les nog bij gaat maken, hebben dat niet.)

De base klasse gaan we de BaseEntity noemen. Deze bevat alle minimale attributen die een entiteit moet hebben (dus niet de name).

BaseEntity
BaseEntity

id (elke entiteit moet een Primary Key hebben)
createDAte (deze is administratief heel handig voor alle entiteiten)
editDate (deze is administratief heel handig voor alle entiteiten)
De exacte code voor de BaseEntity kun je vinden in de src-map van deze repository.

Deze klasse begint niet met een @Entity annotatie, maar met een @MappedSuperclass. Met deze annotatie implementeer je de simpelste vorm van overerving. De BaseEntity klasse krijgt geen eigen database tabel en kan geen relaties aangaan. De attributen van de BaseEntity klasse worden in de tabel van de sub-klasse gezet. In de code werkt de overerving verder zoals je gewend bent.

Daarnaast die je ook de @PrePersist en @PreUpdate annotaties. Deze zorgen er voor dat de entiteit voor de eerste persist (save) of voor elke opvolgende persist iets doet. In dit slaat het de huidige tijd en datum op.

Behalve deze "simpele" vorm van overerving, zijn er ook nog andere manieren. Maar denk er aan, aggregatie over compositie en compositie over overerving.

GenreEntity en PublisherEntity
De andere twee entiteiten mag je zelf implementeren.

Zorg dat ze de @Entity annotatie bevatten
Gebruik de @Table annotatie om de juiste tabel-naam in te stellen
Zorg dat ze beide van BaseEntity overerven.
Doe je dat niet, zorg dan dat tenminste de Primary Key een automatisch gegenereerd Long is.
Implementeer de andere attributen met getters en setters
Zorg dat de name attribuut in beide entiteiten verplicht is.
Stap 5 (repositories)
Definieer de GenreRepository en de PublisherRepository in de repositories package. Zorg dat ze overerven van de JpaRepository. Deze repositories hebben geen bijzondere functionaliteiten nodig.

Stap 6 (service interface)
Je hebt vorige week een GenreService gebruikt die niet helemaal werkt zoals het zou moeten. De publieke methodes in die "mock"-service hadden echter wel de goede basis opzet.

Maak een nieuwe GenreService die wel alle publieke methodes van de mock-GenreService bevat:

List findAllGenres();
GenreEntity findGenreById(Long id);
GenreEntity createGenre(GenreEntity input);
GenreEntity updateGenre(Long id, GenreEntity input);
void deleteGenre(Long id);
Doe hetzelfde voor de PublisherService.

Nog wat algemene opmerkingen:

Injecteer de repository via "constructor injection"
Gebruik de repository methodes om de benodigde data uit de database te halen of in de database te zetten.
Denk aan een goede afhandeling van de Optional datatypes in de service. Als de record niet gevonden is, mag je voor nu null returnen. Je kunt dit op verschillende manieren uitwerken, zoals uitgelegd in EdHub. In de voorbeeld uitwerkingen kun je straks ook verschillende manieren vinden.
Denk er aan dat je niet het ID overschrijft in de update methode, anders pas je misschien de verkeerde aan of maak je een nieuwe record.
PROTIP: het is handig om een private getGenreById methode te maken, die de record uit de database haalt en checkt. Deze actie moet je namelijk voor meerdere functies uitvoeren.
Voor de PublisherService mag je hetzelfde doen.

Stap 7 (controller)
Implementeer de PublisherController.
Weet je niet meer hoe dat moet, kijk dan nog eens bij de instructies van vorige week.

Stap 8 (data)
Vul de database met testdata door een data.sql in je resources map te zetten. Denk er ook aan dat je de juiste instellingen in je application.properties heb staan.

Zorg dat je ten minste één Genre en één Publisher in je database hebt staan.

PROTIP: voor datums kun je in de data.sql de waarde now() gebruikern, een SQL functie

Stap 9 (Postman)
Voeg een actuele export van je postman collectie toe aan de resources map.