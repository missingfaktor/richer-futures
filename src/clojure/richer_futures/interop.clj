(ns richer-futures.interop
  (:use [richer-futures.util]))

(defn arity-of-method
  [method]
  (->> method .getParameterTypes alength))

; Using some sneaky reflection to figure out arity of a function.
; Credit partly goes to http://stackoverflow.com/a/1813967/192247.
(defn arities
  [fun]
  (let [all-declared-methods (.getDeclaredMethods (class fun))
        methods-named (fn [name]
                        (filter #(= (.getName %) name) all-declared-methods))
        methods-named-invoke (methods-named "invoke")
        methods-named-do-invoke (methods-named "doInvoke")
        is-rest-fn (seq methods-named-do-invoke)]
    (->> methods-named-invoke
         (map arity-of-method)
         sort
         (provided is-rest-fn
                   (fn [v] (append v :rest))))))

(def max-fixed-arity-allowed-by-both-scala-and-clojure
  20)

(defn valid-numeral-arity?
  [arity]
  (and (number? arity)
       (<= 0 arity max-fixed-arity-allowed-by-both-scala-and-clojure)))

(defn valid-arity?
  [arity]
  (or (valid-numeral-arity? arity)
      (= :rest arity)))

(defn scala-function-class-symbol
  [numeral-arity]
  (if (valid-numeral-arity? numeral-arity)
    (symbol (str "scala.runtime.AbstractFunction" numeral-arity))
    (fail-with (str "Cannot create a Scala function with arity " numeral-arity "."))))

(defmacro generate-scala-function-proxy
  [numeral-arity fun]
  (let [syms (generate-n gensym numeral-arity)
        scala-function-class (scala-function-class-symbol numeral-arity)
        apply (symbol "apply")]
    `(proxy [~scala-function-class] []
       (~apply ~(vec syms) (~fun ~@syms)))))

(defn create-scala-function
  [fun arity]
  (case arity
    0 (generate-scala-function-proxy 0 fun)
    1 (generate-scala-function-proxy 1 fun)
    2 (generate-scala-function-proxy 2 fun)
    3 (generate-scala-function-proxy 3 fun)
    4 (generate-scala-function-proxy 4 fun)
    5 (generate-scala-function-proxy 5 fun)
    6 (generate-scala-function-proxy 6 fun)
    7 (generate-scala-function-proxy 7 fun)
    8 (generate-scala-function-proxy 8 fun)
    9 (generate-scala-function-proxy 9 fun)
    10 (generate-scala-function-proxy 10 fun)
    11 (generate-scala-function-proxy 11 fun)
    12 (generate-scala-function-proxy 12 fun)
    13 (generate-scala-function-proxy 13 fun)
    14 (generate-scala-function-proxy 14 fun)
    15 (generate-scala-function-proxy 15 fun)
    16 (generate-scala-function-proxy 16 fun)
    17 (generate-scala-function-proxy 17 fun)
    18 (generate-scala-function-proxy 18 fun)
    19 (generate-scala-function-proxy 19 fun)
    :rest (fail-with "Rest functions not supported right now.")))

