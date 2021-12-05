package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qlpk.entity.BenhNhan;
import qlpk.entity.Thuoc;

@Repository
public interface ThuocRepo extends JpaRepository<Thuoc, Integer> {
    @Query("SELECT t from Thuoc t where t.ten = ?1")
    Thuoc findByName(String ten);
}
