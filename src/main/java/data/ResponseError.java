package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Осюшкин Денис
 * Класс DTO представляющий объект хранения данных ошибки авторизации
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseError {

    /**
     * Сообщение об ошибке
     */
    private String error;
}
