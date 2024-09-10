package data;

import lombok.*;

/**
 * @author Осюшкин Денис
 * Класс DTO представляющий объект хранения данных авторизации
 */
@Builder
@Getter
@Setter
public class Auth {

    /**
     * Электронная почта
     */
    private String email;

    /**
     * Пароль
     */
    private String password;
}
