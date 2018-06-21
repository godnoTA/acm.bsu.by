#include <iostream> 
#include <fstream> 
#include <string> 
#include <set>

using namespace std;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n, m;
	in >> n;
	n++;
	in >> m;
	set<pair<int, int>>mySet;
	/*int**matr = new int*[m];
	for (int i = 0; i < m; i++)
		matr[i] = new int[2];*/
	string str1="Yes", str2="Yes", str3="Yes";
	int a = 0, b = 0, k=0;
	for (int i = 0; i < m; i++){
		in >> a;
		in >> b;
		if (a == b) {
			str1 = "No";
			str2 = "No";
			out << str1 << endl;
			out << str2 << endl;
			out << str3 << endl;
			return 0;
		}
		k = mySet.size();
		mySet.insert(make_pair(a, b));
		mySet.insert(make_pair(b, a));
		if (k==mySet.size()){
			str1 = "No";
		}
	}
	/*for (int i = 0; i < m; i++){
		for (int j = 0; j < 2; j++)
			in>>matr[i][j];
	}

	for (int i = 0; i<m; i++) {
		if (matr[i][0] == matr[i][1]) {
			str1 = "No";
			str2 = "No";
			out << str1 << endl;
			out << str2 << endl;
			out << str3 << endl;
			return 0;
		}
		if (str1 == "Yes")
		for (int j = i + 1; j<m; j++) {
			if (((matr[j][0] == matr[i][0] && matr[j][1] == matr[i][1])) || (matr[j][0] == matr[i][1] && matr[j][1] == matr[i][0])) {
				str1 = "No";
				//out << str1 << endl;
				//out << str2 << endl;
				//out << str3 << endl;
				//return 0;
			}
		}
		
	}*/
		out << str1 << endl;
		out << str2 << endl;
		out << str3 << endl;
	/*for (int i = 0; i < m; i++)
		delete[]matr[i];
	delete[]matr;*/
	return 0;
}