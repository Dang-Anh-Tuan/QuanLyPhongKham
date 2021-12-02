package qlpk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.BacSy;
import qlpk.repo.BacSyRepo;

import java.util.List;


@Service
public class BacSyServiceImp implements BacSyService{
    @Autowired
    private BacSyRepo repo;

    @Override
    public void deleteBacSy(int id) {
        repo.deleteById(id);
    }

    @Override
    public boolean saveBacSy(BacSy bacSy) {
        repo.save(bacSy);
        return true;
    }

    @Override
    public boolean updateBacSy(BacSy bacSy) {
        
        return true;
    }

    @Override
    public BacSy searchBacSyByCMT(String cmt) {
        return repo.findByCmt(cmt);
    }

    @Override
    public List<BacSy> getAll() {
        return repo.findAll();
    }
}
