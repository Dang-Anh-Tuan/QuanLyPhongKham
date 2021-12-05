package qlpk.dto.auth.param;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//cái này dùng để test object gửi từ form qua
public class LoginFormParam {
    private String username;
    private String password;
}
