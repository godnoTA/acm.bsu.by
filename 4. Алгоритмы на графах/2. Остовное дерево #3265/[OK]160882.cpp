#include <iostream>
#include <fstream>
#include <queue>
using namespace std;

int main()
{
	fstream inFile("input.txt",ios::in);
	fstream outFile("output.txt",ios::out);

	int **Mass;
	int size;
	int count = 0;
	int *Mass2;          //массив для отметок были ли мы в этой вершине;
	queue<int> rebra;    //вектор хранящий все ребёра 
	queue<int> v;        //вектор хранящий вершины

	inFile>>size;
	size++;

	if (size == 1)
		{
			outFile<<1<<endl<<1<<" "<<1;
			return 0;

	}

	Mass2= new int[size];
	for(int i = 1; i < size; i++)
	Mass2[i] = 0;

	Mass= new int*[size];
	for(int i = 0; i < size; i++)
		Mass[i] = new int[size];


	for( int i = 1 ; i < size ; i++)
		for( int j = 1; j < size ; j++)
			inFile>>Mass[i][j];

	v.push(1);
	int ver;
	
	Mass2[1]=1;

	for(  ; v.size() != 0 ; )
	{
		ver=v.front();
		v.pop();

			for( int j = 1; j < size ; j++)
			{
				if(Mass2[j]==0 && Mass[ver][j] == 1)
				{
					Mass2[j] = 1;
					v.push(j);
					rebra.push(ver);
					rebra.push(j);
					count++;
				}
			}
	}
		
	if(count == size - 2)
	{
		outFile<<count<<endl;
	for( ; rebra.size() != 0;)
	{
		outFile<<rebra.front()<<" ";
		rebra.pop();
		outFile<<rebra.front()<<endl;
		rebra.pop();
	}
	}
	else outFile<<-1;
	
	return 0;
	
}