from scipy.special import lpmv as legendre

from matplotlib import pyplot as plt
from matplotlib import cm

import numpy as np
import math

from functools import partial
import argparse

import os


# the figures are saved as .pdf's,
# so ensure that the output directory exists
# otherwise, create it
# NOTE: files are saved like <save_dir>/fig_<m>_<l>.pdf
save_dir = "./res/Figs"
if not os.path.exists(save_dir):
    os.mkdir(save_dir)


    
# grab arguments from the user
# can either specify to plot all the predetermined spherical harmonic
# or they can specify an l and m value to plot a single spherical harmonic
parser = argparse.ArgumentParser(prog="PlotSphericalHarmonics", description="Plot the angular spherical harmonics.")
arg_group = parser.add_mutually_exclusive_group(required=True)
arg_group.add_argument("--plot-all", action="store_true", help="Plot all given spherical harmonics.")
arg_group.add_argument("--ml", nargs=2, type=int, metavar=("M", "L"), help="Plot only the specified spherical harmonic Y^m_l.")

args = parser.parse_args()




"""
computes the (l,m)th spherical harmonic given a value for theta and phi
this is vectorized meaning theta and phi can be numpy arrays
there is an option to have the radius array normalized
such that the largest number is 1
"""

def SphericalHarmonic(theta, phi, m, l, normalize=True):
    coeff = np.sqrt(((2*l + 1)/(4*np.pi))*(math.factorial(l-m)/math.factorial(l+m)))
    
    # instead of the complex exponential, just take the real part, i.e. the cosine
    # I think it does this anyways when the value is used elsewhere, but just to not hide it
    phase = np.cos(m*phi)

    r = np.abs(coeff * phase * legendre(m, l, np.cos(theta)))

    return r if not normalize else r/np.max(r)



"""
helper function to convert an (r,theta,phi) point (or numpy array)
 to cartesian coordinates so they can be plotted by matplotlib
"""
def SphericalToCartesian(r, theta, phi):
    x = r * np.sin(theta) * np.cos(phi)
    y = r * np.sin(theta) * np.sin(phi)
    z = r * np.cos(theta)

    return x,y,z



# define 1000 (evenly spaced) theta's and phi's
theta = np.linspace(0, np.pi, 1000)
phi = np.linspace(0, 2*np.pi, 1000)


# creates the coordinate points, basically
Theta,Phi = np.meshgrid(theta, phi)


# if we provided only a single (m,l) pair to plot
if args.ml:
    m,l = args.ml
    SphericalHarmonicPartial = partial(SphericalHarmonic, m=m, l=l)
    r = SphericalHarmonicPartial(Theta,Phi)
    
    # pyplot can't do spherical coordinates, so we convert to cartesian
    X,Y,Z = SphericalToCartesian(r, Theta, Phi)
    
    # define the plot to take 3d values
    fig,ax = plt.subplots(subplot_kw={"projection": "3d"})

    # plot the spherical harmonic with some nice colors
    surf = ax.plot_surface(X,Y,Z, cmap=cm.viridis, linewidth=0, antialiased=False)

    # add a colorbar so we know what the colors correspond to
    fig.colorbar(surf, shrink=0.5, aspect=5)

    # show the plot
    ax.set_title(f"Spherical Harmonic $Y^{{ {m} }}_{l}$")
    plt.savefig(os.path.join(save_dir, f"fig_{m}_{l}.pdf"))
    plt.show()

# if we said to plot all
elif args.plot_all:
    # define all the (m,l) pairs
    ML_PAIRS = [
        [0 ,0],
        [0 ,1],
        [1 ,1],
        [0 ,2],
        [1 ,2],
        [-2,2]
    ]

    for i,ml_pair in enumerate(ML_PAIRS):
        m,l = ml_pair
        SphericalHarmonicPartial = partial(SphericalHarmonic, m=m, l=l)
        
        r = SphericalHarmonicPartial(Theta,Phi)
        X,Y,Z = SphericalToCartesian(r, Theta, Phi)
        
        fig,ax = plt.subplots(subplot_kw={"projection": "3d"})
        surf = ax.plot_surface(X,Y,Z, cmap=cm.viridis, linewidth=0, antialiased=False)
        fig.colorbar(surf, shrink=0.5, aspect=5)

        ax.set_title(f"Spherical Harmonic $Y^{{ {m} }}_{l}$")
        plt.savefig(f"./res/Figs/fig_{m}_{l}.pdf")
    
else:
    parser.print_help()
    raise ValueError("ERROR: Invalid input!")
