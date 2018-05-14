import string
inFile = open("input.txt", 'r')
n = int(inFile.readline())
binStr = bin(n)
list = []
for i in range(len(binStr)):
    if binStr[len(binStr) - i - 1] == '1':
        list += [i]

outFile = open("output.txt", 'w')
for i in range(len(list)):
    outFile.write(str(list[i]) + "\n")