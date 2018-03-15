file = open('input.txt')

def validate_heap(heap):
    for i in range((int)(len(heap) / 2)):
        if not heap[i] <= heap[i * 2 + 1]:
            return False
        if (2 * i + 1) + 1 < len(heap) and not heap[i] <= heap[(2 * i + 1) + 1]:
            return False
    return True

lst = []
file.readline()
line = file.readline()
for str0 in line.split(' '):
    lst.append(int(str0.strip()))

file = open('output.txt', 'w+')

if (validate_heap(lst)):
    file.write("Yes")
else:
    file.write("No")
