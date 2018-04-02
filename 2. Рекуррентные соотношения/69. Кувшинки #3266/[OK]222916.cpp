
#include <fstream>
#include<iostream>
#include <string>
using namespace std;
int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt ");
	string st;
	int max;
	in >> st;
	max = atoi(st.c_str());
	int *mas = new int[max+1];
	for (int i = 1; i <= max; i++) {
		in >> st;
		mas[i] = atoi(st.c_str());
	}
	if (max == 1)
	{
		out << mas[1];
		return 0;
	}
	else if (max == 2)
	{
		out << -1;
		return 0;
	}
	else if (max == 3) {
		out << mas[1] + mas[3];
		return 0;
	}
	else if (max == 4) {
		out << mas[1] + mas[4];
		return 0;
	}
	else if (max == 5) {
		out << mas[1] + mas[3] + mas[5];
		return 0;
	}
	else {
		int *ma = new int[max+1];
		ma[1] = mas[1];
		ma[3] = mas[1] + mas[3];
		ma[4] = mas[1] + mas[4];
		ma[5] = mas[1] + mas[3] + mas[5];
		for (int i = 6; i <= max; i++) {
			int max1 = -1;
			if (ma[i - 2] > ma[i - 3])
				ma[i] = ma[i - 2] + mas[i];
			else
				ma[i] = ma[i - 3] + mas[i];
		}
		out << ma[max];
		return 0;
	}
}