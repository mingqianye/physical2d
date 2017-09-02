(ns physical2d.math-util-test
  (:require [clojure.test :refer :all]
            [physical2d.math-utils :refer :all]
            ))

(deftest next-frame-test
  (testing "next frame consumes a world and returns a world"
    (is (true? (= [4 5] (sum [1 2] [3 3]))))
    (is (true? (= [8 10] (sum [1 2] [3 3] [4 5]))))
    (is (true? (= [4 5 3] (sum [1 2 2] [3 3 1]))))
    (is (true? (= [9 11 10] (sum [1 2 2] [3 3 1] [5 6 7]))))
))
