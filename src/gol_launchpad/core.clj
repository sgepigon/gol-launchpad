(ns gol-launchpad.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.spec.test.alpha :as stest]
            [expound.alpha :as expound]
            [gol-launchpad.life :as life]
            [gol-launchpad.patterns :as patterns]
            [gol-launchpad.lp :as lp]
            [gol-launchpad.sound :as sound]
            [overtone.live :as overtone :refer :all]))

(def board (atom patterns/barren))

(defn main-loop
  "TODO"
  [m]
  (let [beat (m)]
    (do (lp/print-board @board)
        (sound/play-board @board)
        (swap! board life/next)
        (apply-by (m (inc beat)) #'main-loop [m]))))

(on-event [:midi :note-on]
          (fn [{:keys [note]}]
            (swap! board #(life/toggle-cell % (lp/midi-note->coords note))))
          ::lp-handler)

(comment

  (def m (metronome 200))

  (main-loop m)

  (stop)

  (set! s/*explain-out* (expound/custom-printer {:show-valid-values? true
                                                 :print-specs? false
                                                 :theme :figwheel-theme}))

  (remove-event-handler ::lp-handler))
