;; -*- lexical-binding: t; -*-

(TeX-add-style-hook
 "Project1"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("article" "titlepage")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("PreambleCommon" "") ("Preamble" "") ("import" "")))
   (TeX-run-style-hooks
    "latex2e"
    "./res/FeynmanDiagrams/Diagram"
    "article"
    "art10"
    "PreambleCommon"
    "Preamble"
    "import")
   (LaTeX-add-labels
    "fig:MainDiagram"
    "eq:GeneralResult"
    "eq:1"
    "eq:2"))
 :latex)

