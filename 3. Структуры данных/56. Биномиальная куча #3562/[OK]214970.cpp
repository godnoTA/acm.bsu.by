#include <iostream>
#include <fstream>
#include <vector>

using namespace std;


int main() {

	long long int n = 0;

	ifstream fi("input.txt");
	fi >> n;


	vector<bool> dvoichno;
	
	while (n != 1)
	{
		dvoichno.push_back(n % 2);
		n /= 2;
	}
	dvoichno.push_back(1);
	ofstream fo("output.txt");

	
	for (int i = 0; i < dvoichno.size(); ++i)
	{
		if (dvoichno[i] == 1) fo << i << endl;
	}
	


	system("pause");
	return 0;
}