package org.example.lafiresversin2.fire.firecontroller;


import org.example.lafiresversin2.fire.fireservice.FireService;
import org.example.lafiresversin2.fire.fireentity.FireDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fires")
public class FireController {

    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @PostMapping
    public ResponseEntity<FireDTO> createFire(@RequestBody FireDTO fireDTO) {
        FireDTO createdFire = fireService.createFire(fireDTO);
        return new ResponseEntity<>(createdFire, HttpStatus.CREATED);
    }


    @GetMapping("/active")
    public ResponseEntity<List<FireDTO>> findAllFire() {
        List<FireDTO> activeFires = fireService.getAllActiveFires();
        return new ResponseEntity<>(activeFires, HttpStatus.OK);
    }

    @PatchMapping("/{fireId}/close")
    public ResponseEntity<Void> closeFire(@PathVariable Long fireId) {
        boolean closed = fireService.closeFire(fireId);
        if (closed) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 hvis ikke fundet
        }
    }

}
