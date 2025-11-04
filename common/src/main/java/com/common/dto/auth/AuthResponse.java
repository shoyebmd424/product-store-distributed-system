package com.common.dto.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthResponse {
   private String token;
}
