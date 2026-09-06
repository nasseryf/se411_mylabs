import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Exercise 1
        System.out.println("Exercise 1:");

        String[] names = {
            "Nasser",
            "Faisal",
            "Abdulmajid"
        };

        PrintableList<String> list =
                new PrintableList<>(names);

        list.printItems();


        // Exercise 2
        System.out.println("\nExercise 2:");

        NumberBox<Integer> integerBox =
                new NumberBox<>(10);

        NumberBox<Double> doubleBox =
                new NumberBox<>(5.5);

        System.out.println("Integer: " + integerBox.getItem());
        System.out.println("Double: " + doubleBox.getItem());

        List<Integer> numbers1 =
                Arrays.asList(1, 2, 3, 4);

        List<Double> numbers2 =
                Arrays.asList(1.5, 2.5, 3.5);

        System.out.println(
                "Integer sum: " +
                NumberBox.sumNumbers(numbers1));

        System.out.println(
                "Double sum: " +
                NumberBox.sumNumbers(numbers2));


        // Exercise 3
        System.out.println("\nExercise 3:");

        Pipeline<String, Integer> pipeline =
                Pipeline.<String>start()
                        .addTransformer(text -> text.trim())
                        .addTransformer(text -> text.length());

        Integer result =
                pipeline.execute("  Hello  ");

        System.out.println("Pipeline result: " + result);


        // Exercise 4
        System.out.println("\nExercise 4:");

        List<String> letters =
                Arrays.asList("A", "B", "C");

        printList(letters);

        List<Integer> numbers =
                Arrays.asList(10, 20, 30);

        System.out.println(
                "Sum: " + sumNumbers(numbers));
    }


    public static void printList(List<?> list) {

        for (Object item : list) {
            System.out.println(item);
        }
    }


    public static double sumNumbers(
            List<? extends Number> numbers) {

        double sum = 0;

        for (Number number : numbers) {
            sum += number.doubleValue();
        }

        return sum;
    }
}