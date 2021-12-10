package qlpk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import qlpk.entity.Benh;

@Service
public interface BenhService {
	void deleteBenh(int id);
    boolean saveBenh(Benh benh);
    boolean updateBenh(Benh benh);
    List<Benh> getAll();
    Optional<Benh> getById(int id);
}
