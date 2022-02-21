#!/usr/bin/env sh
export POSTGRES_USER=postgres
export POSTGRES_PASSWORD=postgres
export PGADMIN_DEFAULT_EMAIL=pgadmin@sdrm.pl
export PGADMIN_DEFAULT_PASSWORD=postgres
export APP_DB_NAME=sdrm

docker-compose up