(ns richer-futures.callbacks
  (:use [richer-futures.basic]
        [richer-futures.interop])
  (:import [scala.concurrent Future]
           [scala.util Try Success Failure]))

(defn completed? [fut]
  (.isCompleted fut))

(defn on-success
  [fut callback]
  (.onSuccess fut
              (create-scala-partial-function callback)
              *execution-context*))

(defn on-failure
  [fut callback]
  (.onFailure fut
              (create-scala-partial-function callback)
              *execution-context*))

(defn on-complete
  [fut success-callback failure-callback]
  (.onComplete fut
               (fn [try-object]
                 (if (.isSuccess try-object)
                   (success-callback (.value try-object))
                   (failure-callback (.exception try-object))))
               *execution-context*))
