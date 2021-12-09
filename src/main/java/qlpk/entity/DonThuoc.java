package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class DonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lieuLuong;
    private float tongTien;
    private Date ngayCap;
    @ManyToOne(targetEntity = Benh.class)
    @JoinColumn(name = "benh_id")
    private Benh benh;
    @ManyToOne(targetEntity = Thuoc.class)
    @JoinColumn(name = "thuoc_id")
    private Thuoc thuoc;
    @ManyToOne(targetEntity = ThongTinChamSoc.class)
    @JoinColumn(name = "cham_soc_id", referencedColumnName = "id")
    private ThongTinChamSoc thongTinChamSoc;
}
