package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.BacSy;
import qlpk.entity.Benh;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenhRepo extends JpaRepository<Benh, Integer> {
    List<Benh> findBenhByBacSy(Optional<BacSy> idbacsy);
    List<Benh> findBenhByBacSy_IdAndIsDelete(int idbacSy, boolean isDelete);
    List<Benh> findBenhByIsDelete(boolean isDelete);
}
