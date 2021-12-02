package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Benh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tenBenh;
    private String mota;
    @ManyToOne(targetEntity = BacSy.class)
    @JoinColumn(name = "bac_sy_id", referencedColumnName = "id")
    private BacSy bacSy;
    @OneToMany(targetEntity = DonThuoc.class, mappedBy = "benh")
    private Set<DonThuoc> donThuoc;
}
