package com.lawny.backend.lawn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LawnRepository extends JpaRepository<Lawn, Long> {
    
}
