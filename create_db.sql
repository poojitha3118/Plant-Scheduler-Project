
CREATE DATABASE IF NOT EXISTS plant_scheduler;
USE plant_scheduler;
CREATE TABLE IF NOT EXISTS plants (
    name VARCHAR(100) PRIMARY KEY,
    type VARCHAR(100),
    sunlight VARCHAR(100),
    water_frequency VARCHAR(100)
);
INSERT INTO plants (name, type, sunlight, water_frequency) VALUES
('Rose', 'Flowering', 'Full Sun', 'Every 2 days'),
('Tulip', 'Bulbous', 'Partial Sun', 'Every 3 days'),
('Cactus', 'Succulent', 'Bright Light', 'Every 10 days'),
('Lily', 'Flowering', 'Partial Shade', 'Every 4 days');
