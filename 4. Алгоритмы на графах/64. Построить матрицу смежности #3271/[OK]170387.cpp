#include "iostream"
#include "fstream"
using namespace std;
 
int main()
{
	int N, M;
	ifstream input("input.txt");
	ofstream output("output.txt");
	if(input == NULL )
        return 1;
	input >> N >> M;
	if((N<1)||(N>100)||(M<0)||(M>N*(N-1)/2))
		return 1;
	int **A = new int*[N];
	for(int i=0; i<N; i++)
		A[i]=new int[N];
	for(int i=0;i<N;i++)
        for(int j=0;j<N;j++)
            A[i][j]=0;
	if(M!=0)
	{
	while(!input.eof())
	{
		int x,y;
		input >> x >> y;  
		if((x==y)||(x<1)||(x>N)||(y<1)||(y>N))
			return 1;
		A[x-1][y-1]=1;
        A[y-1][x-1]=1;
    }
	}
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            output << A[i][j] << " ";
        }
        output << "\n";
    }	
    input.close();
	output.close();
    return 0;
}
