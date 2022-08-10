import numpy as np

def cdivide(f, g):   # f and g are polys in x and y
  
  b = g.coef[g.degree()]
  q = np.polynomial.Polynomial([np.polynomial.Polynomial([0])])
  r = f.copy()
  k = f.degree() - g.degree() + 1

  x = np.polynomial.Polynomial([0, 1])
  # print("b = ", b, "\n k = ", k, "\n f = ", f.coef, "\n g = ", g.coef, "\n r = ", r.coef)

  while (r.degree() >= g.degree()):
    
    q += b**(k-1) * r.coef[r.degree()] * x**(r.degree() - g.degree())
    
    ar = r.coef.copy()
    for i in range(0, len(ar)):
      ar[i] *= b
    ag = g.coef.copy()
    for i in range(0, len(ag)):
      ag[i] *=  r.coef[r.degree()]
    ag = np.concatenate([np.full(r.degree() - g.degree(), np.polynomial.Polynomial([0])), ag])
    r = np.polynomial.Polynomial(ar - ag)
    while (r.coef[r.degree()] == np.polynomial.Polynomial([0])):
      r = r.truncate(r.degree())

    k -= 1

    # print("b = ", b, "\n k = ", k, "\n g = ", g.coef, "\n r = ", r.coef)

  r *= b**k

  return [b**max(0, f.degree() - g.degree() + 1), q, r]

def divide(f, g):  # f and g are polys in y

  b = g.coef[g.degree()]
  q = np.polynomial.Polynomial([0])
  r = f.copy()

  y = np.polynomial.Polynomial([0, 1])

  while (r.degree() >= g.degree() and not(r.degree() <= 0 and r.coef[0] == 0)):
    a = r.coef[r.degree()]
    if (a % b == 0):
      tmp = a / b * y**(r.degree() - g.degree())
      q = q + tmp
      r = r - g * tmp
    else:
      return null
  
  if (r.degree() <= 0 and r.coef[0] == 0):
    return q
  else:
    return null

def res(f, g):
  
  if (g.degree() <= 0):
    return g.coef[0]
  elif (f.degree() > g.degree()):
    return res(g, f) * ((-1)**(f.degree()*g.degree()))
  else:
    divisionPack = cdivide(g, f)
    return divide(res(f, divisionPack[2]), (f.coef[f.degree()]) ** ((g.degree() - f.degree()) * (f.degree() - 1) + divisionPack[2].degree()))

f0 = np.polynomial.Polynomial([4, 1, -3])
f1 = np.polynomial.Polynomial([6, -2])
f2 = np.polynomial.Polynomial([2])

g0 = np.polynomial.Polynomial([-4, -6, -2])
g1 = np.polynomial.Polynomial([-3])
g2 = np.polynomial.Polynomial([3])

f = np.polynomial.Polynomial([f0, f1, f2])
g = np.polynomial.Polynomial([g0, g1, g2])

print(res(f, g))

f0 = np.polynomial.Polynomial([-4, -2, 2])
f1 = np.polynomial.Polynomial([-1, 3])
f2 = np.polynomial.Polynomial([2])

g0 = np.polynomial.Polynomial([-16, 0, 4])
g1 = np.polynomial.Polynomial([0, 4])
g2 = np.polynomial.Polynomial([5])

f = np.polynomial.Polynomial([f0, f1, f2])
g = np.polynomial.Polynomial([g0, g1, g2])

print(res(f, g))