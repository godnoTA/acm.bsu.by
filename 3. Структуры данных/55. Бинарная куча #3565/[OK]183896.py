filik=open('input.txt','r')
m=filik.readline()
m=int(m)
mas=filik.readline().split()
for i in range(m):
    mas[i]=int(mas[i])
filik.close()
filik2=open('output.txt','w')
flag=True
for i in range(int(m/2)):
    if 2 * i + 1 > m-1:
        break
    if 2 * i + 2 > m-1:
        if mas[2*i+1] >= mas[i]:
            continue
        else:
            flag=False
            break
    if mas[2 * i + 1] >= mas[i] and mas[2 * i + 2] >= mas[i]:
        continue
    else:
        flag=False
        break
if (flag):
    filik2.write('Yes')
else:
    filik2.write('No')
filik2.close()
