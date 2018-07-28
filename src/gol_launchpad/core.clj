(ns gol-launchpad.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.spec.test.alpha :as stest]
            [overtone.live :as overtone :refer :all]))

(def glider [[:dead :live :dead :dead :dead :dead :dead :dead]
             [:dead :dead :live :dead :dead :dead :dead :dead]
             [:live :live :live :dead :dead :dead :dead :dead]
             [:dead :dead :dead :dead :dead :dead :dead :dead]
             [:dead :dead :dead :dead :dead :dead :dead :dead]
             [:dead :dead :dead :dead :dead :dead :dead :dead]
             [:dead :dead :dead :dead :dead :dead :dead :dead]
             [:dead :dead :dead :dead :dead :dead :dead :dead]])

;; 8 x 8
(def n 8)
(def m 8)

(s/def ::coords (s/cat :x (s/int-in 0 n) :y (s/int-in 0 m)))
(s/def ::cell (s/or :live #{:live}, :dead #{:dead}))
(s/def ::row (s/coll-of ::cell, :kind vector?, :count n))
(s/def ::board (s/coll-of ::row, :kind vector?, :count m))

(def live? #{:live})

;; midi

(def lp (first (midi-connected-receivers)))

(s/fdef ->midi-note
  :args (s/cat :coords ::coords)
  :ret pos-int?)

(defn ->midi-note
  ""
  [x y]
  (+ (* 16 y) x))

(defn toggle-on [x y]
  (midi-note-on lp (->midi-note x y) 120))

(defn toggle-off [x y]
  (midi-note-off lp (->midi-note x y)))

(defn neighbors- [x y]
  (->> (for [i (range -1 2)
             j (range -1 2)]
         [(mod (+ x i) n)
          (mod (+ y j) m)])
       (remove #{[x y]})))

(defn cell [board [x y]]
  (get-in board [y x]))

(defn neighbors
  ""
  [board x y]
  (->> (neighbors- x y)
       (map #(cell board %))
       (filter live?)
       count))

#_(neighbors board 0 0)

(defn main-loop [m]
  (let [beat (m)]
    (do (println beat)
        (apply-by (m (inc beat)) #'main-loop [m]))))

(defn print-cell [board x y]
  (if (live? (cell board [x y]))
    (toggle-on x y)
    (toggle-off x y)))

(defn print-board [board]

  (for [i (range n) j (range m)]
    (print-cell board i j)))

#_(print-board (sgen/generate (s/gen ::board)))

(def off [[:dead :dead :dead :dead :dead :dead :dead :dead]
          [:dead :dead :dead :dead :dead :dead :dead :dead]
          [:dead :dead :dead :dead :dead :dead :dead :dead]
          [:dead :dead :dead :dead :dead :dead :dead :dead]
          [:dead :dead :dead :dead :dead :dead :dead :dead]
          [:dead :dead :dead :dead :dead :dead :dead :dead]
          [:dead :dead :dead :dead :dead :dead :dead :dead]
          [:dead :dead :dead :dead :dead :dead :dead :dead]])

(print-board off)

#_(neighbors 0 0)

(comment

  (map #(apply toggle-off %) (neighbors 0 0))
  (map #(apply toggle-on %) (neighbors 0 0))

 (main-loop (metronome 120))

  (s/exercise ::board 1)

  (defn rand-seed [] (rand-nth [0 1]))

  (let [receiver ]
                                        ;Play a midi note c4 at 80 velocity for 1 millisecond on the fourth channel
                                        ;Note that the channel is zero-indexed, whereas normal mixers/midi devices start counting them from 1.
    (overtone.midi/midi-note receiver (note :c4) 80 1 3)

                                        ;Turn on the sustain pedal to full on the first channel
                                        ;64 is the midi control number for the sustain (damper) pedal.
    (midi-note-on lp (->midi-note 7 7) 120 )

    (midi-note-off lp (->midi-note 7 7))

    (let [{:keys [bpm else start] :as nome} (metronome 120)]

      {:bpm bpm
       :else else
       :start start}

      (defn ->frequency
        ""
        []

        )


      )


)

  )
