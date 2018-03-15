#include <fstream>
#include <queue>

using namespace std;



int main()
{
	int N;
	ifstream in("input.txt");
	in >> N;
	int*H = new int[N + 1];

	for (int i = 1; i <= N; i++)
		H[i] = 0;

	int v = -1, w = -1;
	for (int i = 1; i < N; i++)
	{
		in >> v >> w;
		H[w] = v;
	}

	ofstream out("output.txt");

	for (int i = 1; i <= N; i++)
		out << H[i] << " ";
	out.close();
	return 0;
}