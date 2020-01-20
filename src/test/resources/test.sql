DROP TABLE IF EXISTS ship;

CREATE TABLE ship
(
    id       BIGINT(20)  NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NULL,
    planet   VARCHAR(50) NULL,
    shipType VARCHAR(9)  NULL,
    prodDate date        NULL,
    isUsed   BIT(1)      NULL,
    speed    DOUBLE      NULL,
    crewSize INT(4)      NULL,
    rating   DOUBLE      NULL,
    PRIMARY KEY (id)
);

INSERT INTO ship(name, planet, shipType, prodDate, isUsed, speed, crewSize, rating)
VALUES ('Orion III', 'Mars', 'MERCHANT', '2995-01-01', true, 0.82, 617, 1.31)
     , ('Daedalus', 'Jupiter', 'MERCHANT', '3001-01-01', true, 0.94, 1619, 1.98)
     , ('Eagle Transporter', 'Earth', 'TRANSPORT', '2989-01-01', true, 0.79, 4527, 1.02)
     , ('F-302 Mongoose', 'Neptune', 'MILITARY', '3011-01-01', false, 0.24, 2170, 2.13)
     , ('Excalibur', 'Mercury', 'MILITARY', '3011-01-01', false, 0.64, 128, 5.69)
     , ('Explorer', 'Saturn', 'MERCHANT', '3007-01-01', false, 0.69, 4495, 4.25)
     , ('Icarus I', 'Mercury', 'TRANSPORT', '2999-01-01', false, 0.08, 826, 0.27)
     , ('Hermes', 'Venus', 'MERCHANT', '3010-01-01', false, 0.05, 445, 0.40)
     , ('Odyssey', 'Neptune', 'TRANSPORT', '2988-01-01', false, 0.44, 1436, 1.10)
     , ('Orbit Jet', 'Venus', 'TRANSPORT', '3011-01-01', false, 0.55, 1931, 4.89)
     , ('Aries Ib', 'Saturn', 'MILITARY', '3013-01-01', true, 0.37, 3562, 2.11)
     , ('Hunter IV', 'Jupiter', 'MILITARY', '3010-01-01', false, 0.71, 4379, 5.68)
     , ('Serenity', 'Saturn', 'TRANSPORT', '3008-01-01', false, 0.92, 1588, 6.13)
     , ('Scorpio E-X-1', 'Mars', 'MERCHANT', '3014-01-01', false, 0.03, 682, 0.40)
     , ('Mark IX Hawk', 'Jupiter', 'MILITARY', '3003-01-01', true, 0.58, 927, 1.36)
     , ('Excelsior', 'Venus', 'MILITARY', '3013-01-01', true, 0.45, 3488, 2.57)
     , ('Amaterasu', 'Saturn', 'MILITARY', '3007-01-01', true, 0.88, 1517, 2.71)
     , ('USS Cygnus', 'Jupiter', 'TRANSPORT', '3005-01-01', false, 0.74, 3129, 3.95)
     , ('Argonaut', 'Jupiter', 'MERCHANT', '3002-01-01', false, 0.53, 4897, 2.36)
     , ('Avalon', 'Mars', 'TRANSPORT', '3000-01-01', false, 0.91, 4660, 3.64)
     , ('Arcadia', 'Earth', 'MILITARY', '2989-01-01', false, 0.07, 4271, 0.18)
     , ('Red Dwarf', 'Venus', 'MERCHANT', '2990-01-01', true, 0.70, 3255, 0.93)
     , ('Derelict', 'Earth', 'TRANSPORT', '2988-01-01', false, 0.75, 4419, 1.88)
     , ('Terra V', 'Saturn', 'MERCHANT', '3013-01-01', false, 0.10, 1040, 1.14)
     , ('Hyperion', 'Uranus', 'TRANSPORT', '3010-01-01', true, 0.79, 3987, 3.16)
     , ('Normandy SR-1', 'Saturn', 'TRANSPORT', '3016-01-01', false, 0.91, 3749, 18.20)
     , ('Battlestar', 'Earth', 'MILITARY', '2990-01-01', true, 0.55, 2307, 0.73)
     , ('Conquistador', 'Uranus', 'MILITARY', '2990-01-01', false, 0.29, 315, 0.77)
     , ('Titan', 'Mars', 'MERCHANT', '3002-01-01', true, 0.86, 1252, 1.91)
     , ('Prometheus', 'Saturn', 'TRANSPORT', '3001-01-01', true, 0.14, 3841, 0.29)
     , ('Centaur', 'Saturn', 'TRANSPORT', '3004-01-01', true, 0.62, 4277, 1.55)
     , ('Venture Star', 'Mercury', 'MERCHANT', '3013-01-01', false, 0.59, 281, 6.74)
     , ('Vorlon', 'Neptune', 'MERCHANT', '3001-01-01', true, 0.09, 3858, 0.19)
     , ('Liberator', 'Uranus', 'MILITARY', '3015-01-01', false, 0.51, 3175, 8.16)
     , ('Vulture', 'Venus', 'MERCHANT', '2993-01-01', true, 0.54, 1980, 0.80)
     , ('Elysium', 'Saturn', 'MERCHANT', '3002-01-01', true, 0.66, 3865, 1.47)
     , ('Nemesis', 'Neptune', 'MILITARY', '2999-01-01', true, 0.13, 1390, 0.25)
     , ('Nostromo', 'Saturn', 'MERCHANT', '2991-01-01', true, 0.31, 1967, 0.43)
     , ('Tardis', 'Jupiter', 'MERCHANT', '3016-01-01', false, 0.86, 4871, 17.20)
     , ('Star Destroyer', 'Mercury', 'MILITARY', '3017-01-01', false, 0.92, 4880, 24.53);