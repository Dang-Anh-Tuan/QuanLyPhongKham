package qlpk.service;

import qlpk.entity.DonThuoc;
import qlpk.entity.Thuoc;

public interface DonThuocService {
    Integer saveThuoc(DonThuoc donThuoc);
    DonThuoc getById(int id);
}
