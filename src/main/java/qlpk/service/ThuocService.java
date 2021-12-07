package qlpk.service;

import qlpk.entity.Thuoc;

import java.util.List;
import java.util.Optional;

public interface ThuocService {
    void deleteThuoc(int id);
    boolean saveThuoc(Thuoc thuoc);
    boolean updateThuoc(Thuoc thuoc);
    Thuoc searchThuocByName(String name);
    List<Thuoc> getAll();
    Optional<Thuoc> getById(int id);
}
