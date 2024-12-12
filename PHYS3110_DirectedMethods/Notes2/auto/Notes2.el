;; -*- lexical-binding: t; -*-

(TeX-add-style-hook
 "Notes2"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("article" "titlepage")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("PreambleCommon" "") ("Preamble" "")))
   (TeX-run-style-hooks
    "latex2e"
    "article"
    "art10"
    "PreambleCommon"
    "Preamble")
   (LaTeX-add-labels
    "eq:1"))
 :latex)

