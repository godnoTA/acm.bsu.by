#include <iostream> 
#include <fstream> 
#include <vector> 
#include <queue>
#include <alg.h>
using namespace std;
queue<int> Queue;
int firstPosOfFalse = 0;
void Push(int i) {
	Queue.push(i);
}

bool FindFalse(bool* mas,int size) {
	for (int i = firstPosOfFalse; i < size;i++)
		if (mas[i] == false) {
			firstPosOfFalse = i;
			return true;
		}
	return false;
}

int main() {
	ifstream fin("input.txt");
	int n1;
	fin >> n1;
	vector<vector<int>> mas(n1);

	int I;
	vector<vector<int>>::iterator beg = mas.begin(), last;
	for (int i = 0; i < n1; i++)
		for (int j = 0; j < n1; j++) {
			fin >> I;
			I ? (beg + i)->push_back(j + 1) : 0;
		}
	fin.close();

	
	ofstream fout("output.txt");
	vector<int>::iterator beg1, last1;

	//	beg = mas.begin();
//	last = mas.end();
	//Queue.push(1);
	int temp;

	bool *sd = new bool[n1];
	for (int i = 0; i < n1; i++)	
		sd[i] = false;
	//fout << "1";
	int* ans = new int[n1];
	//ans[0] = 1;
	int count = 1;
	while (FindFalse(sd,n1)) {
		Queue.push(firstPosOfFalse+1);
		while (Queue.size() != 0) {
			temp = Queue.front();
			Queue.pop();
			if (sd[temp - 1] == true)
				continue;
			//if(temp!=1)fout << " "<<temp;
			ans[temp-1] = count++;
			sd[temp-1] = true;
			for_each((beg + temp - 1)->begin(), (beg + temp - 1)->end(), Push);
		}
	}
	fout << ans[0];
	for (int i = 1; i < n1; i++)
		fout << " " << ans[i];
	fout << "\n";
	delete[] sd;
	delete[] ans;
	fout.close();

	return 0;
}