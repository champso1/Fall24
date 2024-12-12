# Spherical Harmonics

## Setting up virtual environment

This uses only a few pretty standard packages, namely scipy, matplotlib, numpy, and some normal standard library stuff, so it should be fine to just pip install normally, or if on a Linux distribution, install the packages from your package manager. However, if you'd like to set up a virtual environment, navigate to the root of the directory, then run

```
python3 -m venv .venv
source ./.venv/bin/activate
python3 -m pip install -r ./requirements.txt
```

You can replace the `.venv` in the first line with whatever you'd like to call your virtual environment folder.


## Running the Code

To run the code, just do `./PlotSphericalHarmonics -h` to see the help menu; I'll reiterate it here: you can pass in one of two flags, the first being `--plot-all`, which will iterate over the list of m and l pairs and save them all as PDF files in the folder `./res/Figs`. Alternatively, you can psas the flag `--ml` then after that the specific m and l you'd like to plot. This one will also pop the plot up in a new window as well as saving it to the output folder.
