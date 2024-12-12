;; -*- lexical-binding: t; -*-

(TeX-add-style-hook
 "PreambleHW"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("tikz-feynman" "compat=1.1.0") ("titlesec" "") ("enumitem" "")))
   (TeX-run-style-hooks
    "tikz-feynman"
    "titlesec"
    "enumitem")
   (LaTeX-add-enumitem-newlists
    '("parts" "enumerate")))
 :latex)

