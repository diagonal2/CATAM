import numpy as np
import Q3
import Q8

def writePoly(f):
  m = len(f.coef) - 1
  s = ""
  for i in range(m, -1, -1):
    if (i == 0):
      s += str(round(np.real(f.coef[0])))
    elif (i == 1):
      s += str(round(np.real(f.coef[1]))) + "x + "
    else:
      s += str(round(np.real(f.coef[i]))) + "x^" + str(i) + " + "
  return s

afs = [[1, -6, -7, 0, 1], [10, 9, 0, -1, 1], [6, 22, 23, 2, 1], 
       [-3, -1, -7, -1, 0, 1], [3, -7, 8, 0, -1, 1], [6, -1, -3, 6, -2, 1]]

for af in afs:

  af = np.trim_zeros(af, 'b')
  f = np.polynomial.Polynomial(af)
  print("f(x) =", writePoly(f))

  print("disc(f) =", round(Q3.res(f, f.deriv())))

  if (f.degree() == 4):
    print("R_h1(f)(x) =", writePoly(Q8.Rh1(f)))
    print("R_h2(f)(x) =", writePoly(Q8.Rh2(f)))
  elif (f.degree() == 5):
    print("R_h3(f)(x) =", writePoly(Q8.Rh3(f)))
  
  print()