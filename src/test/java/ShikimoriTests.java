import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.Categories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ShikimoriTests {

    @BeforeEach
    void setUp(){
        Configuration.pageLoadStrategy = "eager";
        open ("https://shikimori.io/");
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }


    @ValueSource(strings = {
            "Evangelion", "K-On!"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдаваться не пустой список карточек")

    void searchResultsShouldNotBeEmpty(String searchQuery) {

        $("input[placeholder='Поиск...']").setValue(searchQuery);

        $$(".b-db_entry-variant-list_item").shouldHave(sizeGreaterThan(0));
    }

    @CsvSource(value =  {
            "Аниме, https://shikimori.io/animes",
                    "Манга, https://shikimori.io/mangas",
                    "Ранобэ, https://shikimori.io/ranobe"
    })
    @ParameterizedTest(name = "При переходе в раздел {0}, должна открываться корректная страница {1} и не пустой список карточек")

    void navigateListCategoriesNotBeEmptyCard(String elementMenu, String expectedLink){

        SelenideElement menu = $("div.menu-dropdown.main");
        actions().moveToElement(menu).perform();

        SelenideElement link = $$("div.menu-dropdown.main.active a")
                .findBy(com.codeborne.selenide.Condition.attribute("title", elementMenu));

        link.shouldBe(visible).click();

        webdriver().shouldHave(url(expectedLink));

        $$("a.cover")
                .shouldHave(sizeGreaterThan(0));

    }

    @EnumSource(Categories.class)
    @ParameterizedTest
    void categoriesPageShouldDisplayCorrectTitle(Categories categories){

        SelenideElement menu = $("div.menu-dropdown.main");
        actions().moveToElement(menu).perform();

        SelenideElement link = $$("div.menu-dropdown.main.active a")
                .findBy(com.codeborne.selenide.Condition.attribute("title", categories.name()));

        link.shouldBe(visible).click();

        $("header.head h1").shouldHave(text(categories.description));


    }

}