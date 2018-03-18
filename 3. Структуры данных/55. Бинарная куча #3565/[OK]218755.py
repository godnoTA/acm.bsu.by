status = True
with open('input.txt') as file:
    N = int(file.readline())
    heap = []
    for digit in (file.readline()).split(" "):
        heap.append(int(digit))


for j in range(N):
    try:
        if(heap[j] > heap[2*j + 1] or heap[j] > heap[2*j + 2]): status = False
    except IndexError: continue

with open('output.txt','w') as file:
    if(status == True): file.write('Yes')
    else: file.write('No')



