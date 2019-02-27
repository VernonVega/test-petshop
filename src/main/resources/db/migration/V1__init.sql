CREATE SEQUENCE clientIdSeq;
CREATE TABLE IF NOT EXISTS client
(
    clientId     INTEGER DEFAULT nextval('clientIdSeq')
    CONSTRAINT client_pkey
    PRIMARY KEY,
  clientName VARCHAR(25) NOT NULL,
  clientPhone   VARCHAR(255),
  clientEmail   VARCHAR(255),
  clientIsWholesale   boolean  NOT NULL,
  age NUMERIC (3)
);

CREATE SEQUENCE clientAddressIdSeq;
CREATE TABLE IF NOT EXISTS clientAddress
(

  clientAddressId INTEGER DEFAULT nextval('clientAddressIdSeq')
  CONSTRAINT client_address_pkey
    PRIMARY KEY,
    clientId INTEGER NOT NULL,
    clientAddressCity VARCHAR(255)
    );

INSERT INTO client (id, enabled, title, type) VALUES (101117185, FALSE, NULL, 'private');
INSERT INTO client (id, enabled, title, type) VALUES (453961806, FALSE, NULL, 'private');
INSERT INTO client (id, enabled, title, type) VALUES (-1001096085963, FALSE, 'Awesome Events', 'supergroup');
INSERT INTO client (id, enabled, title, type) VALUES (14515, TRUE, 'Test', 'private');
INSERT INTO client (id, enabled, title, type) VALUES (21, TRUE, 'Dev', 'private');