package com.common.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserResponse {
    private  long id;
    private  String username;
    private String email;
}
