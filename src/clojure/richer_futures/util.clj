(ns richer-futures.util)

(defn fail-with
  [msg]
  (throw (RuntimeException. msg)))

(defn conceding
  "Conditional function application combinator. Meant to be used in conjunction with ->>."
  [cond fun x]
  (if cond
    (fun x)
    x))

(defn generate-n
  [fun n]
  (take n (iterate (constantly (fun)) (fun))))

(defn append
  [xs x]
  (conj (vec xs) x))

