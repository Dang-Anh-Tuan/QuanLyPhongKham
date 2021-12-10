package qlpk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.BacSy;
import qlpk.entity.YTa;
import qlpk.modelUtil.BacSyLuong;
import qlpk.modelUtil.YtaLuong;
import qlpk.repo.YTaRepo;
import qlpk.service.YTaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class YTaServiceImp implements YTaService {
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

    @Override
    public List<YtaLuong> tinhLuongYta(Date sdate, Date edate) {
        System.err.println("111111111111111111111111");
        List<YTa> listYta = repo.findAll();
        System.err.println("2222222222222222222222222");
        List<YtaLuong> listYtaLuong = new ArrayList<>();
        for(YTa yta:listYta){
            YtaLuong ytaLuong = new YtaLuong();
            ytaLuong.setYta(yta);
            ytaLuong.setLuong(repo.tinhLuongYta(yta.getId(), sdate, edate).get(0));
            listYtaLuong.add(ytaLuong);
        }
        System.err.println("3333333333333333333333333333");
        return listYtaLuong;
    }

}
