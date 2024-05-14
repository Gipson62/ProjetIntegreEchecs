INSERT INTO `Rank` (id, description, name) VALUES
(1, 'Description pour Fer', 'Fer'),
(2, 'Description pour Bronze', 'Bronze'),
(3, 'Description pour Argent', 'Argent'),
(4, 'Description pour Or', 'Or'),
(5, 'Description pour Platine', 'Platine'),
(6, 'Description pour Diamant', 'Diamant');

INSERT INTO Account (id, email, username, `rank`, birthdate, is_beginner, elo, password, bio, tag, gender) VALUES
(default, 'utilisateur1@example.com', 'utilisateur1', 1, '2000-01-01', 1, 1501, 'motdepasse1', 'Bio de l''utilisateur 1', 123456, 'Masculin'),
(default, 'utilisateur2@example.com', 'utilisateur2', 3, '1995-05-10', 0, 2200, 'motdepasse2', NULL, 654321, 'Féminin'),
(default, 'utilisateur3@example.com', 'utilisateur3', 2, '1998-03-15', 0, 1800, 'motdepasse3', NULL, 987654, 'Masculin'),
(default, 'utilisateur4@example.com', 'utilisateur4', 4, '1990-07-20', 0, 2400, 'motdepasse4', 'Bio de l''utilisateur 4', 456789, 'Féminin'),
(default, 'utilisateur5@example.com', 'utilisateur5', 2, '1997-04-20', 0, 1850, 'motdepasse5', 'Bio de l''utilisateur 5', 111111, 'Masculin'),
(default, 'utilisateur6@example.com', 'utilisateur6', 3, '1996-08-15', 0, 2100, 'motdepasse6', NULL, 222222, 'Féminin'),
(default, 'utilisateur7@example.com', 'utilisateur7', 4, '1993-10-25', 0, 2300, 'motdepasse7', 'Bio de l''utilisateur 7', 333333, 'Masculin'),
(default, 'utilisateur8@example.com', 'utilisateur8', 5, '1988-12-30', 0, 2550, 'motdepasse8', NULL, 444444, 'Féminin');


INSERT INTO FriendShip (id, Friend_1, Friend_2, dateRelation) VALUES
(default, 1, 3, '2024-04-30'),
(default, 3, 1, '2024-04-30'),
(default, 1, 2, '2024-04-30'),
(default, 2, 1, '2024-04-30'),
(default, 3, 4, '2024-04-30'),
(default, 4, 3, '2024-04-30');

INSERT INTO TournamentState (id, state) VALUES
(1, 'final'),
(2, 'demi-final'),
(3, 'quart-final'),
(4, 'huitième-final'),
(5, 'qualifications');

INSERT INTO Tournament (id, elo, name, date) VALUES
(default, 2000, 'Tournament 1', '2024-06-15'),
(default, 2200, 'Tournament 2', '2024-07-20'),
(default, 1800, 'Tournament 3', '2024-08-10'),
(default, 2400, 'Tournament 4', '2024-09-15');

INSERT INTO `Match` (id, player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
(default, 1, 8, 2, 1, '2024-08-16 13:00:00', '2024-08-16 15:00:00', 'w', 7200),
(default, 1, 2, 1, 1, '2024-06-15 10:00:00', '2024-06-15 12:00:00', 'b', 7200),
(default, 1, 2, 2, 2, '2024-07-20 09:00:00', '2024-07-20 11:00:00', 'w', 7200),
(default, 1, 2, 1, 1, '2024-06-20 11:00:00', '2024-06-20 13:00:00', 'b', 7200),
(default, 1, 3, 1, 1, '2024-06-25 14:00:00', '2024-06-25 16:00:00', 'w', 7200),
(default, 2, 1, 1, 1, '2024-07-01 09:00:00', '2024-07-01 11:00:00', 'w', 7200),
(default, 1, 3, 1, 1, '2024-07-05 10:00:00', '2024-07-05 12:00:00', 'b', 7200),
(default, 1, 2, 2, 2, '2024-07-10 13:00:00', '2024-07-10 15:00:00', 'w', 7200),
(default, 1, 3, 2, 2, '2024-07-15 11:00:00', '2024-07-15 13:00:00', 'b', 7200),
(default, 1, 5, 3, 4, '2024-08-01 10:00:00', '2024-08-01 12:00:00', 'b', 7200),
(default, 1, 6, 3, 4, '2024-08-05 11:00:00', '2024-08-05 13:00:00', 'w', 7200),
(default, 1, 7, 3, 4, '2024-08-10 12:00:00', '2024-08-10 14:00:00', 'b', 7200),
(default, 1, 8, 3, 4, '2024-08-15 13:00:00', '2024-08-15 15:00:00', 'w', 7200);


-- ---------------------------------------------------------------------------------------------------------------------------------
-- Insérer les mouvements possibles pour les pions avec la notation précédente
INSERT INTO Move (id) VALUES
('a2'), ('a3'), ('a4'), ('b2'), ('b3'), ('b4'), ('c2'), ('c3'),
('c4'), ('d2'), ('d3'), ('d4'), ('e2'), ('e3'), ('e4'), ('f2'),
('f3'), ('f4'), ('g2'), ('g3'), ('g4'), ('h2'), ('h3'), ('h4');

-- Insérer les mouvements possibles pour les pions noirs avec la notation précédente
INSERT INTO Move (id) VALUES
('a7'), ('a6'), ('a5'), ('b7'), ('b6'), ('b5'), ('c7'), ('c6'),
('c5'), ('d7'), ('d6'), ('d5'), ('e7'), ('e6'), ('e5'), ('f7'),
('f6'), ('f5'), ('g7'), ('g6'), ('g5'), ('h7'), ('h6'), ('h5');
-- Insérer les mouvements possibles pour les cavaliers noirs avec une notation simplifiée
INSERT INTO Move (id) VALUES
('Nc6'), ('Na6'), ('Nf6'), ('Nh6');

-- Insérer les mouvements possibles pour les cavaliers blancs avec une notation simplifiée
INSERT INTO Move (id) VALUES
('Nc3'), ('Na3'), ('Nf3'), ('Nh3');

-- Insérer les mouvements possibles pour les fous noirs avec une notation simplifiée
INSERT INTO Move (id) VALUES
('Bc8'), ('Bb7'), ('Bg8'), ('Bh7');

-- Insérer les mouvements possibles pour les fous blancs avec une notation simplifiée
INSERT INTO Move (id) VALUES
('Bc1'), ('Bb2'), ('Bg1'), ('Bh2');

-- Insérer les mouvements possibles pour les reines noires avec une notation simplifiée
INSERT INTO Move (id) VALUES
('Qd8'), ('Qc7'), ('Qb8');

-- Insérer les mouvements possibles pour les reines blanches avec une notation simplifiée
INSERT INTO Move (id) VALUES
('Qd1'), ('Qc2'), ('Qb1');


-- Inserts pour le premier match
INSERT INTO MatchMove ( move_number, match_id, elapsed_time, move_id)
VALUES
( 1, 1, '00:10:00', 'e4'), -- Coup 1 des Blancs
( 2, 1, '00:15:00', 'e5'), -- Coup 1 des Noirs en réponse au coup 1 des Blancs
( 3, 1, '00:20:00', 'd4'), -- Coup 2 des Blancs en réponse au coup 1 des Noirs
( 4, 1, '00:25:00', 'd5'), -- Coup 2 des Noirs en réponse au coup 2 des Blancs
( 5, 1, '00:30:00', 'Nf3'); -- Coup 3 des Blancs en réponse au coup 2 des Noirs

select *
from matchMove
order by match_id,move_number;


-- Insertion de l'ouverture
INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('e2', 'e4', 'e7', 'e5', 'Espagnole', 'Ouverture espagnole');
-- Insertion de l'attaque
INSERT INTO Attack (move1, name, description) VALUES
('Nf3', 'Attaque de Cavalier', 'Un développement typique des Blancs dans l\'ouverture espagnole');
-- Insertion de la défense
INSERT INTO Defense (move1, move2, name, description) VALUES
('Nc6', 'Bb5', 'Défense des Pions', 'Une réponse typique des Noirs pour contrer l\'ouverture espagnole');

INSERT INTO Defense (move1, move2, name, description) VALUES
('Nf3', 'Nc3', 'Défense cavalier impossible', 'Defense de test');

-- Inserts pour les ouvertures
INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('Nf3', 'Nc3','g3','e4', 'OPening test', 'heheheheeh'),
('e4', 'e5', 'd4', 'd5', 'Test pour voir', 'An opening that begins just a test');



-- Inserts pour les ouvertures
INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('e4', 'e5', 'Nf3', 'Nc6', 'Italian Game', 'An opening that begins with the moves 1.e4 e5 2.Nf3 Nc6');
-- Inserts pour les défenses
INSERT INTO Defense (move1, move2, name, description) VALUES
('e4', 'e5', 'Ruy Lopez', 'A classical defense against 1.e4 e5');
-- Inserts pour les attaques
-- INSERT INTO Attack (move1, name, description) VALUES
 -- ('Nf3', 'Reti Opening', 'A hypermodern opening that begins with the move 1.Nf3');
-- existe deja

INSERT INTO Move (id) VALUES
('exD5'), ('Dxd5');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('e4', 'D5', 'exD5', 'Dxd5', 'Mieses-Kotrč Variation', 'Scandinavian Defense: Mieses-Kotrč Variation');
-- Insertion de l'attaque
INSERT INTO Attack (move1, name, description) VALUES
('e4', 'king s pawn', 'Un développement typique des Blancs');
-- Insertion de la défense
INSERT INTO Defense (move1, move2, name, description) VALUES
('e4', 'd5', 'Scandinavian Defense', 'Une réponse typique des Noirs pour contrer l\'ouverture');



INSERT INTO MatchMove ( move_number, match_id, elapsed_time, move_id)
VALUES
( 1, 14, '00:10:00', 'e4'), -- Coup 1 des Blancs
(  2,14, '00:15:00', 'd5'), -- Coup 1 des Noirs en réponse au coup 1 des Blancs
(  3,14, '00:20:00', 'exD5'), -- Coup 2 des Blancs en réponse au coup 1 des Noirs
( 4,14, '00:25:00', 'Dxd5'); -- Coup 2 des Noirs en réponse au coup 2 des Blancs





