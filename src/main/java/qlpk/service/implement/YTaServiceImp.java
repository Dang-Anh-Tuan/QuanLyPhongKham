package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.dto.UserDTO;
import qlpk.entity.BacSy;
import qlpk.entity.YTa;
import qlpk.repo.UserRepo;
import qlpk.repo.YTaRepo;
import qlpk.service.YTaService;

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
        return false;
    }

    @Override
    public YTa searchYTaByCMT(String cmt) {
        return yTaRepo.findByCmt(cmt);
    }

    @Override
    public Optional<YTa> getById(int id) {
        return yTaRepo.findById(id);
    }
}
