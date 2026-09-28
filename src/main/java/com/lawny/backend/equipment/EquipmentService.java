package com.lawny.backend.equipment;

import java.util.List;
import org.springframework.stereotype.Service;

@Service 
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository){
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

        public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }

            public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
}
