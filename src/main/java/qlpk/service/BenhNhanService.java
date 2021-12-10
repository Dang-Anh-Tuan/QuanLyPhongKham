package qlpk.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import qlpk.entity.BenhAn;
import qlpk.entity.BenhNhan;
import qlpk.entity.YTa;

import java.util.List;

public interface BenhNhanService {
	boolean saveBenhNhan(BenhNhan benhNhan);
	Optional<BenhNhan> findById(int id);
	boolean updateBenhNhan(BenhNhan benhNhan);
    List<BenhNhan> findAll();
    void deleteBenhNhan(int id);
    boolean saveBenhNhan(BenhNhan benhNhan);
    boolean updateBenhNhan(BenhNhan benhNhan);
    BenhNhan searchBenhNhanByCMT(String cmt);
}
