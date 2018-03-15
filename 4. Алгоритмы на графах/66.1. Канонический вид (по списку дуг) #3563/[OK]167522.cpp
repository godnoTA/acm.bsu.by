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
	if((N<1)||(N>100000))
		return 1;
	int *P = new int[N];
	for (int i = 0; i<N;i++)
		P[i]=0;
	while((!input.eof())&&(t<=N-1))
	{
		int u,v;
		input >> u >> v;
		P[v-1]=u;
		t++;
	}
	for(int i=0;i<N;i++)
		output << P[i]<< " ";
	input.close();
	output.close();
	return 0;

}