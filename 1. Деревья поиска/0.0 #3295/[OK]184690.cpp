#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main(){
	vector <long> massiv;
	ofstream fout("output.txt");
	ifstream fin("input.txt");
	long a;
	if (fin.is_open()){
		long long result = 0;
		bool b = true;
		while (fin >> a){
			for (int i = 0; i < massiv.capacity(); i++)
				if (a == massiv[i])
					b = false;
			if (b){
				result += a;
				massiv.push_back(a);
			}
			b = true;
		}
	    fout << result;
	}
	else{
		cout << "ppp";
		fin.close();
		fout.close();
	}
}
