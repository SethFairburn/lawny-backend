package com.lawny.backend.lawn;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LawnService {
    private final LawnRepository lawnRepository;

    public LawnService(LawnRepository lawnRepository) {
        this.lawnRepository = lawnRepository;
    }

    public Lawn createLawn(Lawn lawn) {
        return lawnRepository.save(lawn);
    }

    public void deleteLawn(Long id) {
        lawnRepository.deleteById(id);
    }

    public List<Lawn> getAllLawns() {
        return lawnRepository.findAll();
    }
}