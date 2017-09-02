(ns physical2d.math-util-test
  (:require [clojure.test :refer :all]
            [physical2d.math-utils :refer :all]
            ))

(deftest sum-test
  (testing "Sum multiple vectors"
    (is (= [4 5] (sum [1 2] [3 3])))
    (is (= [8 10] (sum [1 2] [3 3] [4 5])))
    (is (= [4 5 3] (sum [1 2 2] [3 3 1])))
    (is (= [9 11 10] (sum [1 2 2] [3 3 1] [5 6 7])))
))

(deftest combos-of-2-test
  (testing "Sum multiple vectors"
    (is (= [] (combos-of-2 [1])))
    (is (= [[1 2]] (combos-of-2 [1 2])))
    (is (= [[1 2] [1 3] [2 3]] (combos-of-2 [1 2 3])))
))

