(ns physical2d.math-utils)


(defn ^:private v+ [v1 v2]
  (mapv + v1 v2))

(defn sum [& args]
  "sum multiple vectors"
  (reduce v+ args))
