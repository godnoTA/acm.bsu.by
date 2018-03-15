#include <fstream>
#include <vector>
#include <algorithm>

int main()
{
	std::ifstream fin("input.txt");
	std::ofstream fout("output.txt");
	
	long n = 0;
	long long sum = 0;
	std::vector<long> listBT;

	while (!fin.eof()) { // считали значения из файла и занесли их в список
		fin >> n;
		listBT.push_back(n);
	}

	n = 0;

	std::sort(listBT.rbegin(), listBT.rend());

	long size = listBT.size(); // узнали размер обработанного листа

	for (long i = 0; i < size; i++)
	{
		if(n != listBT[i])
		{
			n = listBT[i];
			sum += n;
		}
		
	}// посчитали сумму
		

	fout << sum;

	fin.close();
	fout.close();

	return 0;
}
