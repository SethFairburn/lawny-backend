package com.lawny.backend.equipment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

}

/*
 * Extending JpaRepository gives us common database operations
 * without needing to write the SQL ourselves.
 *
 * Examples:
 * findAll()       -> get all equipment
 * findById(id)    -> get equipment by ID
 * save(equipment) -> create or update equipment
 * delete(equipment) -> delete equipment
 *
 * JpaRepository<Equipment, Long>
 *
 * Equipment -> the Entity this repository manages
 * Long      -> the data type of Equipment's ID
 */