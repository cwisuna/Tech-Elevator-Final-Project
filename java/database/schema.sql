BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, addresses, types, landmarks, hotels, routes, routes_landmarks, reviews;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE addresses (
    id serial PRIMARY KEY,
    street varchar,
    city varchar,
    state varchar,
    zip int
);
CREATE TABLE types(
    id int PRIMARY KEY,
    name varchar
);
CREATE TABLE landmarks (
    id serial PRIMARY KEY,
    address_id int REFERENCES addresses(id),
    likes int,
	name varchar,
    type int REFERENCES types(id),
	description varchar NOT NULL,
    img_URL varchar,
    is_pending boolean NULL
);
CREATE TABLE hotels (
    id serial PRIMARY KEY,
    address_id int REFERENCES addresses(id),
    name varchar
);

CREATE TABLE itinerary (
    id serial PRIMARY KEY,
    user_id int REFERENCES users(user_id),
    hotel_id int REFERENCES addresses(id)
);

CREATE TABLE landmarks_itinerary(
    itinerary_id int NOT NULL REFERENCES itinerary(id),
    landmark_id int NOT NULL REFERENCES landmarks(id),
    CONSTRAINT landmarks_itinerary_pk PRIMARY KEY(itinerary_id, landmark_id)
);

CREATE TABLE reviews(
    id serial PRIMARY KEY,
    landmark_id int REFERENCES landmarks(id),
    user_id int REFERENCES users(user_id),
    username varchar NULL,
    title varchar NOT NULL,
    is_liked boolean NOT NULL,
    description varchar NULL
);

COMMIT TRANSACTION;
