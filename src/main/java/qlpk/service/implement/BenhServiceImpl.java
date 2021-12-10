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
		benhRepo.deleteById(id);
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
		return benhRepo.findAll();
	}

	@Override
	public Optional<Benh> getById(int id) {
		return benhRepo.findById(id);
	}

}
