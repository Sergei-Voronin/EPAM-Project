-- создаём таблицу "users"
CREATE TABLE public."users"
(
    "user_id" integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    "first_name" character varying(20),
    "last_name" character varying(20),
    "email" character varying(20) UNIQUE NOT NULL,
    "login" character varying(20) UNIQUE NOT NULL,
    "password" character varying(20) NOT NULL,
    PRIMARY KEY ("user_id")
);


-- создаём таблицу "products"
CREATE TABLE public."products"
(
    "product_id" integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    "user_id" integer NOT NULL,
    "description" character varying(200),
    "price" numeric,
    "auction_timer" timestamp without time zone,
    PRIMARY KEY ("product_id"),
    CONSTRAINT "fc_user_id" FOREIGN KEY ("user_id")
        REFERENCES public."users" ("user_id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

	
-- создаём таблицу "bets"
CREATE TABLE public."bets"
(
    "bet_id" integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    "product_id" integer NOT NULL,
    "user_id" integer NOT NULL,
    "bet" numeric NOT NULL,
    PRIMARY KEY ("bet_id"),
    CONSTRAINT "fc_product_id" FOREIGN KEY ("product_id")
        REFERENCES public."products" ("product_id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "fc_user_id" FOREIGN KEY ("user_id")
        REFERENCES public."users" ("user_id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);