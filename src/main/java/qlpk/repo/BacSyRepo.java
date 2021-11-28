package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.BacSy;

@Repository
public interface BacSyRepo extends JpaRepository<BacSy, Integer> {
}
