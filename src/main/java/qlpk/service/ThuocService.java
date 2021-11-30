package qlpk.service;

import qlpk.entity.BacSy;
import qlpk.entity.Thuoc;

import java.util.List;

public interface ThuocService {
	void deleteThuoc(int id);
    boolean saveThuoc(Thuoc thuoc);
    boolean updateBacSy(Thuoc thuoc);
    BacSy searchBacSyByCMT(String cmt);
    List<BacSy> getAll();
}
