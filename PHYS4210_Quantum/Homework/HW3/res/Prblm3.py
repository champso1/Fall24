from matplotlib import pyplot as plt
import numpy as np

PI = np.pi



def f1(z: np.array) -> np.array:
    X = -1 / np.tan(z)
    X[:-1][np.diff(X) < 0] = np.nan
    return X

def f2(z, z0):
    # numpy being weird, this one can't be vectorized for some reason
    return np.array([np.sqrt((z0**2 / _z**2) - 1) for _z in z])





ax = plt.subplot()
ax.axis([0,10,0,15]) # (xmin, xmax, ymin, ymax)
ax.set_xticks([0, PI/2, PI, 1.5 * PI, 2*PI, 2.5*PI, 3*PI, 3.5*PI, 4*PI])
ax.set_xticklabels(['0', '$\\pi/2$', '$\\pi$', '$3\\pi/2$', '$2\\pi$', '$5\\pi/2$', '$3\\pi$', "$3\\pi/2$", "$4\\pi$"])    



z0 = 16 
X_f1 = np.linspace(0, 4*PI, 1000, endpoint=False)
X_f2 = np.linspace(0, z0, 1000, endpoint=False)

ax.plot(X_f1, f1(X_f1), "b-", label=r"$-\cot(z)$")
ln, = ax.plot(X_f2, f2(X_f2, z0), "r-", label=r"$\sqrt{\frac{z_0^2}{z^2} - 1}$")
ax.set_title(f"Odd w.f. for z0 = {z0}")
ax.legend(loc="upper right", framealpha=1.0)


plt.savefig("./res/Prblm3_1.pdf")


ln.remove()
z0 = 1.2
ax.set_title(f"Odd w.f. for z0 = {z0}")
X_f2 = np.linspace(0, z0, 1000, endpoint=False)
ax.plot(X_f2, f2(X_f2, z0), "r-", label=r"$\sqrt{\frac{z_0^2}{z^2} - 1}$")


plt.savefig("./res/Prblm3_2.pdf")