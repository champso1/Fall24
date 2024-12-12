from matplotlib import pyplot as plt
import numpy as np



def Omega(t):
    return np.sqrt(1/(1+t^2))


def PsiSquared(x,t):
    return np.sqrt(2/np.pi) * Omega(t) * np.exp(-2 * (Omega(t)**2) * (x**2))



X = np.linspace(-5, 5,500)
t = [0,20]
y = np.array([PsiSquared(X,_t) for _t in t])

fig,ax = plt.subplots()

ax.set_ylim(0,0.5)
ax.plot(X,y[0], label="$\\Psi(x,0)$")
ax.plot(X,y[1], label="$\\Psi(x,20)$")
ax.legend(loc="upper right")
plt.savefig(".\\res\\Prblm4.pdf")