(ns richer-futures.basic
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

(defn future-map [fut fun]
  (.map fut (create-scala-function fun 1) *execution-context*))

(defn future-flatmap [fut fun]
  (.flatMap fut (create-scala-function fun 1) *execution-context*))

(defn future-mresult [v]
  (.successful Future$/MODULE$ v))
;
;(def f1 (future
;         (do
;           (Thread/sleep 50000)
;           2)))
;;
;(def f2 (future
;          (do
;            (Thread/sleep 50000)
;            10)))
;
;
;(future-map f1
;  (fn [x]
;    (future-map f2
;      (fn [y]
;        (println (+ x y))
;        ))))
;
;
;(future-flatmap f1
;  (fn [x]
;    (future-flatmap f2
;      (fn [y]
;        (println (+ x y))
;        ))))

