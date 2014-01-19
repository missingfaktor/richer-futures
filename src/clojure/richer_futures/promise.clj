(ns richer-futures.promise
  (:import [scala.concurrent Promise Promise$])
  (:refer-clojure :exclude [future promise]))

(defn promise []
  (.apply Promise$/MODULE$))

(defn success [promise value]
  (.success promise value))

(defn future [promise]
  (.future promise))

(defn failure [promise throwable]
  (.failure promise throwable))
