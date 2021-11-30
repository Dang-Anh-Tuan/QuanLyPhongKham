package qlpk.service;

import java.util.List;
import qlpk.model.Medicine;
public interface MedicineService {
	Iterable<Medicine> findAll();
    List<Medicine> search(String q);
    Medicine findOne(long id);
    void save(Medicine med);
    void delete(Medicine med);
}
