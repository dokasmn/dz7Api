use dz7api;

INSERT INTO artist(name_artist, link_artist)
VALUES 
("JPEGMAFIA", "youtube.com/@JPEGMAFIA"),
("LEALL", "youtube.com/@LEALL"),
("Playboi Carti", "youtube.com/channel/UC652oRUvX1onwrrZ8ADJRPw"),
("Sain", "youtube.com/channel/UCFoBB2GjYw1OSDwKwtoBGqg");
		
INSERT INTO category(name_category, max_temperature, min_temperature)
VALUES
("Frio", 23, -10),
("Calor", 45, 24);

INSERT INTO music (music_country, duration_music, genre_music, link_music, name_music, id_category)
VALUES 
('EUA', 168, 'Hip-hop', 'youtube.com/watch?v=F9JnxjP9cH4', 'I`ll be right there', 1),
('BRA', 149, 'Funk', 'youtube.com/watch?v=fC7REgp6WvE', 'SUV Prata', 2),
('EUA', 209, 'Trap', 'youtube.com/watch?v=YG3EhWlBaoI', '2024', 1),
('BRA', 173, 'Hip-hop', 'youtube.com/watch?v=LCyrRfi2-9k', 'Aquelas Coisas Mais Pra Frente', 2);

INSERT INTO playlist (name_playlist)
VALUES
('Ritmo de verão'),
('Batidas do inverno');

INSERT INTO artist_has_music(artist_id, music_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

INSERT INTO playlist_has_music(playlist_id, music_id)
VALUES
(1, 2),
(2, 1),
(1, 4),
(2, 3);
