inFile = open("input.txt", 'r')
n = int(inFile.readline())
dict = {}
for i in range(n):
    dict[i] = 0

for i in range(n - 1):
    list = inFile.readline().split()
    u, v = int(list[0]), int(list[1])
    dict[v - 1] = u

outFile = open("output.txt", 'w')
for i in range(n):
    print(str(dict[i]) + " ")
    outFile.write(str(dict[i]) + " ")