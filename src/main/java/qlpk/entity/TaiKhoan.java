package qlpk.entity;

import lombok.Data;
import qlpk.entity.enums.Role;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "taikhoan")
public class TaiKhoan {
    @Id
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private Role role;
}
