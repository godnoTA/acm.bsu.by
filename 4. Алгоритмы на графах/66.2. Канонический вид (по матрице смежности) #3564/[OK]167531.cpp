#include <iostream>
#include <fstream>
using namespace std;
int main()
{
	long N;
	int t =1;
	ifstream input("input.txt");
	ofstream output("output.txt");
	if(input == NULL)
		return 1;
	input >> N;
	if((N<1)||(N>100))
		return 1;
	int **P = new int*[N];
	int *K = new int [N];
	for (int i = 0; i<N;i++)
		P[i]=new int[N];
	for (int i = 0; i<N;i++)
		K[i]=0;
	for (int i = 0; i<N;i++)
	{
		for (int j = 0; j<N;j++)
			input >> P[i][j];
	}
	for (int i = 0; i<N;i++)
	{
		for (int j = 0; j<N;j++)
		{
			if (P[j][i]==1)
		K[i] = j+1;
		}
	}





	
	for(int i=0;i<N;i++)
		output << K[i]<< " ";
	input.close();
	output.close();
	return 0;

}