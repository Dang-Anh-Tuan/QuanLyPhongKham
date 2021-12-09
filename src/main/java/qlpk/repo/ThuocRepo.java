package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.Thuoc;

@Repository
public interface ThuocRepo extends JpaRepository<Thuoc, Integer> {
}
