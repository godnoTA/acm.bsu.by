#include <iostream>
#include <fstream>
#include <iterator>
#include <cmath>
using namespace std;
int peremn(int *p, int N)
{
    int **m = new int*[N];
    for (int i = 0; i < N; i++)
        m[i] = new int [N];
    for (int i = 0; i < N; i++)
        m[i][i] = 0;
    for (int L = 2; L <= N; L++)   
    {
        for (int i = 0; i < N - L + 1; i++)
        {
            int j = i + L - 1;
            m[i][j] = INT_MAX;
            for (int k = i; k <= j - 1; k++)
            {
                int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                if (q < m[i][j])
                    m[i][j] = q;
            }
        }
    }
    return  m[0][N - 1];
}
int main()
{
	int N;
	ifstream input("input.txt");
	ofstream output("output.txt");
	if(input == NULL )
        return 1;
	input>> N ;
	if((N<2)||(N>100))
		return 1;
	int *mas = new int[N+1];
	int count=0;
	while(!input.eof())
	{		
		int space;
		input >> mas[count];  
		input >> space;
		if(count == N-1)
		{
			mas[N]=space;
			break;
		}
		count++;
	}
	output << peremn(mas,N);	
	return 0;

}
