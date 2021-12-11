package qlpk.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qlpk.entity.Benh;
import qlpk.repo.BenhRepo;
import qlpk.service.BenhService;

@Service
public class BenhServiceImpl implements BenhService {
	
	@Autowired
	private BenhRepo benhRepo;
	
	public BenhServiceImpl(BenhRepo benhRepo) {
		this.benhRepo = benhRepo;
	}

	@Override
	public void deleteBenh(int id) {
		Benh benh = benhRepo.getById(id);
		benh.setDelete(true);
		saveBenh(benh);
	}

	@Override
	public boolean saveBenh(Benh benh) {
		benhRepo.save(benh);
		return true;
	}

	@Override
	public boolean updateBenh(Benh benh) {
		benhRepo.save(benh);
		return true;
	}


	@Override
	public List<Benh> getAll() {
		return benhRepo.findBenhByIsDelete(false);
	}

	@Override
	public Optional<Benh> getById(int id) {
		return benhRepo.findById(id);
	}

	@Override
	public List<Benh> getBenhByBacSy(int idbacsy) {
		return benhRepo.findBenhByBacSy_IdAndIsDelete(idbacsy, false);
	}

}
