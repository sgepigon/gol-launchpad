(ns gol-launchpad.patterns)

;; empty

(def barren
  [[:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]])

;; still life

(def blinker
  [[:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :👶 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]])

;; oscillators

(def beacon
  [[:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :👶 :👶 :💀 :💀 :💀 :💀 :💀]
   [:💀 :👶 :👶 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]])

;; spaceships

(def glider
  [[:💀 :👶 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :👶 :💀 :💀 :💀 :💀 :💀]
   [:👶 :👶 :👶 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]])

;; whaaaat

(def whaaaat
  [[:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :👶 :👶 :💀 :💀 :💀 :💀]
   [:💀 :👶 :👶 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :👶 :👶 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]])

;; cross-tetris

(def cross-tetris
  [[:💀 :💀 :💀 :💀 :💀 :💀 :💀 :👶]
   [:💀 :👶 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :👶 :💀 :💀 :💀 :👶 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :👶 :👶 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :👶 :💀 :💀 :💀 :👶 :💀]])

(def peek-a-boo
  [[:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]
   [:💀 :💀 :💀 :👶 :👶 :💀 :💀 :💀]])

(def blah
  [[:💀 :👶 :💀 :💀 :💀 :💀 :👶 :💀]
   [:👶 :💀 :💀 :💀 :💀 :💀 :💀 :👶]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:💀 :💀 :💀 :💀 :💀 :💀 :💀 :💀]
   [:👶 :💀 :💀 :💀 :💀 :💀 :💀 :👶]])
