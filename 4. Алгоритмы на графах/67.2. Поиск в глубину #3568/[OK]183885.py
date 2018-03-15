class DFS:
    def _init_(self):
        self.N = 0
        self.tempor = 1
        self.mmas = []
        self.used = []
        self.temp = []

    def In(self):
        filik = open('input.txt', 'r')
        self.N = int(filik.readline())
        self.used = [False for i in range(self.N)]
        self.temp = [0 for i in range(self.N)]
        self.tempor = 1
        self.used = [False for i in range(self.N)]
        self.temp = [0 for i in range(self.N)]
        self.mmas=[]
        for line in filik:
            stroke = line.split()
            for i in range(self.N):
                stroke[i] = int(stroke[i])
            self.mmas.append(stroke)
        filik.close()
        #self.mmas.remove(self.mmas[0])
        filik.close()

    def poisk(self, i2):
        self.used[i2] = True
        self.temp[i2] = self.tempor
        self.tempor += 1
        for j in range(self.N):
            if self.mmas[i2][j] == 1 and self.used[j] == False:
                self.poisk(j)

    def global_poisk(self):
        for i in range(self.N):
            if self.used[i] == False:
                self.poisk(i)
        filik2 = open('output.txt', 'w')
        for i in range(self.N):
            filik2.write(str(self.temp[i]) + ' ')
        filik2.close()

a=DFS()
a.In()
a.global_poisk()