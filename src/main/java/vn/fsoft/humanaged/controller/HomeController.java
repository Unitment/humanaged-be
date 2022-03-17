package vn.fsoft.humanaged.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fsoft.humanaged.domain.RefreshToken;
import vn.fsoft.humanaged.exception.TokenRefreshException;
import vn.fsoft.humanaged.payload.request.LogOutRequest;
import vn.fsoft.humanaged.payload.request.LoginRequest;
import vn.fsoft.humanaged.payload.request.TokenRefreshRequest;
import vn.fsoft.humanaged.payload.response.LoginResponse;
import vn.fsoft.humanaged.payload.response.TokenRefreshResponse;
import vn.fsoft.humanaged.security.jwt.JwtUtils;
import vn.fsoft.humanaged.security.services.RefreshTokenService;
import vn.fsoft.humanaged.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(new LoginResponse(
                jwt,
                refreshToken.getToken(),
                userDetails));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getAccount)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getAccountName());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByAccount(logOutRequest.getAccountName());
        return ResponseEntity.ok("Log out successful!");
    }
}