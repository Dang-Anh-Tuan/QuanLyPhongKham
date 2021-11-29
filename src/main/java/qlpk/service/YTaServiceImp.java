package qlpk.service;

import org.springframework.stereotype.Service;
import qlpk.entity.YTa;
import qlpk.repo.YTaRepo;

import java.util.List;

@Service
public class YTaServiceImp implements YTaService{
    private YTaRepo repo;
    @Override
    public List<YTa> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteYTa(int id) {
        repo.deleteById(id);
    }

    @Override
    public boolean saveYTa(YTa yTa) {
        repo.save(yTa);
        return true;
    }

    @Override
    public boolean updateYTa(YTa yTa) {
        return false;
    }

    @Override
    public YTa searchYTaByCMT(String cmt) {
        return repo.findByCmt(cmt);
    }
}
