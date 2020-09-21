CREATE TABLE doctors (
    id BIGINT IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL ,
    specialization VARCHAR(255) NOT NULL
);

CREATE TABLE patients (
    id BIGINT IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL ,
    phone VARCHAR(255) NOT NULL
);

CREATE TABLE recipes (
    id BIGINT IDENTITY PRIMARY KEY NOT NULL ,
    patientId BIGINT NOT NULL,
    doctorId BIGINT NOT NULL,
    description VARCHAR(1023) NOT NULL,
    creationDate DATE NOT NULL,
    validityInDays INTEGER NOT NULL,
    priority INTEGER NOT NULL,
    FOREIGN KEY (patientId) REFERENCES patients(id),
    FOREIGN KEY (doctorId) REFERENCES doctors(id)
);