package qlpk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@Entity
public class YTa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "không được để trống")
    private String ten;
    private String cmt;
    private String trinhDo;
    private int thamNien;
    private String diaChi;
    private String sdt;
    @DateTimeFormat (pattern="yyyy-MM-dd")
    @Past(message = "Ngày sinh phải trước ngày hiện tại")
    private Date bd;
    @OneToMany(targetEntity = BenhAn.class, mappedBy = "yTa")
    private Set<BenhAn> benhAn;
    @OneToMany(targetEntity = ThongTinChamSoc.class, mappedBy = "yTa")
    private Set<ThongTinChamSoc> thongTinChamSoc;
    @OneToOne
    @JoinColumn(name = "userName")
    private User user;
}
