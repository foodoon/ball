package guda.ball.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by foodoon on 2014/8/5.
 */
public class CollectionHelper {

    public static <S, T> List<T> transformList(Collection<S> input, Transformer<S, T> transformer) {
        List<T> ret = new ArrayList<T>();
        if (input == null) {
            return ret;
        }
        for (S o : input) {
            ret.add(transformer.transform(o));
        }
        return ret;
    }
}
