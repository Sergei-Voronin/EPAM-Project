-- add data into "users"

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Иван', 'Иванов', '7.62@inbox.ru', 'Ivan', '777');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Альберт', 'Петров', 'alb@inbox.ru', 'Alb', '7777');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Наталья', 'Сидорова', 'n_sid@gmail.com', 'Nataly', 'asd12');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Фёдор', 'Волков', 'volkov@mail.ru', 'Volkov', '342d');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Геннадий', 'Бирюков', 'gena@mail.ru', 'Gena', 'a3r4t');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Варвара', 'Трошина', 'var_765@list.ru', 'Varvara', '54tr');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Николай', 'Орлов', '24nic@bk.ru', 'Nikola', 'dzs4');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Алксандр', 'Спиваков', 'alex777@bk.ru', 'Alex', '4432');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Ольга', 'Фролова', 'oly12@bk.ru', 'Oly', '34324');

INSERT INTO users (first_name, last_name, email, login, password)
VALUES ('Виктор', 'Суворов', 'suvorov81@bk.ru', 'Suvorov', '4rer');


-- add data into "products"
INSERT INTO products (user_id, description, price, auction_timer)
VALUES (1, 'Картридж с игрой "Adventure Island 3"', 250.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (1, 'Картридж с игрой "Aladdin 3"', 270.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (2, 'Картридж с игрой "Ariel Mermaid"', 190.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (2, 'Картридж с игрой "Batman"', 290.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (3, 'Картридж с игрой "BattleToads"', 310.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (3, 'Картридж с игрой "BattleToads Double Dragon"', 310.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (4, 'Картридж с игрой "Felix the Cat"', 550.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (4, 'Картридж с игрой "Castlevania 1,2 части"', 1100.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (5, 'Картридж с игрой "Sonic The Hedgehog"', 200.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (5, 'Картридж с игрой "Mortal kombat"', 300.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (6, 'Картридж с игрой "Duck Tales"', 240.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (6, 'Картридж с игрой "Duck Tales 2"', 240.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (7, 'Картридж с игрой "Double Dragon 4"', 350.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (7, 'Картридж с игрой "Super Mario Bros"', 310.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (8, 'Картридж с игрой "Prince of Persia"', 400.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (8, 'Картридж с игрой "Lion King"', 250.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (9, 'Картридж с игрой "Darkwing Duck"', 290.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (9, 'Картридж с игрой "Contra Force"', 540.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (10, 'Картридж с игрой "Chip and Dale"', 700.00, current_timestamp(0) + time '24:00');

INSERT INTO products (user_id, description, price, auction_timer)
VALUES (10, 'Картридж с игрой "Tom and Jerry"', 200.00, current_timestamp(0) + time '24:00');


-- add data into "bets"
INSERT INTO bets (product_id, user_id, bet)
VALUES (1, 2, 50.00);

INSERT INTO bets (product_id, user_id, bet)
VALUES (4, 7, 30.00);

INSERT INTO bets (product_id, user_id, bet)
VALUES (3, 1, 100.00);

INSERT INTO bets (product_id, user_id, bet)
VALUES (12, 5, 140.00);

INSERT INTO bets (product_id, user_id, bet)
VALUES (9, 7, 20.00);

INSERT INTO bets (product_id, user_id, bet)
VALUES (11, 9, 70.00);