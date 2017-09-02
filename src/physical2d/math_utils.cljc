(ns physical2d.math-utils)


(defn ^:private v+ [v1 v2]
  "sum 2 vectors"
  (mapv + v1 v2))

(defn sum [& args]
  "sum multiple vectors"
  (reduce v+ args))

(defn ^:private pair-up [e coll]
  "pair each element in coll with e"
  (map (fn [input] [e input]) coll))


(defn combos-of-2 [elements]
  "find all combination of size 2 for the input list"
  (if (<= (count elements) 1)
    '()
    (let [first-element (first elements)
          other-elements (drop 1 elements)]
      (concat (pair-up first-element other-elements)
              (combos-of-2 other-elements))))
)
