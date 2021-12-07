package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.TaiKhoan;

@Repository
public interface TaiKhoanRepo extends JpaRepository<TaiKhoan, String> {

}
