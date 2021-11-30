package qlpk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import qlpk.entity.Thuoc;
import qlpk.repo.ThuocRepo;

public class ThuocServiceImpl implements ThuocService{
	@Autowired
	private ThuocRepo thuocRepo;
	public ThuocServiceImpl(ThuocRepo thuocRepo) {
		super();
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
	public Thuoc addThuoc(String cmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Thuoc> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
