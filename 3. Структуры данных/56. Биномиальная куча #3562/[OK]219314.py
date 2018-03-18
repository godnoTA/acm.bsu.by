with open('input.txt') as file:
    N = str(bin(int(file.readline())))
N = N[2:]
isPossible = False
with open('output.txt', 'w') as file:
    for i in range(len(N) - 1, -1, -1):
        if N[i] == '1':
            isPossible = True
            file.write(str(len(N) - i - 1) + '\n')
    if isPossible is False:
        file.write(str(-1))
