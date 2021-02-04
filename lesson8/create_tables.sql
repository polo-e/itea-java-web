CREATE TABLE category(
   id SERIAL PRIMARY KEY,
   name CHARACTER VARYING
);

CREATE TABLE products(
   id SERIAL PRIMARY KEY,
   name CHARACTER VARYING,
   description CHARACTER VARYING,
   price INT,
   category_id INT,
   FOREIGN KEY(category_id) 
   REFERENCES category(id)	
);