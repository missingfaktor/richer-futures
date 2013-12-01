# richer-futures

Clojure has futures, but they are not much more powerful than Java's futures. They support a blocking await, a few functions to fiddle with promises, but that's about it.

This project intends to provide better futures for Clojure.

The additional features it will provide are:

1. Callbacks
2. Combinators
3. Monadic API
4. Syntax transformation that will help us write sequential-like code (like async-await in C#)

We decided that it will make more sense to build atop an existing, trusted implementation. Scala's futures seemed to fit the bill, and underlie this implementation. (We plan to eventually do away with the Scala futures and use a custom implementation instead.)

## Usage

This section will be updated once this project is in usable state.

## References

1. [Scala futures - official documentation][2].
2. [Another attempt][3] at providing better futures for Clojure.
3. [Rich Hickey's presentation on CSP][1] where he explains why they consciously chose against evolving futures in Clojure.

## License

Copyright © 2013 Rahul Goma Phulore, Sumit Mahamuni, Pooja Akshantal

Distributed under the Eclipse Public License, the same as Clojure.



[1]: http://www.infoq.com/presentations/clojure-core-async
[2]: http://docs.scala-lang.org/overviews/core/futures.html)
[3]: http://www.niclas-meier.de/2012/06/alternate-futures-and-promises-for-clojure/