package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Осюшкин Денис
 * Класс DTO представляющий объект хранения данных ресурса цветов
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourceColor {

    /**
     * Номер страницы
     */
    private Integer page;

    /**
     * Количество элементов на странице
     */
    private Integer per_page;

    /**
     * Общее количество элементов
     */
    private Integer total;

    /**
     * Общее количество страниц
     */
    private Integer total_pages;

    /**
     * Список данных цветов
     */
    private List<DataColor> data;

    /**
     * Поддержка
     */
    private Support support;
}
