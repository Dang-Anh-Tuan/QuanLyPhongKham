package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class DonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Không được để trống")
    private String lieuLuong;
    @NotEmpty(message = "Không được để trống")
    private float tongTien;
    @NotEmpty(message = "Không được để trống")
    private Date ngayCap;
    @ManyToOne(targetEntity = Benh.class)
    @JoinColumn(name = "benh_id")
    private Benh benh;
    @ManyToOne(targetEntity = Thuoc.class)
    @JoinColumn(name = "thuoc_id")
    private Thuoc thuoc;
    @ManyToOne(targetEntity = ThongTinChamSoc.class)
    private ThongTinChamSoc thongTinChamSoc;
}
