package qlpk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.model.Medicine;
import qlpk.repo.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineServiceImpl {
	@Autowired
    private MedicineRepository medicineRepository;
    @Override
    public Iterable<Medicine> findAll() {
        return medicineRepository.findAll();
    }
    @Override
    public Medicine findOne(long id) {
        return medicineRepository.findOne(id);
    }
    @Override
    public void save(Medicine med) {
    	medicineRepository.save(med);
    }
    @Override
    public void delete(Medicine med) {
    	medicineRepository.delete(med);
    }
}
