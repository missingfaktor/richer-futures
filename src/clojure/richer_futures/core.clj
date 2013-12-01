(ns richer-futures.core
  (:import [scala.concurrent ExecutionContext$Implicits$ Future Future$ Await$]
           [clojure.lang IDeref]
           [scala.concurrent.duration Duration$])
  (:use [richer-futures.interop])
  (:refer-clojure :exclude [future]))

(def ^{:dynamic true} *execution-context*
  (.global ExecutionContext$Implicits$/MODULE$))

(def ^{:dynamic true} *max-await-duration*
  (.Inf Duration$/MODULE$))

(defn future-fn [fun]
  (.apply Future$/MODULE$ (create-scala-function fun 0) *execution-context*))

(defmacro future [code]
  `(future-fn (fn [] ~code)))

(defn block-and-await [fut]
  (.result Await$/MODULE$ fut *max-await-duration*))

