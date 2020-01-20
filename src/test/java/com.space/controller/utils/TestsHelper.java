package com.space.controller.utils;

import com.space.controller.ShipOrder;
import com.space.model.ShipType;

import java.util.ArrayList;
import java.util.List;

public class TestsHelper {
    public final static String NORMAL_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";

    public final static String NORMAL_JSON_WITH_ID =
            "{" +
                    "\"id\": %s," +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";

    public final static String NORMAL_JSON_WITH_RATING =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14," +
                    "\"rating\": %s" +
                    "}";

    public final static String NO_SPEED_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"crewSize\": 14" +
                    "}";


    public static final String EMPTY_NAME_JSON =
            "{" +
                    "\"name\": \"\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";
    public static final String NEGATIVE_PROD_DATE_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : -32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";
    public static final String TOO_BIG_CREW_SIZE_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 300000000" +
                    "}";

    public static final String NEGATIVE_CREW_SIZE_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": -2" +
                    "}";

    public static final String TOO_BIG_PLANET_LENGTH_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"EarthEarthEarthEarthEarthEarthEarthEarthEarthEarthEarth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";
    public static final String NO_IS_USED_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";

    public static final String IS_USED_TRUE_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":true," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";

    public static final String IS_USED_FALSE_JSON =
            "{" +
                    "\"name\": \"123456789\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32998274577071," +
                    "\"isUsed\":false," +
                    "\"speed\":0.8," +
                    "\"crewSize\": 14" +
                    "}";

    public static final String CORRECT_COMPUTE_RATING_JSON =
            "{" +
                    "\"name\": \"test10\"," +
                    "\"planet\":\"Earth\"," +
                    "\"shipType\": \"MILITARY\"," +
                    "\"prodDate\" : 32872203569853," +
                    "\"isUsed\":true," +
                    "\"speed\":0.4," +
                    "\"crewSize\": 40" +
                    "}";

    public static final String JSON_SKELETON =
            "{" +
                    "\"name\": \"%s\"," +
                    "\"isUsed\":%s," +
                    "\"speed\":%s," +
                    "\"crewSize\": %s" +
                    "}";

    public static final String JSON_SKELETON_2 =
            "{" +
                    "\"planet\": \"%s\"," +
                    "\"shipType\": \"%s\"," +
                    "\"prodDate\": %s" +
                    "}";

    private List<ShipInfoTest> allShips = new ArrayList<>();

    public TestsHelper() {
        allShips.add(new ShipInfoTest(1L, "Orion III", "Mars", ShipType.MERCHANT, 32367628329660L, true, 0.82, 617, 1.31));
        allShips.add(new ShipInfoTest(2L, "Daedalus", "Jupiter", ShipType.MERCHANT, 32556844329665L, true, 0.94, 1619, 1.98));
        allShips.add(new ShipInfoTest(3L, "Eagle Transporter", "Earth", ShipType.TRANSPORT, 32178412329666L, true, 0.79, 4527, 1.02));
        allShips.add(new ShipInfoTest(4L, "F-302 Mongoose", "Neptune", ShipType.MILITARY, 32872204329667L, false, 0.24, 2170, 2.13));
        allShips.add(new ShipInfoTest(5L, "Excalibur", "Mercury", ShipType.MILITARY, 32872204329667L, false, 0.64, 128, 5.69));
        allShips.add(new ShipInfoTest(6L, "Explorer", "Saturn", ShipType.MERCHANT, 32746060329668L, false, 0.69, 4495, 4.25));
        allShips.add(new ShipInfoTest(7L, "Icarus I", "Mercury", ShipType.TRANSPORT, 32493772329668L, false, 0.08, 826, 0.27));
        allShips.add(new ShipInfoTest(8L, "Hermes", "Venus", ShipType.MERCHANT, 32840668329669L, false, 0.05, 445, 0.40));
        allShips.add(new ShipInfoTest(9L, "Odyssey", "Neptune", ShipType.TRANSPORT, 32146876329669L, false, 0.44, 1436, 1.10));
        allShips.add(new ShipInfoTest(10L, "Orbit Jet", "Venus", ShipType.TRANSPORT, 32872204329670L, false, 0.55, 1931, 4.89));
        allShips.add(new ShipInfoTest(11L, "Aries Ib", "Saturn", ShipType.MILITARY, 32935276329670L, true, 0.37, 3562, 2.11));
        allShips.add(new ShipInfoTest(12L, "Hunter IV", "Jupiter", ShipType.MILITARY, 32840668329670L, false, 0.71, 4379, 5.68));
        allShips.add(new ShipInfoTest(13L, "Serenity", "Saturn", ShipType.TRANSPORT, 32777596329671L, false, 0.92, 1588, 6.13));
        allShips.add(new ShipInfoTest(14L, "Scorpio E-X-1", "Mars", ShipType.MERCHANT, 32966812329671L, false, 0.03, 682, 0.40));
        allShips.add(new ShipInfoTest(15L, "Mark IX Hawk", "Jupiter", ShipType.MILITARY, 32619916329672L, true, 0.58, 927, 1.36));
        allShips.add(new ShipInfoTest(16L, "Excelsior", "Venus", ShipType.MILITARY, 32935276329672L, true, 0.45, 3488, 2.57));
        allShips.add(new ShipInfoTest(17L, "Amaterasu", "Saturn", ShipType.MILITARY, 32746060329672L, true, 0.88, 1517, 2.71));
        allShips.add(new ShipInfoTest(18L, "USS Cygnus", "Jupiter", ShipType.TRANSPORT, 32682988329673L, false, 0.74, 3129, 3.95));
        allShips.add(new ShipInfoTest(19L, "Argonaut", "Jupiter", ShipType.MERCHANT, 32588380329673L, false, 0.53, 4897, 2.36));
        allShips.add(new ShipInfoTest(20L, "Avalon", "Mars", ShipType.TRANSPORT, 32525308329674L, false, 0.91, 4660, 3.64));
        allShips.add(new ShipInfoTest(21L, "Arcadia", "Earth", ShipType.MILITARY, 32178412329674L, false, 0.07, 4271, 0.18));
        allShips.add(new ShipInfoTest(22L, "Red Dwarf", "Venus", ShipType.MERCHANT, 32209948329675L, true, 0.70, 3255, 0.93));
        allShips.add(new ShipInfoTest(23L, "Derelict", "Earth", ShipType.TRANSPORT, 32146876329675L, false, 0.75, 4419, 1.88));
        allShips.add(new ShipInfoTest(24L, "Terra V", "Saturn", ShipType.MERCHANT, 32935276329675L, false, 0.10, 1040, 1.14));
        allShips.add(new ShipInfoTest(25L, "Hyperion", "Uranus", ShipType.TRANSPORT, 32840668329676L, true, 0.79, 3987, 3.16));
        allShips.add(new ShipInfoTest(26L, "Normandy SR-1", "Saturn", ShipType.TRANSPORT, 33029884329678L, false, 0.91, 3749, 18.20));
        allShips.add(new ShipInfoTest(27L, "Battlestar", "Earth", ShipType.MILITARY, 32209948329679L, true, 0.55, 2307, 0.73));
        allShips.add(new ShipInfoTest(28L, "Conquistador", "Uranus", ShipType.MILITARY, 32209948329679L, false, 0.29, 315, 0.77));
        allShips.add(new ShipInfoTest(29L, "Titan", "Mars", ShipType.MERCHANT, 32588380329679L, true, 0.86, 1252, 1.91));
        allShips.add(new ShipInfoTest(30L, "Prometheus", "Saturn", ShipType.TRANSPORT, 32556844329680L, true, 0.14, 3841, 0.29));
        allShips.add(new ShipInfoTest(31L, "Centaur", "Saturn", ShipType.TRANSPORT, 32651452329680L, true, 0.62, 4277, 1.55));
        allShips.add(new ShipInfoTest(32L, "Venture Star", "Mercury", ShipType.MERCHANT, 32935276329680L, false, 0.59, 281, 6.74));
        allShips.add(new ShipInfoTest(33L, "Vorlon", "Neptune", ShipType.MERCHANT, 32556844329680L, true, 0.09, 3858, 0.19));
        allShips.add(new ShipInfoTest(34L, "Liberator", "Uranus", ShipType.MILITARY, 32998348329681L, false, 0.51, 3175, 8.16));
        allShips.add(new ShipInfoTest(35L, "Vulture", "Venus", ShipType.MERCHANT, 32304556329681L, true, 0.54, 1980, 0.80));
        allShips.add(new ShipInfoTest(36L, "Elysium", "Saturn", ShipType.MERCHANT, 32588380329681L, true, 0.66, 3865, 1.47));
        allShips.add(new ShipInfoTest(37L, "Nemesis", "Neptune", ShipType.MILITARY, 32493772329681L, true, 0.13, 1390, 0.25));
        allShips.add(new ShipInfoTest(38L, "Nostromo", "Saturn", ShipType.MERCHANT, 32241484329682L, true, 0.31, 1967, 0.43));
        allShips.add(new ShipInfoTest(39L, "Tardis", "Jupiter", ShipType.MERCHANT, 33029884329683L, false, 0.86, 4871, 17.20));
        allShips.add(new ShipInfoTest(40L, "Star Destroyer", "Mercury", ShipType.MILITARY, 33061420329683L, false, 0.92, 4880, 24.53));
    }

    public List<ShipInfoTest> getAllShips() {
        return allShips;
    }


    public List<ShipInfoTest> getShipInfosByName(String name, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.name.contains(name)) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByPlanet(String planet, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.planet.contains(planet)) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByShipType(ShipType type, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.shipType == type) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByAfter(Long after, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.prodDate >= after) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByBefore(Long before, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.prodDate <= before) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByIsUsed(Boolean isUsed, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.isUsed == isUsed) {
                result.add(ship);
            }
        }
        return result;
    }


    public List<ShipInfoTest> getShipInfosByMinSpeed(Double minSpeed, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.speed >= minSpeed) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByMaxSpeed(Double maxSpeed, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.speed <= maxSpeed) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByMinCrewSize(Integer minCrewSize, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.crewSize >= minCrewSize) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByMaxCrewSize(Integer maxCrewSize, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.crewSize <= maxCrewSize) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByMinRating(Double minRating, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.rating >= minRating) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByMaxRating(Double maxRating, List<ShipInfoTest> ships) {
        List<ShipInfoTest> result = new ArrayList<>();
        for (ShipInfoTest ship : ships) {
            if (ship.rating <= maxRating) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<ShipInfoTest> getShipInfosByOrder(ShipOrder order, List<ShipInfoTest> ships) {
        if (order == ShipOrder.ID) {
            ships.sort((o1, o2) -> (int) (o1.id - o2.id));
        } else if (order == ShipOrder.DATE) {
            ships.sort((o1, o2) -> (int) (o1.prodDate - o2.prodDate));
        } else if (order == ShipOrder.SPEED) {
            ships.sort((o1, o2) -> {
                if (o1.speed > o2.speed)
                    return 1;
                else if (o1.speed.equals(o2.speed))
                    return 0;
                else
                    return -1;
            });
        } else if (order == ShipOrder.RATING) {
            ships.sort((o1, o2) -> {
                if (o1.rating > o2.rating)
                    return 1;
                else if (o1.rating.equals(o2.rating))
                    return 0;
                else
                    return -1;
            });
        }

        return ships;
    }


    public List<ShipInfoTest> getShipInfosByPage(Integer pageNumber, Integer pageSize, List<ShipInfoTest> ships) {
        int skip = pageNumber * pageSize;
        List<ShipInfoTest> result = new ArrayList<>();
        for (int i = skip; i < Math.min(skip + pageSize, ships.size()); i++) {
            result.add(ships.get(i));
        }
        return result;
    }

    public ShipInfoTest getShipInfosById(long id) {
        return allShips.stream().filter(s -> s.id == id).findFirst().orElse(null);
    }
}