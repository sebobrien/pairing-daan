package potter;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static potter.App.FIVE_BOOK_DISCOUNT_FACTOR;
import static potter.App.FOUR_BOOK_DISCOUNT_FACTOR;
import static potter.App.PRICE_OF_BOOK;
import static potter.App.THREE_BOOK_DISCOUNT_FACTOR;
import static potter.App.TWO_BOOK_DISCOUNT_FACTOR;

class AppTest {

    @Test
    void noBooks_priceIs0() {
        BigDecimal price = new App().getPrice(0, 0, 0, 0, 0);
        assertThat(price).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void oneBook_priceIs8() {
        App app = new App();
        assertThat(app.getPrice(1, 0, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(8));
        assertThat(app.getPrice(0, 1, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(8));
        assertThat(app.getPrice(0, 0, 1, 0, 0)).isEqualTo(BigDecimal.valueOf(8));
        assertThat(app.getPrice(0, 0, 0, 1, 0)).isEqualTo(BigDecimal.valueOf(8));
        assertThat(app.getPrice(0, 0, 0, 0, 1)).isEqualTo(BigDecimal.valueOf(8));
    }

    @Test
    void twoTheSameBooks_priceIs16() {
        App app = new App();
        assertThat(app.getPrice(2, 0, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(16));
        assertThat(app.getPrice(0, 2, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(16));
        assertThat(app.getPrice(0, 0, 2, 0, 0)).isEqualTo(BigDecimal.valueOf(16));
        assertThat(app.getPrice(0, 0, 0, 2, 0)).isEqualTo(BigDecimal.valueOf(16));
        assertThat(app.getPrice(0, 0, 0, 0, 2)).isEqualTo(BigDecimal.valueOf(16));
    }

    @Test
    void threeTheSameBooks_priceIs24() {
        App app = new App();
        assertThat(app.getPrice(3, 0, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(24));
        assertThat(app.getPrice(0, 3, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(24));
        assertThat(app.getPrice(0, 0, 3, 0, 0)).isEqualTo(BigDecimal.valueOf(24));
        assertThat(app.getPrice(0, 0, 0, 3, 0)).isEqualTo(BigDecimal.valueOf(24));
        assertThat(app.getPrice(0, 0, 0, 0, 3)).isEqualTo(BigDecimal.valueOf(24));
    }

    @Test
    void fourTheSameBooks_priceIs32() {
        App app = new App();
        assertThat(app.getPrice(4, 0, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(32));
        assertThat(app.getPrice(0, 4, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(32));
        assertThat(app.getPrice(0, 0, 4, 0, 0)).isEqualTo(BigDecimal.valueOf(32));
        assertThat(app.getPrice(0, 0, 0, 4, 0)).isEqualTo(BigDecimal.valueOf(32));
        assertThat(app.getPrice(0, 0, 0, 0, 4)).isEqualTo(BigDecimal.valueOf(32));
    }

    @Test
    void tenTheSameBooks_priceIs80() {
        var app = new App();
        assertThat(app.getPrice(10, 0, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(80));
        assertThat(app.getPrice(0, 10, 0, 0, 0)).isEqualTo(BigDecimal.valueOf(80));
        assertThat(app.getPrice(0, 0, 10, 0, 0)).isEqualTo(BigDecimal.valueOf(80));
        assertThat(app.getPrice(0, 0, 0, 10, 0)).isEqualTo(BigDecimal.valueOf(80));
        assertThat(app.getPrice(0, 0, 0, 0, 10)).isEqualTo(BigDecimal.valueOf(80));
    }

    @Test
    void twoDifferentBooks_priceHasFivePercentDiscount() {
        var app = new App();
        BigDecimal nrOfDifferentBooks = BigDecimal.valueOf(2);
        BigDecimal expectedPrice = PRICE_OF_BOOK.multiply(nrOfDifferentBooks).multiply(TWO_BOOK_DISCOUNT_FACTOR);

        assertThat(app.getPrice(1, 1, 0, 0, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 1, 1, 0, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 0, 1, 1, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 0, 0, 1, 1)).isEqualTo(expectedPrice);
    }

    @Test
    void threeDifferentBooks_priceHas10PercentDiscount() {
        var app = new App();
        BigDecimal nrOfDifferentBooks = BigDecimal.valueOf(3);
        BigDecimal expectedPrice = PRICE_OF_BOOK.multiply(nrOfDifferentBooks).multiply(THREE_BOOK_DISCOUNT_FACTOR);

        assertThat(app.getPrice(1, 1, 1, 0, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 1, 1, 0, 1)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(1, 0, 1, 1, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 1, 0, 1, 1)).isEqualTo(expectedPrice);
    }

    @Test
    void fourDifferentBooks_priceHas20PercentDiscount() {
        var app = new App();
        BigDecimal nrOfDifferentBooks = BigDecimal.valueOf(4);
        BigDecimal expectedPrice = PRICE_OF_BOOK.multiply(nrOfDifferentBooks).multiply(FOUR_BOOK_DISCOUNT_FACTOR);

        assertThat(app.getPrice(1, 1, 1, 1, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 1, 1, 1, 1)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(1, 0, 1, 1, 1)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(1, 1, 0, 1, 1)).isEqualTo(expectedPrice);
    }

    @Test
    void fiveDifferentBooks_priceHas25PercentDiscount() {
        var app = new App();
        BigDecimal nrOfDifferentBooks = BigDecimal.valueOf(5);
        BigDecimal expectedPrice = PRICE_OF_BOOK.multiply(nrOfDifferentBooks).multiply(FIVE_BOOK_DISCOUNT_FACTOR);

        assertThat(app.getPrice(1, 1, 1, 1, 1)).isEqualTo(expectedPrice);
    }

    @Test
    void twoDifferent_OfWhichOneDouble_twoHaveFivePercentDiscount_OneFullPrice() {
        var app = new App();
        var expectedPrice = PRICE_OF_BOOK.multiply(BigDecimal.valueOf(2)).multiply(TWO_BOOK_DISCOUNT_FACTOR).add(PRICE_OF_BOOK);

        assertThat(app.getPrice(2, 1, 0, 0, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(2, 0, 1, 0, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 1, 2, 0, 0)).isEqualTo(expectedPrice);
        assertThat(app.getPrice(0, 1, 0, 0, 2)).isEqualTo(expectedPrice);
    }

    @Test
    void sugestedTestCase_001(){
        var app = new App();
        var expectedPrice = BigDecimal.valueOf(8 + (8 * 2 * 0.95)).setScale(2);
        assertThat(app.getPrice(2,1,0,0,0)).isEqualTo(expectedPrice);
    }

    @Test
    void sugestedTestCase_0011(){
        var app = new App();
        var expectedPrice = BigDecimal.valueOf(2 * (8 * 2 * 0.95)).setScale(2);;
        assertThat(app.getPrice(2,2,0,0,0)).isEqualTo(expectedPrice);
    }

    @Test
    void sugestedTestCase_001223(){
        var app = new App();
        var expectedPrice = BigDecimal.valueOf((8 * 4 * 0.8) + (8 * 2 * 0.95)).setScale(2);;
        assertThat(app.getPrice(2,1,2,1,0)).isEqualTo(expectedPrice);
    }

    @Test
    void sugestedTestCase_011234(){
        var app = new App();
        var expectedPrice = BigDecimal.valueOf(8 + (8 * 5 * 0.75)).setScale(2);;
        assertThat(app.getPrice(1,2,1,1,1)).isEqualTo(expectedPrice);
    }

    @Test
    void sugestedTestCase_edgeCase_00112234(){
        var app = new App();
        var expectedPrice = BigDecimal.valueOf(2 * (8 * 4 * 0.8)).setScale(2);;
        assertThat(app.getPrice(2,2,2,1,1)).isEqualTo(expectedPrice);
    }

    @Test
    void sugestedTestCase_edgeCase_five_five_four_five_four(){
        var app = new App();
        var expectedPrice = BigDecimal.valueOf(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8)).setScale(2);;
        assertThat(app.getPrice(5,5,4,5,4)).isEqualTo(expectedPrice);
    }
}