package qlpk.entity;

import lombok.Data;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class BenhAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKham;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayVaoVien;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayXuatVien;
    private float tongTien;
    private boolean daPhat;
    private boolean daKhoi;
    @ManyToOne(targetEntity = BacSy.class)
    @JoinColumn(name = "bac_sy_id", referencedColumnName = "id")
    private BacSy bacSy;
    @ManyToOne(targetEntity = BenhNhan.class)
    @JoinColumn(name = "benh_nhan_id", referencedColumnName = "id")
    private BenhNhan benhNhan;
    @ManyToOne(targetEntity = YTa.class)
    @JoinColumn(name = "y_ta_id", referencedColumnName = "id")
    private YTa yTa;
}
