package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.BenhAn;

@Repository
public interface BenhAnRepo extends JpaRepository<BenhAn, Integer> {
}
