package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Осюшкин Денис
 * Класс DTO представляющий объект хранения данных о цвете
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataColor {

    /**
     * Уникальный идентификатор
     */
    private Integer id;

    /**
     * Название
     */
    private String name;

    /**
     * Год
     */
    private String year;

    /**
     * Цвет
     */
    private String color;

    /**
     * Значение
     */
    private String pantone_value;
}
