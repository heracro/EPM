package org.epm.delivery;

import org.epm.delivery.model.Delivery;
import org.epm.delivery.model.DeliverySearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    //CRUD for single and list
    @PostMapping("/createDelivery")
    public ResponseEntity<?> createDelivery(@RequestBody Delivery delivery) {
        return null;
    }

    @PostMapping("/createDeliveries")
    public ResponseEntity<?> createDeliveries(@RequestBody List<Delivery> deliveries) {
        return null;
    }

    @GetMapping("/findDelivery")
    public ResponseEntity<?> findDelivery(@RequestParam Long id) {
        return null;
    }

    @PostMapping("/findDeliveries")
    public ResponseEntity<?> findDeliveries(@RequestBody DeliverySearchCriteria criteria) {
        return null;
    }

    @PutMapping("/updateDelivery")
    public ResponseEntity<?> updateDelivery(@RequestBody Delivery delivery) {
        return null;
    }

    @PutMapping("/updateDeliveries")
    public ResponseEntity<?> updateDeliveries(@RequestBody List<Delivery> deliveries) {
        return null;
    }

    @DeleteMapping("/deleteDelivery")
    public ResponseEntity<?> deleteDelivery(@RequestBody Delivery delivery) {
        return null;
    }

    @DeleteMapping("/deleteDeliveries")
    public ResponseEntity<?> deleteDeliveries(@RequestBody List<Delivery> deliveries) {
        return null;
    }

    //Additional endpoints
    @GetMapping("/soonestDeliveries")
    public ResponseEntity<?> findPlannedForNextDays(@RequestParam int days) {
        return null;
    }

    @GetMapping("/deliveryStats")
    public ResponseEntity<?> getDeliveryStats() {
        return null;
    }

}
