package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.entity.DonThuoc;
import qlpk.repo.DonThuocRepo;
import qlpk.service.DonThuocService;

@Service
public class DonThuocServiceImp implements DonThuocService {
    @Autowired
    private DonThuocRepo donThuocRepo;

    @Override
    public Integer saveThuoc(DonThuoc donThuoc) {
        DonThuoc donThuoc1 = donThuocRepo.save(donThuoc);
        return donThuoc1.getId();
    }

    @Override
    public DonThuoc getById(int id) {
        return donThuocRepo.getById(id);
    }
}
