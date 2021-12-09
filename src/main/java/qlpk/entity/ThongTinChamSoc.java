package qlpk.entity;

import lombok.Data;
import org.springframework.lang.Nullable;
import qlpk.entity.enums.HinhThuc;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class ThongTinChamSoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private HinhThuc hinhThuc;
    @ManyToOne(targetEntity = YTa.class)
    @JoinColumn(name = "y_ta_id", referencedColumnName = "id")
    private YTa yTa;
    @ManyToOne(targetEntity = BenhNhan.class)
    @JoinColumn(name = "benh_nhan_id", referencedColumnName = "id")
    private BenhNhan benhNhan;
    @OneToMany(targetEntity = DonThuoc.class)
    @JoinColumn(name = "don_thuoc_id", referencedColumnName = "id")
    @Nullable
    private Set<DonThuoc> donThuoc;
}
