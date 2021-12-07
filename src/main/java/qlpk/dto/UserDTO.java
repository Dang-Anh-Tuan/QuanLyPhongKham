package qlpk.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//cái này dùng để test object gửi từ form qua
public class UserDTO {
    private String userName;
    private String password;
}
