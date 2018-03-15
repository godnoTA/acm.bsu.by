filik=open('input.txt','r')
N=int(filik.readline())
tempor=1
mmas=[[]]
used=[False for i in range(N)]
temp=[0 for i in range (N)]
for line in filik:
    stroke=line.split()
    for i in range(N):
        stroke[i]=int(stroke[i])
    mmas.append(stroke)
filik.close()
mmas.remove(mmas[0])
for i in range(N):
    if used[i]==False:
        ne_put=[]
        ne_put.append(i)
        used[i]=True
        temp[i]=tempor
        tempor+=1
        while(ne_put.__len__()!=0):
            p=ne_put[0]
            ne_put.remove(p)
            for j in range(1, N):
                if mmas[p][j]==1 and used[j]==False:
                    used[j]=True
                    ne_put.append(j)
                    temp[j] = tempor
                    tempor += 1
filik.close()
filik2=open('output.txt','w')
for i in range(N):
    filik2.write(str(temp[i])+' ')
filik2.close()