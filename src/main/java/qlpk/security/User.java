package qlpk.security;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class User {
    @Id
    private Integer id;
    @Column(nullable = false, unique = true)
    private String userName;
    private String password;
    private String role;
}
