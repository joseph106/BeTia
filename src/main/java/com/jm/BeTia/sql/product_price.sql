-- Table: public.product_price

-- DROP TABLE IF EXISTS public.product_price;

CREATE TABLE IF NOT EXISTS public.product_price
(
    id bigint NOT NULL DEFAULT nextval('product_price_id_seq'::regclass),
    max_dues_no double precision,
    incremented_price double precision,
    sku character varying(9) COLLATE pg_catalog."default",
    CONSTRAINT product_price_pkey PRIMARY KEY (id),
    CONSTRAINT uk_h0tiak0d2tf4io6um7hy7eib9 UNIQUE (sku),
    CONSTRAINT fkdy82pqcba3u60qqxga4smtxyt FOREIGN KEY (sku)
    REFERENCES public.product (sku) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product_price
    OWNER to postgres;