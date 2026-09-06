import java.util.List;

public class NumberBox<T extends Number> {

    private T item;

    public NumberBox(T item) {
        this.item = item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;

        for (Number number : numbers) {
            sum += number.doubleValue();
        }

        return sum;
    }
}