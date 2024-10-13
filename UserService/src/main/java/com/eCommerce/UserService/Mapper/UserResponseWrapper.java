package com.eCommerce.UserService.Mapper;

import com.eCommerce.UserService.Response.FallBackResponse;
import com.eCommerce.UserService.Response.UserResponse;
import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseWrapper {
    private UserResponse userResponse;
    private FallBackResponse fallBackResponse;

    public UserResponseWrapper(FallBackResponse fallbackResponse) {
        this.fallBackResponse = fallbackResponse;
    }

    public UserResponseWrapper(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
