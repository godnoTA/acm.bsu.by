f = open('input.txt', 'r')
line=f.readline()
n=int(line)
d= [0] * int(n)
f2 = open('output.txt', 'w')
for line in f:
    a, b = line.split()
    a=int(a)
    b=int(b)
    d[b-1]=a

for k in range(n):
    f2.write(str(d[k])+' ')

f.close()
f2.close()