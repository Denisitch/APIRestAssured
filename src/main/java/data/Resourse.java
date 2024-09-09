package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resourse {

    private Integer page;

    private Integer per_page;

    private Integer total;

    private Integer total_pages;

    private List<DataUser> data;

    private Support support;
}
