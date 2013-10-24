(ns dzone-clojure.core-test
  (:use clojure.test
        dzone-clojure.core))

(def valid
  [[3 6 2 9 7 8 5 4 1]
   [5 7 4 3 1 6 2 9 8]
   [8 1 9 5 2 4 6 7 3]
   [2 5 8 4 6 9 3 1 7]
   [1 9 3 7 5 2 4 8 6]
   [6 4 7 8 3 1 9 2 5]
   [4 3 6 2 8 7 1 5 9]
   [7 2 1 6 9 5 8 3 4]
   [9 8 5 1 4 3 7 6 2]])

(def region-error
  [[3 5 8 2 1 6 4 7 9]
   [6 7 1 5 9 4 3 2 8]
   [9 3 5 4 7 8 2 6 1]
   [2 4 9 8 3 7 6 1 5]
   [7 1 2 6 5 3 8 9 4]
   [8 6 4 9 2 1 7 5 3]
   [5 2 6 3 4 9 1 8 7]
   [4 9 7 1 8 2 5 3 6]
   [1 8 3 7 6 5 9 4 2]])

(def column-error
  [[3 5 8 2 1 6 4 7 9]
   [7 6 1 5 9 4 3 2 8]
   [2 4 9 8 3 7 6 1 5]
   [9 3 5 4 7 8 2 6 1]
   [7 1 2 6 5 3 8 9 4]
   [8 6 4 9 2 1 7 5 3]
   [5 2 6 3 4 9 1 8 7]
   [4 9 7 1 8 2 5 3 6]
   [1 8 3 7 6 5 9 4 2]])

(def row-error
  [[3 5 8 2 1 6 4 7 9]
   [6 7 1 5 9 4 3 2 8]
   [2 4 9 8 3 7 6 1 5]
   [9 3 5 4 7 8 2 6 1]
   [7 1 2 6 5 3 8 9 4]
   [8 6 4 9 2 1 7 5 3]
   [5 2 6 3 4 2 1 8 7]
   [4 9 7 1 8 9 5 3 6]
   [1 8 3 7 6 5 9 4 2]])

(deftest grouping-vector
  (is (= (partition-all 3 [0 1 2 3 4 5 6])
         (list [0 1 2] [3 4 5] [6]))))

(deftest sudoku-regions
  (is
   (= (split-regions valid)
      [[[3 6 2] [5 7 4] [8 1 9]]
       [[9 7 8] [3 1 6] [5 2 4]]
       [[5 4 1] [2 9 8] [6 7 3]]
       [[2 5 8] [1 9 3] [6 4 7]]
       [[4 6 9] [7 5 2] [8 3 1]]
       [[3 1 7] [4 8 6] [9 2 5]]
       [[4 3 6] [7 2 1] [9 8 5]]
       [[2 8 7] [6 9 5] [1 4 3]]
       [[1 5 9] [8 3 4] [7 6 2]]])))

(deftest valid-sudoku
  (is (valid-sudoku? valid))
  (is (not (valid-sudoku? region-error)))
  (is (not (valid-sudoku? column-error)))
  (is (not (valid-sudoku? row-error))))

(run-tests)
