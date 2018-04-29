f = open('in.txt', 'r')
n, m, k = [int(x) for x in f.readline().split()]

ans = 0
for i in range(1, n + 1):
    ans = (ans + m) % i

f.close()
f = open('out.txt', 'w')
ans = ans + 1
f.write(str(ans))
if k >= ans:
    ans = 1 + k - ans
else:
    ans = 1 + n - ans + k
f.write('\n' + str(ans))
f.close()
