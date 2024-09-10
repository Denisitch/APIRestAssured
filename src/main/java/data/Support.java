package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Осюшкин Денис
 * Класс DTO представляющий объект хранения данных о поддержке
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Support {

    /**
     * Ссылка
     */
    private String url;

    /**
     * Инфо
     */
    private String text;
}
