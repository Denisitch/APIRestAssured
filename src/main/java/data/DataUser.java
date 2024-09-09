package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataUser {

    private Integer id;

    private String email;

    private String first_name;

    private String last_name;

    private String avatar;
}
