(ns gol-launchpad.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.spec.test.alpha :as stest]
            [overtone.live :as overtone :refer :all]
            [expound.alpha :as expound]))

;; 8 x 8
(def num-rows 8)
(def num-cols 8)
(def colors {:green 120})
(def live :O)
(def dead :_)
(def live? #{live})
(def dead? #{dead})

(s/def ::coords (s/spec (s/cat :row (s/int-in 0 num-rows), :col (s/int-in 0 num-cols))))
(s/def ::cell (s/or :live live?, :dead dead?))
(s/def ::row (s/coll-of ::cell, :kind vector?, :count num-cols))
(s/def ::board (s/coll-of ::row, :kind vector?, :count num-rows))

(s/fdef cell
  :args (s/cat :board ::board, :coords ::coords)
  :ret ::cell)
(defn- cell
  "Return the cell at `coords` on `board`."
  [board [row col :as coords]]
  (get-in board coords))

(s/fdef neighbors-
  :args (s/cat :coords ::coords)
  :ret (s/coll-of ::coords))
(defn- neighbors-
  "A helper functions for `neighbors`. Return all the neighbor coordinates for
  `coords`."
  [[row col :as coords]]
  (for [i (range -1 2), j (range -1 2)
        :when (not= [i j] [0 0])]
    [(mod (+ row i) num-rows)
     (mod (+ col j) num-cols)]))

(s/fdef neighbors
  :args (s/cat :board ::board, :coords ::coords)
  :ret nat-int?)
(defn neighbors
  "Return the number of live neighbors of a cell."
  [board [row col :as coords]]
  (->> (neighbors- coords)
       (map #(cell board %))
       (filter live?)
       count))

;; Game of Life rules

;; Any live cell with fewer than two live neighbors dies
(s/def ::underpopulation (s/and #(live? (apply cell %))
                                #(< (apply neighbors %) 2)))

;; Any live cell with two or three live neighbors survives
(s/def ::survive (s/and #(live? (apply cell %))
                        #(#{2 3} (apply neighbors %))))

;; Any live cell with more than three live neighbors dies
(s/def ::overpopulation (s/and #(live? (apply cell %))
                               #(> (apply neighbors %) 3)))

;; Any dead cell with exactly three live neighbors becomes a live cell
(s/def ::reproduction (s/and #(dead? (apply cell %))
                             #(= (apply neighbors %) 3)))

(s/fdef rules
  :args (s/cat :board ::board, :coords ::coords)
  :ret ::cell)
(defn rules
  "Update a cell on `board` according to the Game of Life rules:

  1. Any live cell with fewer than two live neighbors dies
  2. Any live cell with two or three live neighbors survives
  3. Any live cell with more than three live neighbors dies
  4. Any dead cell with exactly three live neighbors becomes a live cell."
  [board [row col :as coords]]
  (cond
    (s/valid? ::underpopulation [board coords]) dead
    (s/valid? ::survive [board coords]) live
    (s/valid? ::overpopulation [board coords]) dead
    (s/valid? ::reproduction [board coords]) live
    :else (cell board coords)))
;; midi

(def lp (first (midi-connected-receivers)))

(s/fdef ->midi-note
  :args (s/cat :coords ::coords)
  :ret nat-int?)
(defn- ->midi-note
  "Translate `coords` to its place on the Launchpad."
  [[row col :as coords]]
  (+ (* 16 row) col))

(defn- toggle-on
  [[row col :as coords]]
  (midi-note-on lp (->midi-note coords) (:green colors)))

(defn- toggle-off
  [[row col :as coords]]
  (midi-note-off lp (->midi-note coords)))

(defn- print-cell
  [board [row col :as coords]]
  (if (live? (cell board coords))
    (toggle-on coords)
    (toggle-off coords)))

(defn- print-board
  "Print `board` on the Launchpad."
  [board]
  (for [i (range num-rows), j (range num-cols)]
    (print-cell board [i j])))

(defn main-loop
  "TODO"
  [m]
  (let [beat (m)]
    (do (println beat)
        (apply-by (m (inc beat)) #'main-loop [m]))))

(comment

  (def off
    [[:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]])

  (def glider
    [[:_ :O :_ :_ :_ :_ :_ :_]
     [:_ :_ :O :_ :_ :_ :_ :_]
     [:O :O :O :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]
     [:_ :_ :_ :_ :_ :_ :_ :_]])

  (def coords-board (into [] (for [x (range num-rows)]
                               (into [] (for [y (range num-cols)] [x y])))))

  (map #(apply toggle-on %) (neighbors- [0 0]))
  (map #(apply toggle-off %) (neighbors- [0 0]))

  (print-board (sgen/generate (s/gen ::board)))

  (neighbors board [0 0])
  (print-board off)

  (set! s/*explain-out* (expound/custom-printer {:show-valid-values? true
                                                 :print-specs? false
                                                 :theme :figwheel-theme}))


  )
