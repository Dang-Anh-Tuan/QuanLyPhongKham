package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qlpk.entity.BacSy;
import qlpk.entity.YTa;

@Repository
public interface YTaRepo extends JpaRepository<YTa, Integer> {
    @Query("SELECT y from YTa y where y.cmt = ?1")
    public YTa findByCmt(String cmt);
}
