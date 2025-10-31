INSERT INTO singer_table (id, name, genre, debut_date, nationality)
VALUES (1, 'Arijit Singh', 'Pop', '2011-01-01', 'Indian');

INSERT INTO album_table (id, title, release_date, language, singer_id)
VALUES (1, 'Soulful Melodies', '2014-05-12', 'Hindi', 1);

INSERT INTO songs_table (id, title, release_date, duration, language, singer_id, album_id)
VALUES (1, 'Tum Hi Ho', '2013-04-16', 4.30, 'Hindi', 1, 1),
       (2, 'Channa Mereya', '2016-10-15', 4.25, 'Hindi', 1, 1);
