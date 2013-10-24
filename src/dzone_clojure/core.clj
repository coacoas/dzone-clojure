(ns dzone-clojure.core
  (:require [clojure.pprint :as pp]
            [clojure.core.matrix :as m]))

(def sum #(reduce + %))

(defn split-regions [v]
  (map m/transpose
       (mapcat #(partition-all 3 (m/transpose %))
               (partition-all 3 v))))

(defn has-sudoku-properties? [v]
  (let [uniq (set v)
        sum-target (sum (range 1 10))]
    (and (= sum-target (sum uniq))
         (= 9 (count uniq)))))

(defn valid-sudoku? [v]
  (let [rows v
       cols (m/transpose v)
       regions (split-regions v)]
    (let [xs (concat rows cols (map flatten regions))]
      (reduce #(and %1 %2)
              (map has-sudoku-properties? xs)))))
