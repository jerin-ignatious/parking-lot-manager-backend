package jerin.ignatious.parking.lot.rest.controller;

import jerin.ignatious.parking.lot.protocol.http.api.ParkingLotApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController implements ParkingLotApi {

    @Override
    public ResponseEntity<String> getAllSlots(String parkingLotId) {
        System.out.print("in controller");
        return ResponseEntity.ok().body("Current parking lot: " + parkingLotId);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello, Spring Boot!";
    }
}
