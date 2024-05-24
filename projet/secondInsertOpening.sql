INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
    ('e2', 'e4', 'e7', 'e5', 'Ruy Lopez', 'One of the oldest chess openings, characterized by its considerable depth and richness of play, focusing on central control and strategic depth.');

INSERT INTO Defense (move1, move2, name, description) VALUES
    ('Nf3', 'Nc6', 'Two Knights Defense', 'A robust defense that provides Black with solid structure and flexibility, offering opportunities for counterplay.');

INSERT INTO Attack (move1, name, description) VALUES
    ('Bc4', 'Italian Game', 'Initiates an aggressive strategy aimed at undermining Black’s f7 square, leading to the classic Giuoco Piano or Evans Gambit openings.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
    ('d2', 'd4', 'd7', 'd5', 'Queen\'s Gambit', 'A classic opening where White offers a pawn sacrifice to gain better control of the center.');

INSERT INTO Defense (move1, move2, name, description) VALUES
('d7', 'd5', 'Slav Defense', 'A defensive strategy that combines solid pawn structure with flexibility, commonly used to counter the Queen\'s Gambit.');

INSERT INTO Attack (move1, name, description) VALUES
    ('c4', 'Queen\'s Gambit', 'An attacking strategy where White aims to control the center quickly by sacrificing a pawn, which could potentially lead to a stronger pawn structure and more active pieces.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('f4', 'e5', 'Nf3', 'd6', 'King\'s Gambit', 'An aggressive opening in which White sacrifices a pawn for quick development and attack potential.');

INSERT INTO Defense (move1, move2, name, description) VALUES
    ('e5', 'd6', 'Philidor Defense', 'A solid defensive setup aimed at maintaining a strong pawn structure in the center in response to White\'s early aggression.');

INSERT INTO Attack (move1, name, description) VALUES
('f4', 'King\'s Gambit Attack', 'An early pawn sacrifice by White aiming to seize the initiative and control the game\'s tempo through rapid development.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('e4', 'e5', 'Bc4', 'Nf6', 'Italian Game', 'A very old chess opening that starts with 1.e4 e5 2.Bc4 Nf6, focusing on development and control of the center, leading to a rich middle game.');

INSERT INTO Defense (move1, move2, name, description) VALUES
('e5', 'Nf6', 'Two Knights Defense', 'A common response to the Italian Game, where Black not only claims central space but also develops a knight to defend the king and challenge White’s setup.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('c4', 'e6', 'Nc3', 'Bb4', 'Nimzo-Indian Defense', 'A hypermodern opening where Black immediately challenges White’s control of the center and exerts pressure on the e4 square by targeting the knight on c3 with a bishop.');

INSERT INTO Defense (move1, move2, name, description) VALUES
('e6', 'Bb4', 'Nimzo-Indian', 'A classical defense strategy characterized by Black’s bishop pinning White’s knight to challenge White’s control over the center and prevent easy e4 advancement.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
('d4', 'Nf6', 'c4', 'e6', 'Indian Game', 'A flexible opening strategy where White aims to control the center with pawns while Black counters with piece development targeting the center and preparing to fianchetto or maintain pawn flexibility.');

INSERT INTO Defense (move1, move2, name, description) VALUES
('Nf6', 'e6', 'Indian Defense', 'A foundational setup for various Indian defenses where Black prepares to counter White’s central setup through flexible pawn structure and piece development, often leading to the Queen\'s Indian or King\'s Indian setups.');

INSERT INTO Attack (move1, name, description) VALUES
('d4', 'Queen\'s Pawn Game', 'A fundamental attacking approach where White opens with the queen’s pawn to seize space and dictate the center, typically supporting broad central control strategies.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
    ('Nf3', 'd5', 'g3', 'c5', 'Reti Opening', 'A hypermodern chess opening that uses a knight followed by fianchetto of the king’s bishop to control the center from a distance and allows flexibility in pawn structure.');

INSERT INTO Defense (move1, move2, name, description) VALUES
    ('d5', 'c5', 'Symmetrical Defense', 'A solid and symmetrical pawn structure that provides stability and control in the center, often leading to open games with equal chances.');

INSERT INTO Attack (move1, name, description) VALUES
    ('g3', 'Fianchetto Setup', 'This setup involves a fianchetto of the bishop, aiming to increase control over the long diagonal and support central operations from a distance, typically part of broader flank strategies.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
    ('e4', 'e5', 'Nf3', 'Nc6', 'Ruy Lopez', 'One of the oldest and most profound chess openings, aiming for central control and piece development, typically leading into complex middle games.');

INSERT INTO Defense (move1, move2, name, description) VALUES
    ('e5', 'Nc6', 'Open Game Defense', 'A fundamental response that supports the central pawn and develops a knight to safeguard key squares and add pressure on the central files.');

INSERT INTO Attack (move1, name, description) VALUES
    ('Nf3', 'Ruy Lopez Attack', 'The third move Nf3 in the Ruy Lopez sequence prepares to develop the bishop to b5, challenging the knight on c6 and setting up potential pins against the Black king.');

INSERT INTO Opening (move1, move2, move3, move4, name, description) VALUES
    ('d4', 'd5', 'c4', 'c6', 'Slav Defense', 'A solid and reliable response to the Queen’s Gambit, emphasizing control over the central and queenside squares.');

INSERT INTO Defense (move1, move2, name, description) VALUES
    ('d5', 'c6', 'Slav Defense', 'A popular defense strategy in response to the Queen\'s Gambit that provides Black with a strong pawn structure and flexibility.');




-- -----------------------------------

-- Match 1: Ouverture italienne et défense ouverte

-- Match 2: Gambit dame accepté et défense slave

INSERT INTO Defense (move1, move2, name, description) VALUES
('d4', 'd5', 'Défense slave', 'Une défense solide pour les Noirs qui protège le pion d5 avec c6.');

-- Match 3: Gambit du roi et défense Falkbeer

INSERT INTO Defense (move1, move2, name, description) VALUES
('f4', 'e5', 'Défense Falkbeer', 'Les Noirs contre-attaquent immédiatement après le gambit du roi.');

-- Match 4: Ouverture italienne et défense ouverte

INSERT INTO Defense (move1, move2, name, description) VALUES
('e4', 'e5', 'Défense ouverte', 'Les pions centraux sont échangés rapidement pour ouvrir le centre du plateau.');

-- Match 5: Ouverture anglaise et défense sicilienne

INSERT INTO Defense (move1, move2, name, description) VALUES
('c4', 'e6', 'Défense sicilienne', 'Une défense populaire qui cherche à déséquilibrer la position dès le début.');

-- Match 6: Gambit dame refusé et défense slave

INSERT INTO Defense (move1, move2, name, description) VALUES
('d4', 'Nf6', 'Défense slave', 'Une défense solide pour les Noirs qui protège le pion d5 avec c6.');


INSERT INTO Defense (move1, move2, name, description) VALUES
('Nf3', 'd5', 'Défense semi-slave', 'Une défense solide pour les Noirs qui protège le pion d5 avec c6.');












