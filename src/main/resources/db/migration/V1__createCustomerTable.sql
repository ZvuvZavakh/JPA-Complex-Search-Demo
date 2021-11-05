create table customers
(
    id                   varchar not null
        constraint customer_pkey
            primary key,
    created_date         timestamp,
    modified_date        timestamp,
    first_name           varchar(100),
    last_name            varchar(100),
    age                  integer,
    is_active            boolean,
    version              bigint
);