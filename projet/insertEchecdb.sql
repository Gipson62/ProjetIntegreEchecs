INSERT INTO `Rank` (id, description, name) VALUES
                                               (1, 'Description pour Fer', 'Fer'),
                                               (2, 'Description pour Bronze', 'Bronze'),
                                               (3, 'Description pour Argent', 'Argent'),
                                               (4, 'Description pour Or', 'Or'),
                                               (5, 'Description pour Platine', 'Platine'),
                                               (6, 'Description pour Diamant', 'Diamant');

INSERT INTO Account (email, username, `rank`, birthdate, is_beginner, elo, password, bio, tag, gender) VALUES
                                                                                                           ('john.doe@example.com', 'JohnDoe', 1, '1990-05-15', 0, 1200, 'hashed_password_123', 'A chess enthusiast.', 001, 'Male'),
                                                                                                           ('jane.smith@example.com', 'JaneSmith', 2, '1985-08-20', 1, 800, 'hashed_password_456', 'Beginner in chess.', 002, 'Female'),
                                                                                                           ('alice.johnson@example.com', 'AliceJ', 3, '1992-11-30', 0, 1500, 'hashed_password_789', 'Intermediate player.', 003, 'Female'),
                                                                                                           ('bob.brown@example.com', 'BobbyB', 4, '1988-03-25', 0, 1800, 'hashed_password_101', 'Advanced player.', 004, 'Male'),
                                                                                                           ('carol.white@example.com', 'CarolW', 5, '1975-07-09', 0, 2000, 'hashed_password_102', 'Competitive player.', 005, 'Female'),
                                                                                                           ('dave.black@example.com', 'DaveB', 6, '2000-12-12', 1, 950, 'hashed_password_103', 'New to chess.', 006, 'Male'),
                                                                                                           ('emily.gray@example.com', 'EmilyG', 1, '1994-09-14', 1, 1100, 'hashed_password_204', 'Just started learning chess.', 007, 'Female'),
                                                                                                           ('frank.green@example.com', 'FrankieG', 2, '1982-01-22', 0, 1400, 'hashed_password_205', 'Enjoys weekend chess games.', 008, 'Male'),
                                                                                                           ('helen.red@example.com', 'HelenR', 3, '1972-04-08', 0, 1650, 'hashed_password_206', 'Plays chess with friends.', 009, 'Female'),
                                                                                                           ('ivan.silver@example.com', 'IvanS', 4, '2003-06-30', 1, 700, 'hashed_password_207', 'High school chess club member.', 010, 'Male'),
                                                                                                           ('julia.gold@example.com', 'JuliaG', 5, '1999-11-25', 0, 1900, 'hashed_password_208', 'Aspiring professional player.', 011, 'Female'),
                                                                                                           ('kyle.bronze@example.com', 'KyleB', 6, '1987-10-17', 0, 1750, 'hashed_password_209', 'Plays chess online regularly.', 012, 'Male'),
                                                                                                           ('lucas.mint@example.com', 'LucasM', 2, '1991-12-28', 1, 900, 'hashed_password_210', 'Chess lover from a young age.', 013, 'Male'),
                                                                                                           ('megan.peach@example.com', 'MeganP', 1, '1989-07-14', 0, 1300, 'hashed_password_211', 'Casual player, enjoys local tournaments.', 014, 'Female'),
                                                                                                           ('nathan.opal@example.com', 'NateO', 3, '2002-03-05', 0, 1520, 'hashed_password_212', 'Studies chess strategies diligently.', 015, 'Male'),
                                                                                                           ('olivia.cerulean@example.com', 'OliviaC', 4, '1996-05-19', 1, 780, 'hashed_password_213', 'New to competitive chess.', 016, 'Female'),
                                                                                                           ('peter.amber@example.com', 'PeteA', 5, '1978-08-23', 0, 2050, 'hashed_password_214', 'Veteran chess player with 20 years of experience.', 017, 'Male'),
                                                                                                           ('quinn.ruby@example.com', 'QuinnR', 6, '1993-11-15', 0, 1850, 'hashed_password_215', 'Enjoys challenging chess puzzles and games.', 018, 'Non-binary');

INSERT INTO FriendShip (Friend_1, Friend_2) VALUES
                                                (1, 2), (2, 3), (3, 4), (4, 5), (5, 6), (6, 7),
                                                (7, 8), (8, 9), (9, 10), (10, 11), (11, 12), (12, 13),
                                                (13, 14), (14, 15), (15, 16), (16, 17), (17, 18), (18, 1),
                                                (1, 3), (2, 4), (3, 5), (4, 6), (5, 7), (6, 8),
                                                (7, 9), (8, 10), (9, 11), (10, 12), (11, 13), (12, 14),
                                                (13, 15), (14, 16), (15, 17), (16, 18);

INSERT INTO TournamentState (id, state) VALUES
                                            (1, 'final'),
                                            (2, 'demi-final'),
                                            (3, 'quart-final'),
                                            (4, 'huitième-final'),
                                            (5, 'qualifications');

INSERT INTO Tournament (elo, name, date) VALUES
                                             (1200, 'Spring Open', '2023-04-15'),
                                             (1500, 'Midsummer Championship', '2023-06-20'),
                                             (1800, 'Autumn Masters', '2023-09-10'),
                                             (1000, 'Winter Cup', '2023-12-05'),
                                             (2000, 'Summer Invitational', '2023-08-01'),
                                             (2200, 'Fall Classic', '2023-09-15');


INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
                                                                                                                       (1, 2, 1, 3, '2023-06-20 10:00:00', '2023-06-20 11:00:00', 'b', 20),
                                                                                                                       (3, 4, 1, 3, '2023-06-20 12:00:00', '2023-06-20 13:00:00', 'w', 20),
                                                                                                                       (5, 6, 1, 3, '2023-06-21 10:00:00', '2023-06-21 11:00:00', 'b', 20),
                                                                                                                       (7, 8, 1, 3, '2023-06-21 12:00:00', '2023-06-21 13:00:00', 'w', 20),
                                                                                                                       (1, 3, 1, 2, '2023-06-22 10:00:00', '2023-06-22 11:00:00', 'b', 20),
                                                                                                                       (5, 7, 1, 2, '2023-06-22 12:00:00', '2023-06-22 13:00:00', 'w', 20),
                                                                                                                       (1, 5, 1, 1, '2023-06-23 10:00:00', '2023-06-23 11:00:00', 'b', 20);
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
                                                                                                                       (9, 10, 2, 3, '2023-06-25 10:00:00', '2023-06-25 11:00:00', 'w', 20),
                                                                                                                       (11, 12, 2, 3, '2023-06-25 12:00:00', '2023-06-25 13:00:00', 'b', 20),
                                                                                                                       (13, 14, 2, 3, '2023-06-26 10:00:00', '2023-06-26 11:00:00', 'w', 20),
                                                                                                                       (15, 16, 2, 3, '2023-06-26 12:00:00', '2023-06-26 13:00:00', 'b', 20),
                                                                                                                       (9, 11, 2, 2, '2023-06-27 10:00:00', '2023-06-27 11:00:00', 'w', 20),
                                                                                                                       (13, 15, 2, 2, '2023-06-27 12:00:00', '2023-06-27 13:00:00', 'b', 20),
                                                                                                                       (9, 13, 2, 1, '2023-06-28 10:00:00', '2023-06-28 11:00:00', 'w', 20);
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
                                                                                                                       (1, 2, 3, 3, '2023-07-01 10:00:00', '2023-07-01 11:00:00', 'b', 20),
                                                                                                                       (3, 4, 3, 3, '2023-07-01 12:00:00', '2023-07-01 13:00:00', 'w', 20),
                                                                                                                       (5, 6, 3, 3, '2023-07-02 10:00:00', '2023-07-02 11:00:00', 'b', 20),
                                                                                                                       (7, 8, 3, 3, '2023-07-02 12:00:00', '2023-07-02 13:00:00', 'w', 20),
                                                                                                                       (1, 3, 3, 2, '2023-07-03 10:00:00', '2023-07-03 11:00:00', 'b', 20),
                                                                                                                       (5, 7, 3, 2, '2023-07-03 12:00:00', '2023-07-03 13:00:00', 'w', 20),
                                                                                                                       (1, 5, 3, 1, '2023-07-04 10:00:00', '2023-07-04 11:00:00', 'b', 20);
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
                                                                                                                       (9, 10, 4, 3, '2023-07-10 10:00:00', '2023-07-10 11:00:00', 'w', 20),
                                                                                                                       (11, 12, 4, 3, '2023-07-10 12:00:00', '2023-07-10 13:00:00', 'b', 20),
                                                                                                                       (13, 14, 4, 3, '2023-07-11 10:00:00', '2023-07-11 11:00:00', 'w', 20),
                                                                                                                       (15, 16, 4, 3, '2023-07-11 12:00:00', '2023-07-11 13:00:00', 'b', 20),
                                                                                                                       (9, 11, 4, 2, '2023-07-12 10:00:00', '2023-07-12 11:00:00', 'w', 20),
                                                                                                                       (13, 15, 4, 2, '2023-07-12 12:00:00', '2023-07-12 13:00:00', 'b', 20),
                                                                                                                       (9, 13, 4, 1, '2023-07-13 10:00:00', '2023-07-13 11:00:00', 'w', 20);
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
                                                                                                                       (2, 1, 5, 3, '2023-08-01 10:00:00', '2023-08-01 11:00:00', 'w', 20),
                                                                                                                       (4, 3, 5, 3, '2023-08-01 12:00:00', '2023-08-01 13:00:00', 'b', 20),
                                                                                                                       (6, 5, 5, 3, '2023-08-02 10:00:00', '2023-08-02 11:00:00', 'w', 20),
                                                                                                                       (8, 7, 5, 3, '2023-08-02 12:00:00', '2023-08-02 13:00:00', 'b', 20),
                                                                                                                       (2, 4, 5, 2, '2023-08-03 10:00:00', '2023-08-03 11:00:00', 'w', 20),
                                                                                                                       (6, 8, 5, 2, '2023-08-03 12:00:00', '2023-08-03 13:00:00', 'b', 20),
                                                                                                                       (2, 6, 5, 1, '2023-08-04 10:00:00', '2023-08-04 11:00:00', 'w', 20);
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
                                                                                                                       (10, 9, 6, 3, '2023-09-15 10:00:00', '2023-09-15 11:00:00', 'b', 20),
                                                                                                                       (12, 11, 6, 3, '2023-09-15 12:00:00', '2023-09-15 13:00:00', 'w', 20),
                                                                                                                       (14, 13, 6, 3, '2023-09-16 10:00:00', '2023-09-16 11:00:00', 'b', 20),
                                                                                                                       (2, 1, 6, 3, '2023-09-16 12:00:00', '2023-09-16 13:00:00', 'w', 20),
                                                                                                                       (10, 12, 6, 2, '2023-09-17 10:00:00', '2023-09-17 11:00:00', 'b', 20),
                                                                                                                       (14, 2, 6, 2, '2023-09-17 12:00:00', '2023-09-17 13:00:00', 'w', 20),
                                                                                                                       (10, 14, 6, 1, '2023-09-18 10:00:00', '2023-09-18 11:00:00', 'b', 20);


INSERT INTO Move (id) VALUES
                          ('a2'), ('a3'), ('a4'), ('a5'), ('a6'), ('a7'),
                          ('b2'), ('b3'), ('b4'), ('b5'), ('b6'), ('b7'),
                          ('c2'), ('c3'), ('c4'), ('c5'), ('c6'), ('c7'),
                          ('d2'), ('d3'), ('d4'), ('d5'), ('d6'), ('d7'),
                          ('e2'), ('e3'), ('e4'), ('e5'), ('e6'), ('e7'),
                          ('f2'), ('f3'), ('f4'), ('f5'), ('f6'), ('f7'),
                          ('g2'), ('g3'), ('g4'), ('g5'), ('g6'), ('g7'),
                          ('h2'), ('h3'), ('h4'), ('h5'), ('h6'), ('h7'),
                          ('Ba1'), ('Ba2'), ('Ba3'), ('Ba4'), ('Ba5'), ('Ba6'), ('Ba7'), ('Ba8'),
                          ('Bb1'), ('Bb2'), ('Bb3'), ('Bb4'), ('Bb5'), ('Bb6'), ('Bb7'), ('Bb8'),
                          ('Bc1'), ('Bc2'), ('Bc3'), ('Bc4'), ('Bc5'), ('Bc6'), ('Bc7'), ('Bc8'),
                          ('Bd1'), ('Bd2'), ('Bd3'), ('Bd4'), ('Bd5'), ('Bd6'), ('Bd7'), ('Bd8'),
                          ('Be1'), ('Be2'), ('Be3'), ('Be4'), ('Be5'), ('Be6'), ('Be7'), ('Be8'),
                          ('Bf1'), ('Bf2'), ('Bf3'), ('Bf4'), ('Bf5'), ('Bf6'), ('Bf7'), ('Bf8'),
                          ('Bg1'), ('Bg2'), ('Bg3'), ('Bg4'), ('Bg5'), ('Bg6'), ('Bg7'), ('Bg8'),
                          ('Bh1'), ('Bh2'), ('Bh3'), ('Bh4'), ('Bh5'), ('Bh6'), ('Bh7'), ('Bh8'),
                          ('Na1'), ('Na2'), ('Na3'), ('Na4'), ('Na5'), ('Na6'), ('Na7'), ('Na8'),
                          ('Nb1'), ('Nb2'), ('Nb3'), ('Nb4'), ('Nb5'), ('Nb6'), ('Nb7'), ('Nb8'),
                          ('Nc1'), ('Nc2'), ('Nc3'), ('Nc4'), ('Nc5'), ('Nc6'), ('Nc7'), ('Nc8'),
                          ('Nd1'), ('Nd2'), ('Nd3'), ('Nd4'), ('Nd5'), ('Nd6'), ('Nd7'), ('Nd8'),
                          ('Ne1'), ('Ne2'), ('Ne3'), ('Ne4'), ('Ne5'), ('Ne6'), ('Ne7'), ('Ne8'),
                          ('Nf1'), ('Nf2'), ('Nf3'), ('Nf4'), ('Nf5'), ('Nf6'), ('Nf7'), ('Nf8'),
                          ('Ng1'), ('Ng2'), ('Ng3'), ('Ng4'), ('Ng5'), ('Ng6'), ('Ng7'), ('Ng8'),
                          ('Nh1'), ('Nh2'), ('Nh3'), ('Nh4'), ('Nh5'), ('Nh6'), ('Nh7'), ('Nh8'),
                          ('Qd8'), ('Qc7'), ('Qb8'),
                          ('Qd1'), ('Qc2'), ('Qb1'),
                          ('Kc1'), ('Ke1'), ('Kf1'), ('Kg1'), ('Kh1'),  -- Moves for white king
                          ('Kc8'), ('Ke8'), ('Kf8'), ('Kg8'), ('Kh8'),  -- Moves for black king
                          ('Ra1'), ('Rf1'), ('Rg1'), ('Rh1'),           -- Moves for white rooks
                          ('Ra8'), ('Rf8'), ('Rg8'), ('Rh8'),           -- Moves for black rooks
                          ('0-0'), ('0-0-0');

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 1, '00:02:00', 'e2'), -- Player 1 opens with pawn to e4
                                                                         (2, 1, '00:02:30', 'e7'), -- Player 2 responds with pawn to e5
                                                                         (3, 1, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 1, '00:03:30', 'Nc6');-- Player 2 knight to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 2, '00:02:00', 'd2'), -- Player 1 opens with pawn to d4
                                                                         (2, 2, '00:02:30', 'd7'), -- Player 2 responds with pawn to d5
                                                                         (3, 2, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 2, '00:03:30', 'c6'); -- Player 2 pawn to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 3, '00:02:00', 'f4'), -- Player 1 opens with pawn to f4
                                                                         (2, 3, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 3, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 3, '00:03:30', 'd6'); -- Player 2 pawn to d6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 4, '00:02:00', 'e4'), -- Player 1 opens with pawn to e4
                                                                         (2, 4, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 4, '00:03:00', 'Bc4'), -- Player 1 bishop to c4
                                                                         (4, 4, '00:03:30', 'Nf6'); -- Player 2 knight to f6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 5, '00:02:00', 'c4'), -- Player 1 opens with pawn to c4
                                                                         (2, 5, '00:02:30', 'e6'), -- Player 2 responds with pawn to e6
                                                                         (3, 5, '00:03:00', 'Nc3'), -- Player 1 knight to c3
                                                                         (4, 5, '00:03:30', 'Bb4'); -- Player 2 bishop to b4

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 6, '00:02:00', 'd4'), -- Player 1 opens with pawn to d4
                                                                         (2, 6, '00:02:30', 'Nf6'), -- Player 2 responds with knight to f6
                                                                         (3, 6, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 6, '00:03:30', 'e6'); -- Player 2 pawn to e6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 7, '00:02:00', 'Nf3'), -- Player 1 opens with knight to f3
                                                                         (2, 7, '00:02:30', 'd5'), -- Player 2 responds with pawn to d5
                                                                         (3, 7, '00:03:00', 'g3'), -- Player 1 pawn to g3
                                                                         (4, 7, '00:03:30', 'c5'); -- Player 2 pawn to c5

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 8, '00:02:00', 'e4'), -- Player 1 opens with pawn to e4
                                                                         (2, 8, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 8, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 8, '00:03:30', 'Nc6'); -- Player 2 knight to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 9, '00:02:00', 'd4'), -- Player 1 opens with pawn to d4
                                                                         (2, 9, '00:02:30', 'd5'), -- Player 2 responds with pawn to d5
                                                                         (3, 9, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 9, '00:03:30', 'c6'); -- Player 2 pawn to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 10, '00:02:00', 'f4'), -- Player 1 opens with pawn to f4
                                                                         (2, 10, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 10, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 10, '00:03:30', 'd6'); -- Player 2 pawn to d6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 11, '00:02:00', 'e4'), -- Player 1 opens with pawn to e4
                                                                         (2, 11, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 11, '00:03:00', 'Bc4'), -- Player 1 bishop to c4
                                                                         (4, 11, '00:03:30', 'Nf6'); -- Player 2 knight to f6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 12, '00:02:00', 'c4'), -- Player 1 opens with pawn to c4
                                                                         (2, 12, '00:02:30', 'e6'), -- Player 2 responds with pawn to e6
                                                                         (3, 12, '00:03:00', 'Nc3'), -- Player 1 knight to c3
                                                                         (4, 12, '00:03:30', 'Bb4'); -- Player 2 bishop to b4

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 13, '00:02:00', 'd4'), -- Player 1 opens with pawn to d4
                                                                         (2, 13, '00:02:30', 'Nf6'), -- Player 2 responds with knight to f6
                                                                         (3, 13, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 13, '00:03:30', 'e6'); -- Player 2 pawn to e6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 14, '00:02:00', 'Nf3'), -- Player 1 opens with knight to f3
                                                                         (2, 14, '00:02:30', 'd5'), -- Player 2 responds with pawn to d5
                                                                         (3, 14, '00:03:00', 'g3'), -- Player 1 pawn to g3
                                                                         (4, 14, '00:03:30', 'c5'); -- Player 2 pawn to c5

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 15, '00:02:00', 'e4'), -- Player 1 opens with pawn to e4
                                                                         (2, 15, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 15, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 15, '00:03:30', 'Nc6'); -- Player 2 knight to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 16, '00:02:00', 'd4'), -- Player 1 opens with pawn to d4
                                                                         (2, 16, '00:02:30', 'd5'), -- Player 2 responds with pawn to d5
                                                                         (3, 16, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 16, '00:03:30', 'c6'); -- Player 2 pawn to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 17, '00:02:00', 'f4'), -- Player 1 opens with pawn to f4
                                                                         (2, 17, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 17, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 17, '00:03:30', 'd6'); -- Player 2 pawn to d6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 18, '00:02:00', 'e4'), -- Player 1 opens with pawn to e4
                                                                         (2, 18, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 18, '00:03:00', 'Bc4'), -- Player 1 bishop to c4
                                                                         (4, 18, '00:03:30', 'Nf6'); -- Player 2 knight to f6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 19, '00:02:00', 'c4'), -- Player 1 opens with pawn to c4
                                                                         (2, 19, '00:02:30', 'e6'), -- Player 2 responds with pawn to e6
                                                                         (3, 19, '00:03:00', 'Nc3'), -- Player 1 knight to c3
                                                                         (4, 19, '00:03:30', 'Bb4'); -- Player 2 bishop to b4

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 20, '00:02:00', 'd4'), -- Player 1 opens with pawn to d4
                                                                         (2, 20, '00:02:30', 'Nf6'), -- Player 2 responds with knight to f6
                                                                         (3, 20, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 20, '00:03:30', 'e6'); -- Player 2 pawn to e6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 21, '00:02:00', 'Nf3'), -- Player 1 opens with knight to f3
                                                                         (2, 21, '00:02:30', 'd5'), -- Player 2 responds with pawn to d5
                                                                         (3, 21, '00:03:00', 'g3'), -- Player 1 pawn to g3
                                                                         (4, 21, '00:03:30', 'c5'); -- Player 2 pawn to c5

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 22, '00:02:00', 'e4'), -- Player 1 opens with pawn to e4
                                                                         (2, 22, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 22, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 22, '00:03:30', 'Nc6'); -- Player 2 knight to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 23, '00:02:00', 'd4'), -- Player 1 opens with pawn to d4
                                                                         (2, 23, '00:02:30', 'd5'), -- Player 2 responds with pawn to d5
                                                                         (3, 23, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 23, '00:03:30', 'c6'); -- Player 2 pawn to c6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 24, '00:02:00', 'f4'), -- Player 1 opens with pawn to f4
                                                                         (2, 24, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 24, '00:03:00', 'Nf3'), -- Player 1 knight to f3
                                                                         (4, 24, '00:03:30', 'd6'); -- Player 2 pawn to d6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 25, '00:02:00', 'e4'), -- Player 1 opens with pawn to e4
                                                                         (2, 25, '00:02:30', 'e5'), -- Player 2 responds with pawn to e5
                                                                         (3, 25, '00:03:00', 'Bc4'), -- Player 1 bishop to c4
                                                                         (4, 25, '00:03:30', 'Nf6'); -- Player 2 knight to f6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 26, '00:02:00', 'c4'), -- Player 1 opens with pawn to c4
                                                                         (2, 26, '00:02:30', 'e6'), -- Player 2 responds with pawn to e6
                                                                         (3, 26, '00:03:00', 'Nc3'), -- Player 1 knight to c3
                                                                         (4, 26, '00:03:30', 'Bb4'); -- Player 2 bishop to b4

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 27, '00:02:00', 'd4'), -- Player 1 opens with pawn to d4
                                                                         (2, 27, '00:02:30', 'Nf6'), -- Player 2 responds with knight to f6
                                                                         (3, 27, '00:03:00', 'c4'), -- Player 1 pawn to c4
                                                                         (4, 27, '00:03:30', 'e6'); -- Player 2 pawn to e6

INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, 28, '00:02:00', 'Nf3'), -- Player 1 opens with knight to f3
                                                                         (2, 28, '00:02:30', 'd5'), -- Player 2 responds with pawn to d5
                                                                         (3, 28, '00:03:00', 'g3'), -- Player 1 pawn to g3
                                                                         (4, 28, '00:03:30', 'c5'); -- Player 2 pawn to c5

-- mtch pour stat

-- Nouveau match 1
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (1, 4, NULL, NULL, '2023-11-01 10:00:00', '2023-11-01 11:00:00', 'b', 20);

-- Nouveau match 2
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (2, 3, NULL, NULL, '2023-11-01 12:00:00', '2023-11-01 13:00:00', 'w', 20);

-- Nouveau match 3
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (3, 1, NULL, NULL, '2023-11-02 10:00:00', '2023-11-02 11:00:00', 'w', 20);

-- Nouveau match 4
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (4, 2, NULL, NULL, '2023-11-02 12:00:00', '2023-11-02 13:00:00', 'b', 20);

-- Nouveau match 5
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (1, 2, NULL, NULL, '2023-11-03 10:00:00', '2023-11-03 11:00:00', 'w', 20);

-- Nouveau match 6
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (3, 4, NULL, NULL, '2023-11-03 12:00:00', '2023-11-03 13:00:00', 'w', 20);

-- Nouveau match 7
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (2, 1, NULL, NULL, '2023-11-04 10:00:00', '2023-11-04 11:00:00', 'b', 20);

-- Nouveau match 8
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (4, 3, NULL, NULL, '2023-11-04 12:00:00', '2023-11-04 13:00:00', 'w', 20);

-- Nouveau match 9
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (1, 3, NULL, NULL, '2023-11-05 10:00:00', '2023-11-05 11:00:00', 'w', 20);

-- Nouveau match 10
INSERT INTO `Match` (player_black, player_white, tournament, tournament_state, start_date, end_date, winner, time) VALUES
    (2, 4, NULL, NULL, '2023-11-05 12:00:00', '2023-11-05 13:00:00', 'b', 20);

-- Ajout des mouvements pour chaque match
-- Match 1
INSERT INTO MatchMove (move_number, match_id, elapsed_time, move_id) VALUES
                                                                         (1, LAST_INSERT_ID(), '00:02:00', 'e4'),
                                                                         (2, LAST_INSERT_ID(), '00:02:30', 'e5'),
                                                                         (3, LAST_INSERT_ID(), '00:03:00', 'Nf3'),
                                                                         (4, LAST_INSERT_ID(), '00:03:30', 'Nc6'),
                                                                         (1, LAST_INSERT_ID() - 1, '00:02:00', 'd4'),
                                                                         (2, LAST_INSERT_ID() - 1, '00:02:30', 'd5'),
                                                                         (3, LAST_INSERT_ID() - 1, '00:03:00', 'c4'),
                                                                         (4, LAST_INSERT_ID() - 1, '00:03:30', 'c6'),
                                                                         (1, LAST_INSERT_ID() - 2, '00:02:00', 'f4'),
                                                                         (2, LAST_INSERT_ID() - 2, '00:02:30', 'e5'),
                                                                         (3, LAST_INSERT_ID() - 2, '00:03:00', 'Nf3'),
                                                                         (4, LAST_INSERT_ID() - 2, '00:03:30', 'd6'),
                                                                         (1, LAST_INSERT_ID() - 3, '00:02:00', 'e4'),
                                                                         (2, LAST_INSERT_ID() - 3, '00:02:30', 'e5'),
                                                                         (3, LAST_INSERT_ID() - 3, '00:03:00', 'Bc4'),
                                                                         (4, LAST_INSERT_ID() - 3, '00:03:30', 'Nf6'),
                                                                         (1, LAST_INSERT_ID() - 4, '00:02:00', 'c4'),
                                                                         (2, LAST_INSERT_ID() - 4, '00:02:30', 'e6'),
                                                                         (3, LAST_INSERT_ID() - 4, '00:03:00', 'Nc3'),
                                                                         (4, LAST_INSERT_ID() - 4, '00:03:30', 'Bb4'),
                                                                         (1, LAST_INSERT_ID() - 5, '00:02:00', 'd4'),
                                                                         (2, LAST_INSERT_ID() - 5, '00:02:30', 'Nf6'),
                                                                         (3, LAST_INSERT_ID() - 5, '00:03:00', 'c4'),
                                                                         (4, LAST_INSERT_ID() - 5, '00:03:30', 'e6'),
                                                                         (1, LAST_INSERT_ID() - 6, '00:02:00', 'Nf3'),
                                                                         (2, LAST_INSERT_ID() - 6, '00:02:30', 'd5'),
                                                                         (3, LAST_INSERT_ID() - 6, '00:03:00', 'g3'),
                                                                         (4, LAST_INSERT_ID() - 6, '00:03:30', 'c5'),
                                                                         (1, LAST_INSERT_ID() - 7, '00:02:00', 'e4'),
                                                                         (2, LAST_INSERT_ID() - 7, '00:02:30', 'e5'),
                                                                         (3, LAST_INSERT_ID() - 7, '00:03:00', 'Nf3'),
                                                                         (4, LAST_INSERT_ID() - 7, '00:03:30', 'Nc6'),
                                                                         (1, LAST_INSERT_ID() - 8, '00:02:00', 'd4'),
                                                                         (2, LAST_INSERT_ID() - 8, '00:02:30', 'd5'),
                                                                         (3, LAST_INSERT_ID() - 8, '00:03:00', 'c4'),
                                                                         (4, LAST_INSERT_ID() - 8, '00:03:30', 'c6'),
                                                                         (1, LAST_INSERT_ID() - 9, '00:02:00', 'f4'),
                                                                         (2, LAST_INSERT_ID() - 9, '00:02:30', 'e5'),
                                                                         (3, LAST_INSERT_ID() - 9, '00:03:00', 'Nf3'),
                                                                         (4, LAST_INSERT_ID() - 9, '00:03:30', 'd6');


