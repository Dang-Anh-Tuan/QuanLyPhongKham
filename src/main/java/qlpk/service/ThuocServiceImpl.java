package qlpk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import qlpk.entity.Thuoc;
import qlpk.repo.ThuocRepo;
@Service
public class ThuocServiceImpl implements ThuocService{

	@Autowired
	private ThuocRepo thuocRepo;
	
	public ThuocServiceImpl(ThuocRepo thuocRepo) {
		this.thuocRepo = thuocRepo;
	}
	
	@Override
	public void deleteThuoc(int id) {
		thuocRepo.deleteById(id);	
	}

	@Override
	public boolean saveThuoc(Thuoc thuoc) {
		thuocRepo.save(thuoc);
		return true;
	}

	@Override
	public boolean updateThuoc(Thuoc thuoc) {
		thuocRepo.save(thuoc);
		return true;
	}

	@Override
	public Thuoc searchThuocByCMT(String cmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Thuoc> getAll() {
		return thuocRepo.findAll();
	}

	@Override
	public Optional<Thuoc> findById(int id) {
		return thuocRepo.findById(id);
	}

	
	
}
