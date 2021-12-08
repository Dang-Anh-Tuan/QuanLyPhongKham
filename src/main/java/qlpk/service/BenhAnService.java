package qlpk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import qlpk.entity.BenhAn;

@Service
public interface BenhAnService {
	List<BenhAn> getAll();
	
	boolean saveBenhAn(BenhAn benhAn);
	
	boolean updateBenhAn(BenhAn benhAn);
	
	void deleteBenhAn(int id);
	
	Optional<BenhAn> findById(int id);
}
