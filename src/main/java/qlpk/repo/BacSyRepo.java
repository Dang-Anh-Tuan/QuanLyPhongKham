package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import qlpk.entity.BacSy;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BacSyRepo extends JpaRepository<BacSy, Integer> {
    @Query("SELECT bs from BacSy bs where bs.cmt = ?1")
    public BacSy findByCmt(String cmt);
    @Query(value = "CALL `demo-stored-procedure`.get_salary_doctor(:did, :sdate);", nativeQuery = true)
    public float tinhLuongBacSy(@Param("did") int did, @Param("sdate") Date sdate);
}
