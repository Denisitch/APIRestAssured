package unit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class UnitTests {

    @Test
    public void testForUniqueDataPassed() {
        List<String> currentData = new ArrayList<>();
        currentData.add("A");
        currentData.add("B");
        currentData.add("C");
        currentData.add("D");

        List<String> uniqueData = currentData.stream()
                .distinct()
                .toList();

        assertEquals(currentData.size(), uniqueData.size(),
                "Строки в списке не уникальны");
    }

    @Test
    public void testForUniqueDataFailed() {
        List<String> currentData = new ArrayList<>();
        currentData.add("A");
        currentData.add("B");
        currentData.add("C");
        currentData.add("C");

        List<String> uniqueData = currentData.stream()
                .distinct()
                .toList();

        assertTrue(currentData.size() != uniqueData.size(),
                "Строки в списке уникальны");
    }

    @Test
    public void testForSortedDataPassed() {
        List<Integer> currentData = new ArrayList<>();
        currentData.add(1);
        currentData.add(2);
        currentData.add(3);
        currentData.add(4);

        List<Integer> uniqueData = currentData.stream()
                .sorted()
                .toList();

        assertEquals(currentData, uniqueData,
                "Строки в списке не отсортированы");
    }

    @Test
    public void testForSortedDataFailed() {
        List<Integer> currentData = new ArrayList<>();
        currentData.add(1);
        currentData.add(2);
        currentData.add(4);
        currentData.add(3);

        List<Integer> uniqueData = currentData.stream()
                .sorted()
                .toList();

        assertNotSame(currentData, uniqueData, "Строки в списке отсортированы");
    }

    @Test
    public void testForXMLParse() {
        String xml =
                """
                <tests>
                    <test>
                        <id>1</id>
                        <status>200</status>
                    </test>
                    <test>
                        <id>2</id>
                        <status>400</status>
                    </test>
                </tests>
                """;

        Document parse = Jsoup.parse(xml, "", Parser.xmlParser());
        int actualCountTags = parse.select("**").size();
        int expectedCountTags = 8;

        assertEquals(actualCountTags, expectedCountTags,
                "Ожидаемое количество тегов: %s. Действительное количество тегов: %s"
                        .formatted(expectedCountTags, actualCountTags));
    }
}