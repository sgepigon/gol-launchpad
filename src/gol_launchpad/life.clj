(ns gol-launchpad.life
  (:refer-clojure :exclude [next])
  (:require [clojure.spec.alpha :as s]))

;; 8 x 8
(def num-rows 8)
(def num-cols 8)
(def coordinates (for [row (range num-rows), col (range num-cols)] [row col]))

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
(defn cell
  "Return the cell at `coords` on `board`."
  [board [row col :as coords]]
  (get-in board coords))

(s/fdef neighbors-
  :args (s/cat :coords ::coords)
  :ret (s/coll-of ::coords :count 8))
(defn- neighbors-
  "A helper functions for `neighbors`. Return all the neighbor coordinates for
  `coords`."
  [[row col :as coords]]
  (for [i (range -1 2), j (range -1 2)
        :when (not= i j 0)]
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

(s/fdef transition
  :args (s/cat :board ::board, :coords ::coords)
  :ret ::cell)
(defn transition
  "Return a cell on `board` according to the Game of Life rules:

  1. Any live cell with fewer than two live neighbors dies
  2. Any live cell with two or three live neighbors survives
  3. Any live cell with more than three live neighbors dies
  4. Any dead cell with exactly three live neighbors becomes a live cell."
  [board [row col :as coords]]
  (condp s/valid? [board coords]
    ::underpopulation dead
    ::survive live
    ::overpopulation dead
    ::reproduction live
    dead))

(s/fdef next
  :args (s/cat :board ::board)
  :ret ::board)
(defn next
  "Return the next board."
  [board]
  (reduce #(assoc-in %1 %2 (transition board %2)) board coordinates))
