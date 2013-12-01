(ns richer-futures.core-test
  (:require [clojure.test :refer :all]
            [richer-futures.util :refer :all]
            [richer-futures.interop :refer :all]
            [richer-futures.basic :refer :all]))

(deftest fail-with-must-throw-with-given-message
  (try
    (fail-with "xyz")
    (catch Throwable t
      (do
        (is (= (.getClass t) RuntimeException))
        (is (= (.getMessage t) "xyz"))))))
