(ns gol-launchpad.sound
  (:require [clojure.spec.alpha :as s]
            [gol-launchpad.life :as life]
            [overtone.live :refer :all]))

(s/def ::fn-interval (s/fspec :args (s/cat :x number?) :ret number?))
(s/def ::rows pos-int?)
(s/def ::cols pos-int?)

(defn- p4
  [x]
  (float (* x 4/3)))

(defn- p5
  [x]
  (float (* x 3/2)))

(s/fdef ->table
  :args (s/cat :root number?
               :fn-row ::fn-interval :fn-col ::fn-interval
               :dimensions (s/keys* :opt-un [::rows ::cols]))
  :ret (s/coll-of (s/coll-of number? :kind vector?) :kind vector?))
(defn- ->table
  "Returns a table with intervals that satisfy `fn-row` across and `fn-col`down,
  starting at `root`, with dimension `rows` by `cols` (default 8x8)."
  [root fn-row fn-col & {:keys [rows cols] :or {rows 8 cols 8}}]
  (letfn [(->interval [f n x] (vec (take n (iterate f x))))]
    (->> root
         (->interval fn-row rows)
         (mapv (partial ->interval fn-col cols)))))

(def ^:private lookup-table (->table 50 p4 p5))

(defsynth s [freq 440]
  (let [tonic (sin-osc freq)
        h1 (* 1/2 (sin-osc (* freq 2)))
        h2 (* 1/4 (sin-osc (* freq 4)))
        h3 (* 1/8 (sin-osc (* freq 8/3)))
        syn (+ tonic h1 h2 h3)
        sig (lpf (* 0.3
                    (* (env-gen (perc) :action FREE) syn))
                 1600)]
    (out 0 [sig (delay-c sig 0.01 0.01)])))

(comment

  (defsynth s [freq 440]
    (let [factor 2
          mul 20
          env (perc)
          sig (* 0.3 (* (env-gen env :action FREE) (sin-osc (+ freq (* mul (sin-osc (* freq factor)))))))]
      (out 0 [sig (delay-c sig 0.01 0.01)])))
  )

(defn play
  [board coords]
  (when (life/live? (life/cell board coords))
    (s (get-in lookup-table coords))))

(defn play-board
  [board]
  (doseq [coords life/coordinates] (play board coords)))
