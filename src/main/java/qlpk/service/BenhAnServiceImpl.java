package qlpk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import qlpk.entity.BenhAn;
import qlpk.repo.BenhAnRepo;

public class BenhAnServiceImpl implements BenhAnService{

	@Autowired
	private BenhAnRepo benhAnRepo;
	
	public BenhAnServiceImpl(BenhAnRepo benhAnRepo) {
		super();
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

		return false;
	}

	@Override
	public void deleteBenhAn(int id) {
		benhAnRepo.deleteById(id);	
	}

	@Override
	public List<BenhAn> getByName(String name) {
		return null;
	}

	@Override
	public Optional<BenhAn> getById(Integer id) {
		return benhAnRepo.findById(id);
	}

}
