(ns richer-futures.core
  (:import [scala.concurrent ExecutionContext$Implicits$ Future$])
  (:use [richer-futures.interop])
  (:refer-clojure :exclude [future]))

(def ^{:dynamic true} *execution-context*
  (.global ExecutionContext$Implicits$/MODULE$))

(defn future-fn [fun]
  (.apply Future$/MODULE$ (create-scala-function fun 0) *execution-context*))

(defmacro future [& code]
  `(future-fn (fn [] ~code)))

