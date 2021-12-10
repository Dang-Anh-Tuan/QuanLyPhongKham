package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.dto.UserDTO;
import qlpk.entity.BacSy;
import qlpk.entity.YTa;
import qlpk.modelUtil.YtaLuong;
import qlpk.repo.UserRepo;
import qlpk.repo.YTaRepo;
import qlpk.service.YTaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class YTaServiceImp implements YTaService{
    @Autowired
    private YTaRepo yTaRepo;
    @Autowired
    private UserRepo userRepo;
	public YTaServiceImp(YTaRepo yTaRepo, UserRepo userRepo) {
		this.yTaRepo = yTaRepo;
        this.userRepo = userRepo;
	}


    @Override
    public List<YTa> findAll() {
        return yTaRepo.findAll();
    }

    @Override
    public void deleteYTa(int id) {
        yTaRepo.deleteById(id);
    }

    @Override
    public boolean saveYTa(YTa yTa, UserDTO userDTO) {
        yTa.setUser(userRepo.findByUserName(userDTO.getUserName()));
        yTaRepo.save(yTa);
        return true;
    }

    @Override
    public boolean updateYTa(YTa yTa) {
    	 yTaRepo.save(yTa);
         return true;
    }

    @Override
    public YTa searchYTaByCMT(String cmt) {
        return yTaRepo.findByCmt(cmt);
    }

    @Override
    public Optional<YTa> getById(int id) {
        return yTaRepo.findById(id);
    }

    @Override
    public List<YtaLuong> tinhLuongYta(Date sdate, Date edate) {
        System.err.println("111111111111111111111111");
        List<YTa> listYta = yTaRepo.findAll();
        System.err.println("2222222222222222222222222");
        List<YtaLuong> listYtaLuong = new ArrayList<>();
        for(YTa yta:listYta){
            YtaLuong ytaLuong = new YtaLuong();
            ytaLuong.setYta(yta);
            ytaLuong.setLuong(yTaRepo.tinhLuongYta(yta.getId(), sdate, edate).get(0));
            listYtaLuong.add(ytaLuong);
        }
        System.err.println("3333333333333333333333333333");
        return listYtaLuong;
    }
}
