package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qlpk.entity.Benh;
@Repository
public interface BenhRepo extends JpaRepository<Benh, Integer> {
}
