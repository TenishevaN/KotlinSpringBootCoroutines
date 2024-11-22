CREATE TABLE dog_breed (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    sub_breeds TEXT
);

-- If using a separate table for sub-breeds
CREATE TABLE sub_breed (
    id VARCHAR(255) PRIMARY KEY,
    dog_breed_id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (dog_breed_id) REFERENCES dog_breed(id)
);