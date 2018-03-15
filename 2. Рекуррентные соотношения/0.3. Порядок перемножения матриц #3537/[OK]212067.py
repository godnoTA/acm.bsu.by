dimensions = []

file = open("input.txt")
n = int(file.readline().strip())
matrix = [[0 for _ in range(0, n)] for _ in range(0, n)]

def func(i, j):
    lst = []
    for k in range(i, j):
        lst.append(matrix[i][k] + matrix[k + 1][j] + dimensions[i][0] * dimensions[k][1] * dimensions[j][1])
    return min(lst)


for line in file:
    line = line.strip().split(' ')
    dimensions.append([int(line[0]), int(line[1])])

for i in range(1, n):
    for j in range(0, n - i):
        matrix[j][j + i] = func(j, j + i)

wr = open('output.txt', 'w+')
wr.write(str(matrix[0][n - 1]))