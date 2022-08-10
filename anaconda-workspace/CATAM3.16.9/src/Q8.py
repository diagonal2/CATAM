import numpy as np

def Rh1(f):

  roots = f.roots()
  perms = [[0, 1, 2, 3], [0, 2, 1, 3], [0, 3, 1, 2]]
  q = 1
  x = np.polynomial.Polynomial([0, 1])

  for s in perms:
    q *= x - roots[s[0]] * roots[s[1]] - roots[s[2]] * roots[s[3]]
  
  return q

def Rh2(f):

  roots = f.roots()
  reducedPerms = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
  q = 1
  x = np.polynomial.Polynomial([0, 1])

  for s in reducedPerms:
    q *= x - roots[0] * roots[s[0]]**2 - roots[s[0]] * roots[s[1]]**2 - roots[s[1]] * roots[s[2]]**2 - roots[s[2]] * roots[0]**2
  
  return q

def Rh3(f):

  roots = f.roots()
  reducedPreperms = [[], [1, 2], [1, 3], [1, 4], [3, 4], [2, 4]]
  q = 1
  x = np.polynomial.Polynomial([0, 1])

  for t in reducedPreperms:
    
    s = [0, 1, 2, 3, 4, 5]
    if (len(t) == 2):
      s[t[0]] = t[1]
      s[t[1]] = t[0]
    
    tmp = 0
    for i in range(0, 5):
      tmp += roots[s[i]]**2 * (roots[s[(i+1)%5]] * roots[s[(i+4)%5]] + roots[s[(i+2)%5]] * roots[s[(i+3)%5]])
    q *= x - tmp
  
  return q