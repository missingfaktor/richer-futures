package richer_futures;

import clojure.lang.IFn;
import scala.Function1;
import scala.runtime.AbstractFunction1;

@SuppressWarnings("unchecked")
public final class InterOp {
    public static Function1 toScalaFunction(final IFn clojureFunction) {
        return new AbstractFunction1() {
            @Override
            public Object apply(Object arg) {
                return clojureFunction.invoke(arg);
            }
        };
    }
}
