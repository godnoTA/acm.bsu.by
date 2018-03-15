file = open('input.txt')

n, m = 0, 0

line1 = file.readline()
line1 = line1.rstrip().split(' ')

n, m = int(line1[0]), int(line1[1])
lst = [[0 for _ in range(n)] for _ in range(n)]

for line in file:
    l = line.rstrip().split(' ')
    n1 = int(l[0])
    n2 = int(l[1])
    lst[n1 - 1][n2 - 1] = 1
    lst[n2 - 1][n1 - 1] = 1


file = open('output.txt', 'w+')

for i in lst:
    for j in i:
        file.write(str(j))
        file.write(' ')
    file.write('\n')


