package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataColor {

    private Integer id;

    private String name;

    private String year;

    private String color;

    private String pantone_value;
}
