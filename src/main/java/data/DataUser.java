package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Осюшкин Денис
 * Класс DTO представляющий объект хранения данных о пользователе
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataUser {

    /**
     * Уникальный идентификатор
     */
    private Integer id;

    /**
     * Электронная почта
     */
    private String email;

    /**
     * Имя
     */
    private String first_name;

    /**
     * Фамилия
     */
    private String last_name;

    /**
     * Аватар
     */
    private String avatar;
}
