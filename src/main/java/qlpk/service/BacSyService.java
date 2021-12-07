package qlpk.service;

import qlpk.entity.BacSy;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

public interface BacSyService {
    void deleteBacSy(int id);
    boolean saveBacSy(BacSy bacSy);
    boolean updateBacSy(BacSy bacSy);
    BacSy searchBacSyByCMT(String cmt);
    List<BacSy> getAll();
    Optional<BacSy> getById(int id);
}
