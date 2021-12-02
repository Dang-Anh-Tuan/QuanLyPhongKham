package qlpk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.YTa;
import qlpk.repo.YTaRepo;

import java.util.List;
import java.util.Optional;

@Service
public class YTaServiceImp implements YTaService{
	@Autowired
    private YTaRepo repo;
    
	public YTaServiceImp(YTaRepo repo) {
		this.repo = repo;
	}
    
    @Override
    public List<YTa> getAll() {
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
    	repo.save(yTa);
        return true;
    }

    @Override
    public YTa searchYTaByCMT(String cmt) {
        return repo.findByCmt(cmt);
    }

	@Override
	public Optional<YTa> getById(int id) {
		return repo.findById(id);
	}
}
