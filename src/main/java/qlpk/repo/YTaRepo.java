package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.YTa;

@Repository
public interface YTaRepo extends JpaRepository<YTa, Integer> {
}
