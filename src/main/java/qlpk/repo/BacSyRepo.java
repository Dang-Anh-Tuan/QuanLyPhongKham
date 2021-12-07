package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qlpk.entity.BacSy;
import qlpk.entity.Benh;

import java.util.List;
import java.util.Optional;

@Repository
public interface BacSyRepo extends JpaRepository<BacSy, Integer> {
    @Query("SELECT bs from BacSy bs where bs.cmt = ?1")
    public BacSy findByCmt(String cmt);
}
