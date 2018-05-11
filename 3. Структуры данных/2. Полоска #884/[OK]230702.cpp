#include <iostream>
#include <ostream>
#include <fstream>
using namespace std;

class CPolosa
{
public:
	CPolosa();
	virtual ~CPolosa();
	void LoadFromFile(char* FileName);
	void SaveToFile(char* FileName);
	void Solve();
private:
	void Cell(int a, int b, int k, int n);
private:
	int* Matrix;
	int Count;
};


CPolosa::CPolosa()
{

}

CPolosa::~CPolosa()
{
	delete[] Matrix;
}

void CPolosa::LoadFromFile(char* FileName)
{
	int k;
	ifstream fin(FileName);
	fin >> k;
	fin.close();
	Count = 1;
	for (int i = 0; i < k; i++)
		Count *= 2;
	Matrix = new int[Count];
}

void CPolosa::SaveToFile(char* FileName)
{
	ofstream fout(FileName);
	for (int i = 0; i < Count; i++)
	{
		if(i < Count -1)
		{
			fout << Matrix[i] << " ";		
		}else
		{
			fout << Matrix[i];
		}
	}

	fout.close();
}

void CPolosa::Cell(int a, int b, int k, int n)
{
	if (a == b)
	{
		Matrix[k] = a;
		return;
	}
	int d = a < b ? 1 : -1;
	Cell(a, (a + b - d) / 2, k, 2 * n);
	Cell(b, (a + b + d) / 2, n - k - 1, 2 * n);
}

void CPolosa::Solve()
{
	Cell(1, Count, 0, 2);
}

int main()
{
	CPolosa Pol;
	Pol.LoadFromFile("in.txt");
	Pol.Solve();
	Pol.SaveToFile("out.txt");
	return 0;
}