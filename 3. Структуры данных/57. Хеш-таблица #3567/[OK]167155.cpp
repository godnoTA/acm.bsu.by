#include <iostream>
#include <fstream>

using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);

int main()
{
	int M,N, c;
	inStream >> M;
	inStream >> c;
	inStream >> N;
	int *hashTable = new int[M];
	for (int i = 0; i < M; i++)
	{
		hashTable[i] = -1;
	}

	for (int i = 0;i < N;i++)
	{
		int key;
		int count = 0, j;
		inStream >> key;
		j = ((key %M) + c* count) % M;
		count++;
		if (hashTable[j] == -1)
		{
			hashTable[j] = key;
		}
		else
		{
			if (hashTable[j] == key)
			{
				continue;
			}
			else
			{
				do
				{
					j = ((key %M) + c* count) % M;
					count++;
				} while ((hashTable[j] != -1) && (hashTable[j] != key));
				if ((j<M)&&(hashTable[j] == -1))
				{
					hashTable[j] = key;
				}
			}
		}
	}
	inStream.close();

	for (int i = 0; i < M; i++)
	{
		outStream << hashTable[i] << " ";
	}
	outStream << "\n";
	outStream.close();
	return 0;
}
