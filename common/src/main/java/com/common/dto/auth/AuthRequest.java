package com.common.dto.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthRequest {
    private String username;
    private String email;
    private String password;
}
