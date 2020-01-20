package com.space.controller;


import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepo;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipController {

    @Autowired
    private ShipRepo shipRepo;

    @Autowired
    private ShipService shipService;

    @RequestMapping(path = "/rest/ships", method = RequestMethod.GET)
    public List<Ship> getSortedShipsList(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String planet,
                                         @RequestParam(required = false) ShipType shipType,
                                         @RequestParam(required = false) Long after,
                                         @RequestParam(required = false) Long before,
                                         @RequestParam(required = false) Boolean isUsed,
                                         @RequestParam(required = false) Double minSpeed,
                                         @RequestParam(required = false) Double maxSpeed,
                                         @RequestParam(required = false) Integer minCrewSize,
                                         @RequestParam(required = false) Integer maxCrewSize,
                                         @RequestParam(required = false) Double minRating,
                                         @RequestParam(required = false) Double maxRating,
                                         @RequestParam(required = false) ShipOrder order,
                                         @RequestParam(required = false) Integer pageNumber,
                                         @RequestParam(required = false) Integer pageSize) {

        List<Ship> filteredShips = shipService.getFilteredShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating);
        List<Ship> sortedShips = shipService.sortShips(filteredShips, order);
        return shipService.getPage(sortedShips, pageNumber, pageSize);
    }

    @RequestMapping(path = "rest/ships/count", method = RequestMethod.GET)
    public Integer count(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String planet,
                         @RequestParam(required = false) ShipType shipType,
                         @RequestParam(required = false) Long after,
                         @RequestParam(required = false) Long before,
                         @RequestParam(required = false) Boolean isUsed,
                         @RequestParam(required = false) Double minSpeed,
                         @RequestParam(required = false) Double maxSpeed,
                         @RequestParam(required = false) Integer minCrewSize,
                         @RequestParam(required = false) Integer maxCrewSize,
                         @RequestParam(required = false) Double minRating,
                         @RequestParam(required = false) Double maxRating) {

        return shipService.getFilteredShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating).size();
    }

    @RequestMapping(path = "/rest/ships/{id}", method = RequestMethod.GET)
    public Ship getShip(@PathVariable Long id) {
        return shipService.getShipById(id);
    }

    @RequestMapping(path = "/rest/ships", method = RequestMethod.POST)
    public @ResponseBody
    Ship createShip(@RequestBody Ship ship) {
        Ship newShip = shipService.checkShipAndGetNew(ship);
        shipRepo.save(newShip);
        return newShip;
    }

    @RequestMapping(path = "/rest/ships/{id}", method = RequestMethod.POST)
    public @ResponseBody
    Ship updateShip(@RequestBody Ship jsonBody, @PathVariable Long id) {

        Ship updatedShip = shipService.getShipById(id);
        if (shipService.updateShip(updatedShip, jsonBody)) {
            shipRepo.save(updatedShip);
        }
        return updatedShip;
    }

    @RequestMapping(path = "/rest/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteShip(@PathVariable Long id) {
        if (id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Ship ship = getShip(id);
        if (ship == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        shipRepo.delete(ship);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
