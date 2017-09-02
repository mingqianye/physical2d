(ns physical2d.spec-test
  (:require [clojure.test :refer :all]
            [physical2d.spec :refer :all]))

(deftest gen-test
  (testing "generate new body"
    (print "------------------------------------------------")
    (print (generate-world))))

(deftest sanity-check-test
  (testing "Sanity check all specs"
    (is (true? (sanity-check)))))


