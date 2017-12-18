DROP TABLE IF EXISTS clients;
CREATE TABLE clients(
  id INTEGER NOT NULL AUTO_INCREMENT,
  first_name VARCHAR2(50),
  surname VARCHAR2(50),
  doc_id BIGINT,
  PRIMARY KEY (id)
);
INSERT INTO clients(first_name, surname, doc_id) VALUES ('Антон', 'Богослов', '1234567890');

DROP TABLE IF EXISTS requests;
CREATE TABLE requests(
  id INTEGER NOT NULL AUTO_INCREMENT,
  sum DECIMAL(15, 2),
  date DATE,
  duration INTEGER,
  monthly_charge DECIMAL(15, 2),
  client_id INTEGER,
  PRIMARY KEY (id)
);
INSERT INTO requests(sum, date, duration, client_id) VALUES ('100000','2017-12-06','6', '1');