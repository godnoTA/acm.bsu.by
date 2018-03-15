def createMatrix(rowCount, colCount):
    mat = []
    for i in range(rowCount):
        rowList = []
        for j in range(colCount):
            rowList.append(0)
        mat.append(rowList)

    return mat

with open('input.txt') as file:
    N = int(file.readline())
    sizes = []
    string = file.readline()
    sizes.append(int(string.split(" ")[0]))
    sizes.append(int(string.split(" ")[1]))
    for i in range(2, N + 1):
        sizes.append(int((file.readline()).split(" ",2)[1]))

dp = createMatrix(N,N)
for i in range(N - 1):
    dp[i][i+1] = sizes[i] * sizes[i+1]* sizes[i+2]

line = 2
while(line <= N):
    for i in range(N + 1 - line):
            temp = []
            j = line - 1 + i
            for k in range(i, j):
               temp.append(dp[i][k] + dp[k+1][j] + sizes[i]*sizes[k+1]* sizes[j+1])
            dp[i][j] = min(temp)
    line += 1
answer = int(dp[0][N - 1])
with open('output.txt','w') as file:
    file.write(str(answer))