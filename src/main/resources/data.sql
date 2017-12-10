DROP TABLE IF EXISTS requests;

CREATE TABLE requests(
  id INTEGER NOT NULL AUTO_INCREMENT,
  sum DECIMAL(15, 2),
  date DATE,
  duration INTEGER,
  monthly_charge DECIMAL(15, 2),
  PRIMARY KEY (id)
);

INSERT INTO requests(sum, date, duration) VALUES ('100000','2017-12-06','6');