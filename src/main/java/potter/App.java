package potter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    static final BigDecimal PRICE_OF_BOOK = BigDecimal.valueOf(8);
    static final BigDecimal FIVE_BOOK_DISCOUNT_FACTOR = BigDecimal.valueOf(0.75);
    static final BigDecimal FOUR_BOOK_DISCOUNT_FACTOR = BigDecimal.valueOf(0.80);
    static final BigDecimal THREE_BOOK_DISCOUNT_FACTOR = BigDecimal.valueOf(0.90);
    static final BigDecimal TWO_BOOK_DISCOUNT_FACTOR = BigDecimal.valueOf(0.95);

    public BigDecimal getPrice(int nrOfBookOne, int nrOfBookTwo, int nrOfBookThree, int nrOfBookFour, int nrOfBookFive) {
        ArrayList<Long> numberOfDistinctBooksPerPass = getDistinctBookCountPerPass(nrOfBookOne, nrOfBookTwo, nrOfBookThree, nrOfBookFour, nrOfBookFive);

        return numberOfDistinctBooksPerPass.stream()
                .map(nrOfDistinctBooks -> getPriceOfBooks(nrOfDistinctBooks))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getPriceOfBooks(Long nrOfDistinctBooks) {
        return PRICE_OF_BOOK
                .multiply(BigDecimal.valueOf(nrOfDistinctBooks)
                        .multiply(getDiscountFactor(nrOfDistinctBooks)));
    }

    private ArrayList<Long> getDistinctBookCountPerPass(int nrOfBookOne, int nrOfBookTwo, int nrOfBookThree, int nrOfBookFour, int nrOfBookFive) {
        ArrayList<Long> result = new ArrayList<>();

        var current = List.of(nrOfBookOne, nrOfBookTwo, nrOfBookThree, nrOfBookFour, nrOfBookFive);
        var nrOfDistinctBooks = getNrOfDistinctBooks(current);

        while (nrOfDistinctBooks > 0) {
            result.add(nrOfDistinctBooks);
            current = skimBooksOfTop(current);
            nrOfDistinctBooks = getNrOfDistinctBooks(current);
        }
        return result;
    }

    private long getNrOfDistinctBooks(List<Integer> current) {
        return current.stream()
                .filter(integer -> integer > 0)
                .count();
    }

    private List<Integer> skimBooksOfTop(List<Integer> books) {
        var result = books.stream()
                .map(this::skimBookOfTop)
                .collect(toList());
        return result;
    }

    private Integer skimBookOfTop(Integer nrOfBooks) {
        return nrOfBooks > 0 ? nrOfBooks - 1 : nrOfBooks;
    }

    private BigDecimal getDiscountFactor(long nrOfDistinctBooks) {
        return switch ((int) nrOfDistinctBooks) {
            case 5 -> FIVE_BOOK_DISCOUNT_FACTOR;
            case 4 -> FOUR_BOOK_DISCOUNT_FACTOR;
            case 3 -> THREE_BOOK_DISCOUNT_FACTOR;
            case 2 -> TWO_BOOK_DISCOUNT_FACTOR;
            default -> BigDecimal.ONE;
        };
    }


}
