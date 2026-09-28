package com.lawny.backend.lawn;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lawns")
public class LawnController {

    private final LawnService lawnService;

    public LawnController(LawnService lawnService) {
        this.lawnService = lawnService;
    }

    @PostMapping
    public Lawn createLawn(@Valid @RequestBody Lawn lawn) {
        return lawnService.createLawn(lawn);
    }

    @GetMapping
    public List<Lawn> getAllLawns() {
        return lawnService.getAllLawns();
    }

    @DeleteMapping("/{id}")
    public void deleteLawn(@PathVariable("id") Long id) {
        lawnService.deleteLawn(id);
    }
}
