#include<iostream>
#include<fstream>
using namespace std;

int main() {
	
	
ifstream in ("input.txt");
ofstream out("output.txt");
	

//num - num of pairs+beg+end
char* str = new char[80];
int num;

in >> num;
num++;
int*sizes = new int[num];

in >> sizes[0];
in >> sizes[1];

for(int i=2;i<num;++i){
in >> sizes[i];
in >> sizes[i];
 
}

int** best = new int*[num];
for (int i = 0; i < num; ++i) {
best[i] = new int[num];
if (i + 2 < num) {
	best[i][i + 2] = sizes[i] * sizes[i + 1] * sizes[i + 2];
}
}

for (int s = 3; s < num; ++s) {
for (int i = 0; i < num - s; ++i) {
	
int min = best[i + 1][i + s] + sizes[i] * sizes[i + 1] * sizes[i + s];

if (min > best[i][i + s - 1] + sizes[i] * sizes[i + s - 1] * sizes[i + s]) {
	min = best[i][i + s - 1] + sizes[i] * sizes[i + s - 1] * sizes[i + s];
}

for (int k = i + 2; k <= i + s - 2; ++k) {
if (min > (best[i][k] + best[k][i + s] + sizes[i] * sizes[i + s] * sizes[k])) {
min = (best[i][k] + best[k][i + s] + sizes[i] * sizes[i + s] * sizes[k]);
}
}
best[i][i+s] = min;
}
}

out << best[0][num - 1];

	
	return 0;
}
