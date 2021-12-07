package qlpk.service;

import qlpk.entity.BacSy;
import qlpk.entity.YTa;

import java.util.List;
import java.util.Optional;

public interface YTaService {
    List<YTa> findAll();
    void deleteYTa(int id);
    boolean saveYTa(YTa yTa);
    boolean updateYTa(YTa yTa);
    YTa searchYTaByCMT(String cmt);
    Optional<YTa> getById(int id);
}
