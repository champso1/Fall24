from matplotlib import pyplot as plt
import numpy as np



LAMBDA = 1

def PsiSquared(x):
    return LAMBDA*np.exp(-2*LAMBDA*np.abs(x))

sigma_x = 1.0/(np.sqrt(2)*LAMBDA)

X = np.linspace(-2, 2, 200)
y = PsiSquared(X)

fig, ax = plt.subplots()
ax.set_title("Probability density for wave function in Problem 1.5")
ax.plot(X,y)
ax.set_ylim(np.min(y)-0.1, np.max(y)+0.1)
ax.set_xlabel("x")
ax.set_ylabel("$|\\Psi|^2$")
ax.vlines(x= sigma_x, ymin=np.min(y)-0.1, ymax=np.max(y)+1, color="black", linestyle="--")
ax.vlines(x=-sigma_x, ymin=np.min(y)-0.1, ymax=np.max(y)+1, color="black", linestyle="--")

plt.savefig("plot.pdf")