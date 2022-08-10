import numpy as np

def cdivide(f, g):   # f and g are np.polynomial.Polynomial
  
  b = g.coef[g.degree()]
  q = np.polynomial.Polynomial([0])
  r = f.copy()
  k = f.degree() - g.degree() + 1

  x = np.polynomial.Polynomial([0, 1])
  # print("b = ", b, "\n k = ", k, "\n f = ", f.coef, "\n g = ", g.coef, "\n r = ", r.coef)

  while (r.degree() >= g.degree()):
    q += b**(k-1) * r.coef[r.degree()] * x**(r.degree() - g.degree())
    r = r * b - r.coef[r.degree()] * x**(r.degree() - g.degree()) * g
    k -= 1
    # print("b = ", b, "\n k = ", k, "\n g = ", g.coef, "\n r = ", r.coef)
  r *= b**k

  return [b**max(0, f.degree() - g.degree() + 1), q, r]

def res(f, g):
  
  if (g.degree() <= 0):
    return g.coef[0];
  elif (f.degree() > g.degree()):
    return res(g, f) * (-1)**(f.degree()*g.degree())
  else:
    divisionPack = cdivide(g, f)
    return res(f, divisionPack[2]) / (f.coef[f.degree()]) ** ((g.degree() - f.degree()) * (f.degree() - 1) + divisionPack[2].degree())

# afs = [[1, 2, -3, 1], [13, 5, 4, 1], [25, -9, 1, 0, 0, 1], [-3, 1, 7, 0, 0, 0, 1]]
# ags = [[1, -1, 2], [-9, 4, 2, 3], [69, 31, 7, 2], [10, 31, 3, 0, 0, 1]]
# 
# f = np.polynomial.Polynomial([1, 2, -3, 1])
# g = np.polynomial.Polynomial([10, 31, 3, 0, 0, 1])
# 
# for i in range(0, 4):
# 
#   af = np.trim_zeros(afs[i], 'b')
#   ag = np.trim_zeros(ags[i], 'b')
# 
#   f = np.polynomial.Polynomial(af)
#   g = np.polynomial.Polynomial(ag)
# 
#   print(res(f, g))