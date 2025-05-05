CREATE DATABASE budgetdb;
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE ROLE keycloak WITH LOGIN PASSWORD 'keycloak';

CREATE DATABASE keycloak;

\c keycloak

GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;

-- Enable required extensions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Grant privileges on the public schema
GRANT ALL ON SCHEMA public TO keycloak;
GRANT ALL ON ALL TABLES IN SCHEMA public TO keycloak;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO keycloak;
GRANT ALL ON ALL FUNCTIONS IN SCHEMA public TO keycloak;

-- Set default privileges for future objects
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO keycloak;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO keycloak;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON FUNCTIONS TO keycloak;

-- Set the search path
SET search_path TO public;

