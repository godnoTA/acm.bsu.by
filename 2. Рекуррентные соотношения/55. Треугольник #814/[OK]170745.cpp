#include <iostream>
#include <fstream>
#include <list> 
#include <iterator>
using namespace std;
int main()
{
	
	int k;
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	if(fin == NULL )
        return 1;
	fin >> k;
	if((k<1)||(k>1000))
		return 1;
	int **A = new int*[k];
	for(int i=0; i<k; i++)
		A[i]=new int[i+1];
	for(int i=0; i<k; i++)
		for(int j=0; j<i+1; j++)
			fin >> A[i][j];
	if(k==1)
	{
		fout << A[0][0];
		return 0;
	}
	for(int i=1; i<k; i++)
	{
		for(int j=0; j<i+1; j++)
		{
			int max=0;
			if(j-1>=0)
			{
				max=A[i-1][j-1];				
			}
			if(j!=i)
			{
				if(max<A[i-1][j])
					max=A[i-1][j];
			}
			A[i][j]+=max;
		}
	}
	int fmax=0;
	for(int i=0; i<k ;i++)
		if(fmax<A[k-1][i])
			fmax=A[k-1][i];
	
	fout << fmax;
	
	fin.close();
	fout.close();
    return 0;
}