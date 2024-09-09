package data;

import lombok.*;

@Builder
@Getter
@Setter
public class Auth {

    private String email;

    private String password;
}
