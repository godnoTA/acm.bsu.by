#include <iostream>
#include <fstream>
#include <vector>
#include <list>

using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);

class Item
{
	public:
		int  count= 0;
		vector <int> neighbour;
};
int main()
{
	int N, M, a, b;
	inStream >> N >> M;
	vector <Item> list(N);
	for (int i = 0;i<M;i++) 
	{
		inStream >> a >> b;
		list[a - 1].count++;
		list[a - 1].neighbour.push_back(b);
		list[b - 1].count++;
		list[b - 1].neighbour.push_back(a);
	}
	inStream.close();
	for (int i = 0;i<N;i++) 
	{
		outStream << list[i].count << " ";
		for (int j = 0; j<list[i].neighbour.size(); j++) 
		{
			outStream << list[i].neighbour[j] << " ";
		}
		outStream << "\n";
	}
	outStream << "\n";
	outStream.close();
	return 0;
}


