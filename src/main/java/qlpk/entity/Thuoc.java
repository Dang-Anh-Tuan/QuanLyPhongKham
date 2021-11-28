package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Thuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ten;
    private Float gia;
    @OneToMany(targetEntity = DonThuoc.class, mappedBy = "thuoc")
    private Set<DonThuoc> donThuoc;
}
