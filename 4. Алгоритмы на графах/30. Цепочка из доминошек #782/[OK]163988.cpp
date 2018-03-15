#include <iostream>
#include <fstream>

using namespace std;
int mas[7][7];
bool used[7];
bool visited[7];

void go(int num) {
	visited[num] = true;
	for (int i = 0; i < 7; i++)
		if (mas[num][i] && !visited[i])
			go(i);
}

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	if (fin.is_open())
	{
		int N;
		fin >> N;
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				mas[i][j] = 0;

		for (int i = 0; i < 7; i++) {
			visited[i] = false;
			used[i] = false;
		}

		int k = 0, l = 0;
		for (int i = 0; i < N; i++)
		{
			fin >> k;
			fin >> l;
			used[l] = used[k] = true;
			mas[k][l] = mas[l][k] += 1;
		}

		bool isEuler = true;

		for (int i = 0; i < 7; i++) {
			if (used[i]) {
				go(i);
				break;
			}
		}

		for (int i = 0; i < 7; i++) {
			if (!used[i])
				continue;
			if (!visited[i]) {
				isEuler = false;
				break;
			}
		}

		if (isEuler) {
			for (int i = 0; i < 7; i++)
			{
				int sum = 0;
				for (int j = 0; j < 7; j++)
					if (i == j)
						sum += 2 * mas[i][j];
					else
						sum += mas[i][j];
				if (sum % 2 != 0)
				{
					isEuler = false;
					break;
				}
			}
		}

		if (isEuler)
			fout << "Yes";
		else
			fout << "No";
	}
	fin.close();
	fout.close();
}