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

	int** adj_matrix = new int*[N + 1];
	for (int i = 1; i <= N; ++i)
		adj_matrix[i] = new int[N + 1];

	for (int i = 1; i <= N; ++i)
		for (int j = 1; j <= N; j++)
			in >> adj_matrix[i][j];

	for (int i = 1; i <= N; ++i)
		for (int j = 1; j <= N; j++)
			if (adj_matrix[i][j])
				H[j] = i;

	ofstream out("output.txt");

	for (int i = 1; i <= N; i++)
		out << H[i] << " ";
	out.close();
	return 0;
}