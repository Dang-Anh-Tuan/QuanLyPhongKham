package qlpk.service;

import java.util.List;
import qlpk.entity.BenhAn;

public interface BenhAnService {
	List<BenhAn> getAll();
	
	boolean saveBenhAn(BenhAn benhAn);
	
	boolean updateBenhAn(BenhAn benhAn);
	
	void deleteBenhAn(int id);
	
	List<BenhAn> getByName(String name);
}
