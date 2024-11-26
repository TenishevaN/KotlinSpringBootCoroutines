CREATE TABLE dogBreeds(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE subBreeds(
    id SERIAL PRIMARY KEY,
    breed_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (breed_id) REFERENCES dogBreeds(id)
);

CREATE TABLE dogBreedImage(
    id SERIAL PRIMARY KEY,
    breed_id INTEGER NOT NULL,
    image BYTEA
);
