package vn.fsoft.humanaged.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.security.services.UserDetailsImpl;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;

    private final String type = "Bearer";

    private String refreshToken;

    private UserDetailsImpl user;
}