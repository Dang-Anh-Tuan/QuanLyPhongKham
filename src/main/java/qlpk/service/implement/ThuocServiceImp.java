package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.Thuoc;
import qlpk.repo.ThuocRepo;
import qlpk.service.ThuocService;

import java.util.List;
import java.util.Optional;

@Service
public class ThuocServiceImp implements ThuocService {
    @Autowired
    private ThuocRepo repo;
    @Override
    public void deleteThuoc(int id) {
        repo.deleteById(id);
    }

    @Override
    public boolean saveThuoc(Thuoc thuoc) {
        repo.save(thuoc);
        return true;
    }

    @Override
    public boolean updateThuoc(Thuoc thuoc) {
        repo.save(thuoc);
        return true;
    }

    @Override
    public Thuoc searchThuocByName(String name) {
        return repo.findByName(name);
    }


    @Override
    public List<Thuoc> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Thuoc> getById(int id) {
        return repo.findById(id);
    }
}
