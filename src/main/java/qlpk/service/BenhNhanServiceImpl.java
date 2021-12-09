package qlpk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qlpk.entity.BenhNhan;
import qlpk.repo.BenhNhanRepo;

@Service
public class BenhNhanServiceImpl implements BenhNhanService{
	@Autowired
	BenhNhanRepo repo;
	
	public BenhNhanServiceImpl(BenhNhanRepo repo) {
		this.repo = repo;
	}

	@Override
	public boolean saveBenhNhan(BenhNhan benhNhan) {
		repo.save(benhNhan);
		return true;
	}

	@Override
	public Optional<BenhNhan> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public boolean updateBenhNhan(BenhNhan benhNhan) {
		repo.save(benhNhan);
		return true;
	}

}
