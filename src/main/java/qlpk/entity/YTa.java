package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class YTa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ten;
    private String cmt;
    private String trinhDo;
    private int thamNien;
    private String diaChi;
    private String sdt;
    private Date ngaySinh;
    @OneToMany(targetEntity = BenhAn.class, mappedBy = "yTa")
    private Set<BenhAn> benhAn;
    @OneToMany(targetEntity = ThongTinChamSoc.class, mappedBy = "yTa")
    private Set<ThongTinChamSoc> thongTinChamSoc;
}
