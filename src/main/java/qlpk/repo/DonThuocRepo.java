package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.DonThuoc;
@Repository
public interface DonThuocRepo extends JpaRepository<DonThuoc, Integer> {
}
