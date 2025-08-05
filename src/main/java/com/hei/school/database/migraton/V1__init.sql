-- V1__init.sql

do $$
begin
    if not exists (select from pg_type where typname = 'payment_status') then
create type "payment_status" as enum ('VERIFYING', 'SUCCEEDED', 'FAILED');
end if;
end
$$;

create table if not exists don (
                                   id               uuid primary key,
                                   nom_complet      varchar,
                                   email            varchar,
                                   date_paiement    timestamp,
                                   montant          numeric,
                                   moyen_paiement   varchar,
                                   status           payment_status,
                                   vola_payment_id  varchar
);

create table if not exists aide (
                                    id               uuid primary key,
                                    nom_complet      varchar,
                                    email            varchar,
                                    date_paiement    timestamp,
                                    montant          numeric,
                                    moyen_paiement   varchar,
                                    description      text,
                                    status           payment_status,
                                    vola_payment_id  varchar
);
