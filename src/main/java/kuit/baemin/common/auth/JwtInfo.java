package kuit.baemin.common.auth;

public record JwtInfo(
        String accessToken,
        String refreshToken
) {}