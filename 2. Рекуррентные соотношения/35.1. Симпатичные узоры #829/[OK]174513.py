def get_bit(num, bit):
    if num & (1 << bit):
        return 1
    else:
        return 0;


f = open('in.txt', 'r')
n = int(f.readline())
m=int(f.readline())
f.close()
if n > m:
    n, m = m, n
res = (1 << n)
MAX_HEIGHT = 10;
MAX_MASK = (1 << MAX_HEIGHT) ;
adj = []
for r in range(MAX_MASK+1):
    adj.append([])
    for c in range(MAX_MASK+1):
        adj[r].append(0)
d = []
for r in range(31):
    d.append([])
    for c in range(MAX_MASK+1):
        d[r].append(0)
for i in range(res):
    for j in range (res):
        isComp = 1;
        for bit in range( n-1):
            sum = get_bit(i, bit) + get_bit(i, bit + 1) + get_bit(j, bit) + get_bit(j, bit + 1)
            if (sum == 0 or sum == 4):
                isComp = 0
                break
        d[i][j] = isComp;
for i in range(res):
    adj[0][i]=1
for k in range(1,m):
    for i in range(res):
        for j in range (res):
            adj[k][i]=adj[k][i]+adj[k-1][j]*d[j][i]
ans=0
for i in range(res):
    ans=ans+adj[m-1][i]
f2=open("out.txt", "w")
f2.write(str(ans))
f2.close()
