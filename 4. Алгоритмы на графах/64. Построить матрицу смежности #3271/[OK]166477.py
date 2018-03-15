f = open('input.txt', 'r')
line=f.readline()
n, m = line.split()
A = [[0] * int(n) for i in range(int(n))]
f2 = open('output.txt', 'w')
for line in f:
    a, b = line.split()
    A[int(a)-1][int(b)-1]=1
    A[int(b) - 1][int(a) - 1] = 1
for row in A:
    for elem in row:
        f2.write(str(elem))
        f2.write(' ')
    f2.write('\n')
f.close()
f2.close()