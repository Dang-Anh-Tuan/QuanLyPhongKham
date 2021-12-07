package qlpk.entity;

import lombok.Data;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import qlpk.security.User;

import java.util.Date;
import java.util.Set;

@Data
@Entity
public class BacSy  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ten;
    private String cmt;
    private String diaChi;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date ngaySinh;
    private String bacNghe;
    private int thamNien;
    private String trinhDo;
    private String chuyenMon;
    private String sdt;
    @OneToMany(targetEntity = Benh.class, mappedBy = "bacSy")
    private Set<Benh> benh;
    @OneToMany(targetEntity = BenhAn.class, mappedBy = "bacSy")
    private Set<BenhAn> benhAn;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userName")
    private User user;
}
