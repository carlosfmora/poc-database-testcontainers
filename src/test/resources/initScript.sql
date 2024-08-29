CREATE TABLE public.customer
(
    id      serial4     NOT NULL,
    name    VARCHAR     NOT NULL,
    CONSTRAINT customer_unique UNIQUE (name),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);
