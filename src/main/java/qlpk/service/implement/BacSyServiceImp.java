package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.BacSy;
import qlpk.entity.Benh;
import qlpk.repo.BacSyRepo;
import qlpk.repo.BenhRepo;
import qlpk.service.BacSyService;

import java.util.List;
import java.util.Optional;


@Service
public class BacSyServiceImp implements BacSyService {
    @Autowired
    private BacSyRepo bacSyRepo;
    @Autowired
    private BenhRepo benhRepo;

    @Override
    public void deleteBacSy(int id) {
        bacSyRepo.deleteById(id);
    }

    @Override
    public boolean saveBacSy(BacSy bacSy) {
        bacSyRepo.save(bacSy);
        return true;
    }

    @Override
    public boolean updateBacSy(BacSy bacSy) {
    	bacSyRepo.save(bacSy);
        return true;
    }

    @Override
    public BacSy searchBacSyByCMT(String cmt) {
        return bacSyRepo.findByCmt(cmt);
    }

    @Override
    public List<BacSy> getAll() {
        return bacSyRepo.findAll();
    }

	@Override
	public Optional<BacSy> getById(int id) {
		return bacSyRepo.findById(id);
	}

    @Override
    public List<Benh> getListBenhByBacSy(int id) {
        return benhRepo.findBenhByBacSy(bacSyRepo.findById(id));
    }


}
