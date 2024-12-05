
use dz7api;

-- Inserindo artistas na tabela `users` (BaseUser)
INSERT INTO users (username, email, password, created_at, updated_at, dtype)
VALUES
('JPEGMAFIA', 'jpegmafia@email.com', 'senha123', NOW(), NOW(), 'Artist'),
('LEALL', 'leall@email.com', 'senha123', NOW(), NOW(), 'Artist'),
('Playboi Carti', 'carti@email.com', 'senha123', NOW(), NOW(), 'Artist'),
('Sain', 'sain@email.com', 'senha123', NOW(), NOW(), 'Artist');

		
INSERT INTO category (name_category, max_temperature, min_temperature, created_at, updated_at)
VALUES
    ("Frio", 23, -10, NOW(), NOW()),
    ("Calor", 45, 24, NOW(), NOW());



INSERT INTO music (music_country, music_duration, genre_music, link_music, name_music, category_id, created_at, updated_at)
VALUES
    ('EUA', 168, 'Hip-hop', 'youtube.com/watch?v=F9JnxjP9cH4', 'I`ll be right there', 1, NOW(), NOW()),
    ('BRA', 149, 'Funk', 'youtube.com/watch?v=fC7REgp6WvE', 'SUV Prata', 2, NOW(), NOW()),
    ('EUA', 209, 'Trap', 'youtube.com/watch?v=YG3EhWlBaoI', '2024', 1, NOW(), NOW()),
    ('BRA', 173, 'Hip-hop', 'youtube.com/watch?v=LCyrRfi2-9k', 'Aquelas Coisas Mais Pra Frente', 2, NOW(), NOW());


INSERT INTO playlist (name_playlist, created_at, updated_at)
VALUES
    ('Ritmo de verão', NOW(), NOW()),
    ('Batidas do inverno', NOW(), NOW());



INSERT INTO artist_has_music(artist_id, music_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

INSERT INTO playlist_has_music (playlist_id, music_id)
VALUES
    (1, 2),
    (2, 1),
    (1, 4),
    (2, 3);