package riccardodiba.capstoneBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import riccardodiba.capstoneBack.entities.Animale;
import riccardodiba.capstoneBack.payloads.animale.AnimaleDTO;
import riccardodiba.capstoneBack.services.AnimaleService;


import java.util.UUID;

@RestController
@RequestMapping("/api/animale")
@CrossOrigin(origins = "http://localhost:3000")
public class AnimaliController {

    @Autowired
    private AnimaleService animaleService;

    @GetMapping
    public Page<Animale> getAllAnimali(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "uuid") String orderBy) {

        return animaleService.getAllAnimali(page,size,orderBy);
    }

    @GetMapping("/{id}")
    public Animale getAnimaleById(@PathVariable UUID id) {
        return animaleService.findById(id);
    }

    @PostMapping
    public Animale save(@RequestBody AnimaleDTO body) {

        return animaleService.save(body);
    }

    @DeleteMapping("/{id}")
    public void  deleteById(@PathVariable UUID id) {
        animaleService.deleteById(id);
    }

}
@PutMapping("/{id}")
public Animale getAnimaletByIdAndUpdate(@PathVariable UUID id, @RequestBody AnimaleDTO body) {
    return animaleSer
}
