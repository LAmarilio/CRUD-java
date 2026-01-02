CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE pessoas (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at timestamptz DEFAULT now(),
    nome text NOT NULL,
    idade int NOT NULL,
    cidade text NOT NULL,
    estado char(2) NOT NULL,
    pais text NOT NULL
);