CREATE TABLE IF NOT EXISTS chat
(
  id      BIGINT  NOT NULL
    CONSTRAINT chat_pkey
    PRIMARY KEY,
  enabled BOOLEAN NOT NULL,
  title   VARCHAR(255),
  type    VARCHAR(255)
);

INSERT INTO chat (id, enabled, title, type) VALUES (101117185, FALSE, NULL, 'private');
INSERT INTO chat (id, enabled, title, type) VALUES (453961806, FALSE, NULL, 'private');
INSERT INTO chat (id, enabled, title, type) VALUES (-1001096085963, FALSE, 'Awesome Events', 'supergroup');
INSERT INTO chat (id, enabled, title, type) VALUES (14515, TRUE, 'Test', 'private');
INSERT INTO chat (id, enabled, title, type) VALUES (21, TRUE, 'Dev', 'private');