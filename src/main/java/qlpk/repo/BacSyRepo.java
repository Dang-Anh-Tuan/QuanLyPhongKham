package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import qlpk.entity.BacSy;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BacSyRepo extends JpaRepository<BacSy, Integer> {
    @Query("SELECT bs from BacSy bs where bs.cmt = ?1")
    public BacSy findByCmt(String cmt);
    @Query(value = "CALL get_salary_doctor(:did, :sdate, :edate);", nativeQuery = true)
    public List<Float> tinhLuongBacSy(@RequestParam("did") int did, @RequestParam("sdate") Date sdate, @RequestParam("edate") Date edate);
}
