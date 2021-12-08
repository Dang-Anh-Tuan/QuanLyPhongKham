package qlpk.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import qlpk.entity.BenhAn;
import qlpk.entity.BenhNhan;

@Service
public interface BenhNhanService {
	boolean saveBenhNhan(BenhNhan benhNhan);
	Optional<BenhNhan> findById(int id);
	boolean updateBenhNhan(BenhNhan benhNhan);
	
}
