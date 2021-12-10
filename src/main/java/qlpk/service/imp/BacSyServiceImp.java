package qlpk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.BacSy;
import qlpk.modelUtil.BacSyLuong;
import qlpk.repo.BacSyRepo;
import qlpk.service.BacSyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BacSyServiceImp implements BacSyService {
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
    	repo.save(bacSy);
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

	@Override
	public Optional<BacSy> getById(int id) {
		return repo.findById(id);
	}

    @Override
    public List<BacSyLuong> tinhLuongBacSy(Date sdate, Date edate) {
        System.err.println("111111111111111111111111");
        List<BacSy> listBacSi = repo.findAll();
        System.err.println("2222222222222222222222222");
        List<BacSyLuong> listBacSiLuong = new ArrayList<>();
        for(BacSy bacSy:listBacSi){
            BacSyLuong bacSyLuong = new BacSyLuong();
            bacSyLuong.setBacSy(bacSy);
            bacSyLuong.setLuong(repo.tinhLuongBacSy(bacSy.getId(), sdate, edate).get(0));
            listBacSiLuong.add(bacSyLuong);
        }
        System.err.println("3333333333333333333333333333");
        return listBacSiLuong;
    }
}
