f = open('input.txt', 'r')
line=f.readline()
n=int(line)
d= [0] * int(n)
f2 = open('output.txt', 'w')
n_str=0
for line in f:
    a = list(line.split())
    for k in range(n):
        a[k]=int(a[k])
        if a[k] is 1:
            d[k]=n_str+1
        else:
            continue
    n_str+=1

for k in range(n):
    f2.write(str(d[k])+' ')

f.close()
f2.close()