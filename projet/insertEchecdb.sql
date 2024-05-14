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

INSERT INTO Move (id) VALUES
('e4'),
('d4'),
('Nf3'),
('c4'),
('Nc3'),
('g3');

INSERT INTO MatchMove (id, move_number, match_id, elapsed_time, move_id) VALUES
(1, 1, 1, '00:10:00', 'e4'),
(2, 2, 1, '00:15:00', 'd4'),
(3, 1, 2, '00:20:00', 'Nf3'),
(4, 2, 2, '00:25:00', 'Nc3'),
(5, 3, 2, '00:30:00', 'g3');