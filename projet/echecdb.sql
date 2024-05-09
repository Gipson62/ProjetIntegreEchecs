DROP TABLE IF EXISTS Defense;
DROP TABLE IF EXISTS Attack;
DROP TABLE IF EXISTS Opening;
DROP TABLE IF EXISTS MatchMove;
DROP TABLE IF EXISTS Move;
DROP TABLE IF EXISTS `Match`;
DROP TABLE IF EXISTS Tournament;
DROP TABLE IF EXISTS TournamentState;
DROP TABLE IF EXISTS FriendShip;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS `Rank`;

CREATE TABLE `Rank` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(86) NOT NULL,
    name VARCHAR(11) NOT NULL,
	CONSTRAINT CHK_rank_name CHECK (
        name IN ('Fer', 'Bronze', 'Argent', 'Or', 'Platine', 'Diamant')
    )
);

CREATE TABLE Account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    username VARCHAR(24) NOT NULL,
    `rank` INT,
    FOREIGN KEY (`rank`) REFERENCES `Rank`(id),
    birthdate DATE NOT NULL,
    is_beginner BIT(1) NOT NULL,
    elo INT NOT NULL,
    password VARCHAR(24) NOT NULL,
    bio VARCHAR(256),
    tag INT NOT NULL,
    gender VARCHAR(16),
    CONSTRAINT CHK_email_format CHECK (email LIKE '%_@_%.__%'),
    CONSTRAINT CHK_is_beginner CHECK (is_beginner IN (0, 1)),
    CONSTRAINT CHK_elo CHECK (elo >= 0 AND elo <= 3000),
    CONSTRAINT UC_tag_username UNIQUE (tag, username),
    CONSTRAINT UC_email UNIQUE (email)
);

CREATE TABLE FriendShip (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Friend_1 INT,
    Friend_2 INT,
    dateRelation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Friend_1) REFERENCES Account(id) ON UPDATE CASCADE,
    FOREIGN KEY (Friend_2) REFERENCES Account(id) ON UPDATE CASCADE,
    CONSTRAINT UC_Friend_1_Friend_2 UNIQUE (Friend_1, Friend_2)
);

CREATE TABLE TournamentState (
    id INT PRIMARY KEY,
    state VARCHAR(27) NOT NULL,
	CONSTRAINT CHK_tournament_state CHECK (state IN ('final', 'demi-final', 'quart-final', 'huitième-final', 'qualifications')),
	CONSTRAINT UNQ_tournament_state UNIQUE (state)
);

CREATE TABLE Tournament (
    id INT PRIMARY KEY AUTO_INCREMENT,
    elo INT NOT NULL CONSTRAINT CHK_elo_Tournament CHECK (elo >= 0 AND elo <= 3000),
    name VARCHAR(31) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE `Match` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    player_black INT,
    player_white INT,
    tournament INT,
    tournament_state INT,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    winner CHAR(1),
    time INT NOT NULL default 20,
    FOREIGN KEY (player_black) REFERENCES Account(id),
    FOREIGN KEY (player_white) REFERENCES Account(id),
    FOREIGN KEY (tournament) REFERENCES Tournament(id),
    FOREIGN KEY (tournament_state) REFERENCES TournamentState(id),
    CONSTRAINT CHK_players_diff CHECK (player_black <> player_white),
    CONSTRAINT CHK_winner CHECK (winner IN ('b', 'w'))
);

CREATE TABLE Move (
    id VARCHAR(8) PRIMARY KEY
);

CREATE TABLE MatchMove (
    id INT PRIMARY KEY AUTO_INCREMENT,
    move_number INT NOT NULL,
    match_id INT,
    elapsed_time TIME,
    move_id VARCHAR(8) NOT NULL,
    FOREIGN KEY (match_id) REFERENCES `Match`(id),
    FOREIGN KEY (move_id) REFERENCES Move(id),
	CONSTRAINT UC_move_number_match_id UNIQUE (move_number, match_id)
);

CREATE TABLE Opening (
    id INT PRIMARY KEY AUTO_INCREMENT,
    move1 VARCHAR(8) NOT NULL,
    move2 VARCHAR(8) NOT NULL,
    move3 VARCHAR(8) NOT NULL,
    move4 VARCHAR(8) NOT NULL,
    name VARCHAR(35) NOT NULL,
    description VARCHAR(152) NOT NULL,
    FOREIGN KEY (move1) REFERENCES Move(id),
    FOREIGN KEY (move2) REFERENCES Move(id),
    FOREIGN KEY (move3) REFERENCES Move(id),
    FOREIGN KEY (move4) REFERENCES Move(id),
    CONSTRAINT UC_unique_moves_combination UNIQUE (move1, move2, move3, move4)
);

CREATE TABLE Attack (
    move1 VARCHAR(8) PRIMARY KEY,
    name VARCHAR(35) NOT NULL,
    description VARCHAR(152) NOT NULL,
    FOREIGN KEY (move1) REFERENCES Move(id)
);

CREATE TABLE Defense (
    id INT PRIMARY KEY AUTO_INCREMENT,
    move1 VARCHAR(8) NOT NULL,
    move2 VARCHAR(8) NOT NULL,
    name VARCHAR(35) NOT NULL,
    description VARCHAR(152) NOT NULL,
    FOREIGN KEY (move1) REFERENCES Move(id),
    FOREIGN KEY (move2) REFERENCES Move(id),
    CONSTRAINT UC_unique_defense_moves UNIQUE (move1, move2)
);
