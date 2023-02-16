DROP TABLE IF EXISTS subscriber;
DROP TABLE IF EXISTS loans;

CREATE TABLE subscriber (
  id INT AUTO_INCREMENT PRIMARY KEY,
  msisdn VARCHAR(20) NOT NULL UNIQUE,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL
);

CREATE TABLE loans (
  id INT AUTO_INCREMENT PRIMARY KEY,
  subscriber_id INT NOT NULL,
  amount DECIMAL(10, 2) NOT NULL,
  due_date VARCHAR(255),
  loan_status VARCHAR(255),
  repay_amount NUMERIC(19,2),
  subcriber_msisdn VARCHAR(255),
  FOREIGN KEY (subscriber_id) REFERENCES subscriber(id)
);