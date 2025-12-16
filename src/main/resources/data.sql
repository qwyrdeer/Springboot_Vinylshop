insert into genres(create_date, edit_date, name, description)
values (now(), now(), 'pop', 'Pop music is commercially successful, catchy, mainstream music often featuring simple structures, memorable hooks, and relatable themes (like love or partying) designed for mass appeal.'),
       (now(), now(), 'jazz', 'Jazz is a vibrant, improvisational music genre born from African American communities, characterized by syncopated rhythms & complex harmonies.');

insert into publishers(create_date, edit_date, name, address, contact_details)
values (now(), now(), 'Sony', 'Amsterdam', 'ditiseenmailadres'),
       (now(), now(), 'Universal', 'Rotterdam', 'ditiseenmailadres');

INSERT INTO artists(create_date, edit_date, name, biography)
VALUES (now(), now(), 'Lady Gaga', 'Lady Gaga is a globally renowned, shape-shifting pop icon, singer, songwriter, and actress known for theatricality, avant-garde fashion, powerful vocals, while championing self-acceptance and empowering her "Little Monsters". '),
       (now(), now(), 'Tony Bennet', 'Tony Bennett was a legendary American singer known for his timeless voice, enduring pop standards like "I Left My Heart in San Francisco," 19 Grammy Awards, and a remarkable seven-decade career.'),
       (now(), now(), 'Mika', 'Mika is a Lebanese-British singer-songwriter known for his vibrant, catchy pop music and falsetto vocals, catapulting to fame with hits like "Grace Kelly" for his visually colorful style that blends pop, glam, and theatricality.');

INSERT INTO albums(create_date, edit_date, title, release_year, genre_id, publisher_id)
VALUES (now(), now(), 'Mayhem', 2025 ,1, 2),
       (now(), now(), 'Cheek to Cheek', 2014 ,2, 2),
       (now(), now(), 'Origin of Love', 2012 ,1, 1);

INSERT INTO stock(create_date, edit_date, condition, price, album_id)
VALUES (now(), now(), 'Excellent', '40.00', 1),
       (now(), now(), 'Great', '10.00', 2),
       (now(), now(), 'Good', '13.99', 3);

INSERT INTO albums_artists(album_id, artist_id)
VALUES (1, 1), (2, 1), (2, 2), (3, 3);