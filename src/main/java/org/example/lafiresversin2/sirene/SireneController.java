package org.example.lafiresversin2.sirene;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


}
