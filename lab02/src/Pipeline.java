import java.util.ArrayList;
import java.util.List;

public class Pipeline<T, R> {

    private final List<Transformer<Object, Object>> transformers;

    public Pipeline() {
        transformers = new ArrayList<>();
    }

    private Pipeline(List<Transformer<Object, Object>> transformers) {
        this.transformers = transformers;
    }

    public static <T> Pipeline<T, T> start() {
        return new Pipeline<>();
    }

    @SuppressWarnings("unchecked")
    public <V> Pipeline<T, V> addTransformer(
            Transformer<? super R, ? extends V> transformer) {

        List<Transformer<Object, Object>> newTransformers =
                new ArrayList<>(transformers);

        newTransformers.add(input ->
                transformer.transform((R) input));

        return new Pipeline<>(newTransformers);
    }

    @SuppressWarnings("unchecked")
    public R execute(T input) {

        Object result = input;

        for (Transformer<Object, Object> transformer : transformers) {
            result = transformer.transform(result);
        }

        return (R) result;
    }
}