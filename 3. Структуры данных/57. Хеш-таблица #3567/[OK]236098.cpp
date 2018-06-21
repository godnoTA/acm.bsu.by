#include <iostream> 
#include <fstream> 
#include <vector> 

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int const maxi = INT_MAX;
int HashFunc(int n, int m, int c, int i)
{
	return ((n % m) + c * i) % m;
}

int main() 
{
	

	int m=0 , n=0, c=0;
	fin >> m >> c >> n;	

	vector<int> vect_res(m, maxi);
	vector<int> vect;
	bool flag = false;

	while (fin>>n) 
	{
		flag = false;
		for (unsigned int i = 0; i < vect.size(); i++)
			if (vect[i] == n) {
				flag = true;
				break;
			}
		if (flag == false)
			vect.push_back(n);
	}
	fin.close();
	for (unsigned int i = 0; i < m; i++) {
		vect_res[i] = maxi;
	}

	for (int i = 0; i < vect.size(); ++i) {
		int pos = 0;
		int position = HashFunc(vect[i], m, c, pos++);

		while (vect_res[position] != maxi) {
			position = HashFunc(vect[i], m, c, pos++);
		}
		vect_res[position] = vect[i];
	}

	for (unsigned int i = 0; i < m; i++)
	{
		if (vect_res[i] == maxi) 
		{
			fout << "-1"<<" ";
			cout << "-1 "<<" ";
		}
		else
		{
			fout << vect_res[i] << " ";
			cout << vect_res[i] << " ";
		}
	}
	cout << "\n";
	fout << "\n";
	fout.close();
	return 0;
}


