package qlpk.service;

import qlpk.entity.BacSy;
import qlpk.entity.Thuoc;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public interface ThuocService {
	void deleteThuoc(int id);
    boolean saveThuoc(Thuoc thuoc);
    boolean updateThuoc(Thuoc thuoc);
    Thuoc searchThuocByCMT(String cmt);
    List<Thuoc> getAll();
    Optional<Thuoc> findById(int id);
}
