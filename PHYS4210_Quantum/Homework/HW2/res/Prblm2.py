import matplotlib.pyplot as plt
import numpy as np

a = 1
A = np.sqrt(12/(a**3))

def Psi(x):
    if x>=0 and x<=(a/2):
        return A*x
    elif x>(a/2) and x<=a:
        return A*(a-x)
    return np.nan



fig,ax = plt.subplots()

X = np.linspace(-1,2, 500)
y = np.array([Psi(x) for x in X])
ax.plot(X,y)

ax.set_title("Initial wave function for problem 2.7")
ax.set_ylabel("$\\Psi(x,0)$")
ax.set_xlabel("x (units of a)")

plt.savefig(".\\res\\plot.pdf")