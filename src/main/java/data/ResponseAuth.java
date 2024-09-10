package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Осюшкин Денис
 * Класс DTO представляющий объект хранения данных успешной авторизации
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseAuth {

    /**
     * Уникальный идентификатор
     */
    private int id;

    /**
     * Токен
     */
    private String token;
}
