#include <iostream>
#include <fstream>
#include <cstdlib>
#include <cstddef>
#include <cmath>
#include <stack>
#include<set>

using namespace std;
int size1;
	int size2;
	set<pair<int, int>> s;
class myNode {
     public: 
		int a;
		int b;
		int c;

		myNode(int _a, int _b, int _c) {
			a = _a;
			b = _b;
			c = _c;
		}
		~myNode() {
		    
		}
};

bool solve(int* a, int* b, int* c) {
	stack<myNode> st;
	st.push(myNode(0, 0, 0));
	while (!st.empty()) {
		myNode mn = st.top();
		st.pop();
		int i = mn.a;
		if (i >= size1+size2) {
			return true;
		} 
		int j = mn.b;
		int k = mn.c;
		
		if (j < size1 && c[i] == a[j]) {
			if (s.count(make_pair(j + 1, k)) == 0) {
				st.push(myNode(i + 1, j + 1, k));
				s.insert(make_pair(j + 1, k));
			}
		}
		if (k < size2 && c[i] == b[k]) {
			if (s.count(make_pair(j, k + 1)) == 0) {
				st.push(myNode(i + 1, j, k + 1));
				s.insert(make_pair(j, k + 1));
			}
		}
	}
	return false;
}


int main()
{
	//ifstream fin;//Объявляем поток чтения из файла
	//ofstream fout;//Объявляем поток записи в файл
	FILE *filei = fopen("input.txt", "r");
	FILE *fileo = fopen("output.txt", "w");
	//fin.open("input.txt");
	//fout.open("output.txt");
	
	fscanf(filei,"%d", &size1);
	fscanf(filei, "%d", &size2);
	//-----------------------------------------------------------------
	int* a = new int[size1];
	int* b = new int[size2];
	int* c = new int[size2+size1];
	int wD;
	for (int i = 0; i < size1; ++i) {
		fscanf(filei, "%d", &wD);
		a[i] = wD;
	}
	for (int i = 0; i < size2; ++i) {
		fscanf(filei, "%d", &wD);
		b[i] = wD;
	}
	for (int i = 0; i < size1+size2; ++i) {
		fscanf(filei, "%d", &wD);
		c[i] = wD;
	}
	if (solve(a,b,c))
       fprintf(fileo, "possible");
	else
		fprintf(fileo, "not possible");
	//-----------------------------------------------------------------
    return 0;
}

