package qlpk.entity;

import lombok.Data;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class BenhNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ten;
    private String cmt;
    private String diaChi;
    private String sdt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bd;
}
