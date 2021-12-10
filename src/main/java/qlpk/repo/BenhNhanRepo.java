package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.BenhNhan;

@Repository
public interface BenhNhanRepo extends JpaRepository<BenhNhan, Integer> {
}
