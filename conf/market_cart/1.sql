# --- !Ups

CREATE TABLE IF NOT EXISTS Users (
    Id varchar(255) NOT NULL,
    Password varchar(255) NOT NULL,
    Email varchar(255) NOT NULL,
    First_name varchar(255) NOT NULL,
    Last_name varchar(255) NOT NULL,
    Creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (Id));

# --- !Downs
DROP TABLE Users;