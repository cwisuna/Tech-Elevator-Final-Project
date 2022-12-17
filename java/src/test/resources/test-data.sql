BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO addresses (street, city, state, zip) VALUES ('2400 3rd Ave', 'Minneapolis', 'MN', 55404);
INSERT INTO addresses (street, city, state, zip) VALUES ('60 E Broadway', 'Bloomington', 'MN', 55425);

COMMIT TRANSACTION;
