import numpy as np

def res(f, g):  # f and g are np.polynomial.Polynomial
  rootsf = f.roots()
  # rootsg = g.roots()
  tmp = f.coef[f.degree()]**g.degree()
  for z in rootsf:
    tmp *= g(z)
  return tmp

afs = [[2, -3, 1], [2, -3, 1], [-6, -7,-6, 0, 1], [1, 3, 7, 9], [3, 1, 4, 1]]
ags = [[9, -6, 1], [4, -4, 1], [-1, 0, 0, 1], [2, -4, 6, -8, 10], [9, 2, 6, 5]]

j = complex(0, 1)

for i in range(0, 5):
  
  af = np.trim_zeros(afs[i], 'b')
  ag = np.trim_zeros(ags[i], 'b')
  
  f = np.polynomial.Polynomial(af)
  g = np.polynomial.Polynomial(ag)
  
  print(res(f, g))