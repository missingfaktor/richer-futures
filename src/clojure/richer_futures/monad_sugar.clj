(ns richer-futures.monad-sugar
  (:use [richer-futures.basic]
        [clojure.algo.monads])
  (:refer-clojure :exclude [future]))

(defmonad future-m
  [m-result (fn [v] (future-mresult v) )
   m-bind (fn [mv f]
    (future-flatmap mv f))])


;(domonad future-m
;  [x (future
;        (do
;          (Thread/sleep 50000)
;           2))
;   y (future
;        (do
;          (Thread/sleep 50000)
;          10))]
;  (println (+ x y)))

