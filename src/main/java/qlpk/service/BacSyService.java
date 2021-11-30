package qlpk.service;

import qlpk.entity.BacSy;

import java.util.List;

public interface BacSyService {
    void deleteBacSy(int id);
    boolean saveBacSy(BacSy bacSy);
    boolean updateBacSy(BacSy bacSy);
    BacSy searchBacSyByCMT(String cmt);
    List<BacSy> getAll();
}
