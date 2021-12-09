package qlpk.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import qlpk.entity.BenhAn;
import qlpk.repo.BenhAnRepo;
import qlpk.service.BenhAnService;

@Service
public class BenhAnServiceImpl implements BenhAnService {

	@Autowired
	private BenhAnRepo benhAnRepo;
	
	public BenhAnServiceImpl(BenhAnRepo benhAnRepo) {
		
		this.benhAnRepo = benhAnRepo;
	}
	
	@Override
	public List<BenhAn> getAll() {
		return benhAnRepo.findAll();
	}

	@Override
	public boolean saveBenhAn(BenhAn benhAn) {
		benhAnRepo.save(benhAn);
		return true;
	}

	@Override
	public boolean updateBenhAn(BenhAn benhAn) {
		benhAnRepo.save(benhAn);
		return true;
	}

	@Override
	public void deleteBenhAn(int id) {
		benhAnRepo.deleteById(id);	
	}


	@Override
	public Optional<BenhAn> getById(Integer id) {
		return benhAnRepo.findById(id);
	}

}
