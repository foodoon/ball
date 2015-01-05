package guda.ball.util;

/**
 * Created by foodoon on 2014/8/5.
 */
public interface Transformer<S, T> {

    T transform(S object);

}

