(defproject richer-futures "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.scala-lang/scala-library "2.10.3"]
                 [org.clojure/algo.monads "0.1.4"]
                 [slingshot "0.10.3"]]

  :aot :all)
