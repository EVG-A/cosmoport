package com.space.service;

import com.space.NotFoundException;
import com.space.controller.ShipOrder;

import com.space.BadRequestException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShipService {

    @Autowired
    ShipRepo shipRepo;

    public List<Ship> getFilteredShips(String name,
                                       String planet,
                                       ShipType shipType,
                                       Long after,
                                       Long before,
                                       Boolean isUsed,
                                       Double minSpeed,
                                       Double maxSpeed,
                                       Integer minCrewSize,
                                       Integer maxCrewSize,
                                       Double minRating,
                                       Double maxRating) {

        List<Ship> allShips = shipRepo.findAll();

        List<Ship> filteredShips = new ArrayList<>();

        for (Ship ship : allShips) {
            if (name != null && !ship.getName().contains(name)) continue;
            if (planet != null && !ship.getPlanet().contains(planet)) continue;
            if (shipType != null && ship.getShipType() != shipType) continue;
            if (after != null && ship.getProdDate().before(new Date(after))) continue;
            if (before != null && ship.getProdDate().after(new Date(before - 3600001))) continue;
            if (isUsed != null && ship.getUsed().booleanValue() != isUsed.booleanValue()) continue;
            if (minSpeed != null && ship.getSpeed().compareTo(minSpeed) < 0) continue;
            if (maxSpeed != null && ship.getSpeed().compareTo(maxSpeed) > 0) continue;
            if (minCrewSize != null && ship.getCrewSize().compareTo(minCrewSize) < 0) continue;
            if (maxCrewSize != null && ship.getCrewSize().compareTo(maxCrewSize) > 0) continue;
            if (minRating != null && ship.getRating().compareTo(minRating) < 0) continue;
            if (maxRating != null && ship.getRating().compareTo(maxRating) > 0) continue;
            filteredShips.add(ship);
        }
        return filteredShips;
    }

    public List<Ship> sortShips(List<Ship> ships, ShipOrder order) {
        if (order != null) {
            ships.sort((ship1, ship2) -> {
                switch (order) {
                    case ID:
                        return ship1.getId().compareTo(ship2.getId());
                    case SPEED:
                        return ship1.getSpeed().compareTo(ship2.getSpeed());
                    case DATE:
                        return ship1.getProdDate().compareTo(ship2.getProdDate());
                    case RATING:
                        return ship1.getRating().compareTo(ship2.getRating());
                    default:
                        return 0;
                }
            });
        }
        return ships;
    }

    public List<Ship> getPage(List<Ship> ships, Integer pageNumber, Integer pageSize) {
        int page = pageNumber == null ? 0 : pageNumber;
        int size = pageSize == null ? 3 : pageSize;
        int from = page * size;
        int to = from + size;
        if (to > ships.size()) to = ships.size();
        return ships.subList(from, to);
    }

    public void checkProdDate(Ship ship) {
        if (ship.getProdDate().getTime() < 0) {
            throw new BadRequestException();
        }

        Calendar dateMin = Calendar.getInstance();
        Calendar dateMax = Calendar.getInstance();

        dateMin.set(2800, Calendar.JANUARY, 1);
        dateMax.set(3019, Calendar.DECEMBER, 31);

        if (ship.getProdDate().getTime() < dateMin.getTimeInMillis() || ship.getProdDate().getTime() > dateMax.getTimeInMillis()) {
            throw new BadRequestException();
        }
    }

    public Double calculateRating(Ship ship) {

        if (ship.getProdDate() != null && ship.getSpeed() != null) {

            Calendar prodCal = Calendar.getInstance();
            prodCal.setTime(ship.getProdDate());
            int prodYear = prodCal.get(Calendar.YEAR);
            double k = ship.getUsed() ? 0.5 : 1.0;

            double rating = (80 * ship.getSpeed() * k) / (3020 - prodYear);

            return (double) Math.round(rating * 100) / 100;
        }
        return null;
    }

    public Ship checkShipAndGetNew(Ship ship) {
        if (ship == null) {
            return null;
        }
        if (ship.getUsed() == null) {
            ship.setUsed(false);
        } else {
            ship.setUsed(ship.getUsed());
        }
        if (ship.getName() == null
                || ship.getPlanet() == null
                || ship.getShipType() == null
                || ship.getProdDate() == null
                || ship.getSpeed() == null
                || ship.getCrewSize() == null) {
            throw new BadRequestException();
        }

        if (ship.getName().equals("")
                || ship.getPlanet().equals("")
                || ship.getPlanet().length() > 50
                || ship.getName().length() > 50
                || ship.getSpeed() < 0.01
                || ship.getSpeed() > 0.99
                || ship.getCrewSize() < 1
                || ship.getCrewSize() > 9999) {
            throw new BadRequestException();
        }

        checkProdDate(ship);
        Double rating = calculateRating(ship);
        ship.setRating(rating);
        return ship;
    }

    public boolean updateShip(Ship updatedShip, Ship ship) {
        try {
            ship.equals(new Ship());
        } catch (Exception e) {
            return false;
        }

        if (updatedShip != null) {
            if (ship.getName() != null) {
                if (ship.getName().equals("") || ship.getName().length() > 50) {
                    throw new BadRequestException();
                }
                updatedShip.setName(ship.getName());
            }

            if (ship.getPlanet() != null) {
                if (ship.getPlanet().equals("") || ship.getPlanet().length() > 50) {
                    throw new BadRequestException();
                }
                updatedShip.setPlanet(ship.getPlanet());
            }

            if (ship.getShipType() != null) {
                updatedShip.setShipType(ship.getShipType());
            }

            if (ship.getProdDate() != null) {
                checkProdDate(ship);
                updatedShip.setProdDate(ship.getProdDate());
            }

            if (ship.getUsed() != null) {
                updatedShip.setUsed(ship.getUsed());
            } else {
                updatedShip.setUsed(false);
            }

            if (ship.getSpeed() != null) {
                updatedShip.setSpeed(ship.getSpeed());
            }

            if (ship.getCrewSize() != null) {
                if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) {
                    throw new BadRequestException();
                }
                updatedShip.setCrewSize(ship.getCrewSize());
            }

            updatedShip.setRating(calculateRating(updatedShip));
            return true;
        } else {
            return false;
        }
    }

    public Ship getShipById(Long id) {
        if (id == 0) {
            throw new BadRequestException();
        }
        return shipRepo.findAll().stream()
                .filter(ship -> ship.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
        //return shipRepo.findById(id).orElse(null);
    }
}
