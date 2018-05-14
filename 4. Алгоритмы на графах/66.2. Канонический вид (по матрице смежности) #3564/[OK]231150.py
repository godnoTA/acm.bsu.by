inFile = open("input.txt", 'r')
n = int(inFile.readline())
dict = {}
for i in range(n):
    dict[i] = 0

for i in range(n):
    list = inFile.readline().split()
    for j in range(n):
        if list[j] == '1':
            dict[j] = i + 1

outFile = open("output.txt", 'w')
for i in range(n):
    outFile.write(str(dict[i]) + " ")