package org.example.lafiresversin2.fire;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
