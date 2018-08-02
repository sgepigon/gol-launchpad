(ns gol-launchpad.lp
  (:require [clojure.spec.alpha :as s]
            [overtone.studio.midi :as midi]
            [gol-launchpad.life :as life]))

(def colors {:green 120
             :orange 127})

(def ^:private lp (first (midi/midi-connected-receivers)))

(s/fdef coords->midi-note
        :args (s/cat :coords ::life/coords)
        :ret nat-int?)
(defn coords->midi-note
  "Translate `coords` to its place on the Launchpad."
  [[row col :as coords]]
  (+ (* 16 row) col))

(s/fdef midi-note->coords
        :args (s/cat :midi-note nat-int?)
        :ret ::life/coords)
(defn midi-note->coords
  [midi-note]
  (apply (juxt quot rem) [midi-note 16]))

(defn paint
  [coords color]
  (midi/midi-note-on lp (coords->midi-note coords) (color colors)))

(defn erase
  [coords]
  (midi/midi-note-off lp (coords->midi-note coords)))

(defn paint-born
  [[row col :as coords]]
  (paint coords :green))

(defn paint-alive
  [[row col :as coords]]
  (paint coords :orange))

(defn paint-dead
  [[row col :as coords]]
  (erase coords))

(defn toggle-cell
  [board [row col :as coords]]
  (let [cell (life/cell board coords)]
    (cond
      (life/born? cell) (paint-born coords)
      (life/live? cell) (paint-alive coords)
      (life/dead? cell) (paint-dead coords)
      :else (paint-dead coords))))

(defn print-board
  "Print `board` on the Launchpad."
  [board]
  (doseq [coords life/coordinates] (toggle-cell board coords)))
