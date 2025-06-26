package org.example.lafiresversin2.sirene.sirenecontroller;


import org.example.lafiresversin2.sirene.sireneservice.SireneService;
import org.example.lafiresversin2.sirene.sireneentity.SireneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sirens")
public class SireneController {

    @Autowired
    private SireneService sireneService;

    @PostMapping
    public ResponseEntity<SireneDTO> createSirene(@RequestBody SireneDTO sireneDTO) {
        SireneDTO createdSirene = sireneService.createSirene(sireneDTO);
        return new ResponseEntity<>(createdSirene, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SireneDTO>> getAllSirens() {
        List<SireneDTO> sirens = sireneService.getAllSirens();
        return ResponseEntity.ok(sirens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSirene(@PathVariable Long id) {
        sireneService.deleteSiren(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSirene(@PathVariable Long id,
                                             @RequestBody SireneDTO sireneDTO) {
        sireneService.updateSiren(id, sireneDTO);
        return ResponseEntity.noContent().build();
    }


}
