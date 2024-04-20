CREATE TABLE events (
  id              INTEGER NOT NULL PRIMARY KEY,
  entity_id       INTEGER NOT NULL,
  version         INTEGER NOT NULL,
  type            VARCHAR NOT NULL,
  data            VARCHAR NOT NULL,
  created_at      DATETIME(6)  NOT NULL,

  UNIQUE (entity_id, version)
);

-- cat schema.sql | sqlite3 p5.db
-- sqlite> .tables
-- sqlite> select * from events;
-- sqlite> .schema events
