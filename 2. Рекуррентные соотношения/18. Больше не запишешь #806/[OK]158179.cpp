#include <iostream>
#include <fstream>
#include <math.h>
using namespace std;

int main()
{
	int K, N;//к-количество букв N-длинна слова
	fstream input ("input.txt", ios::in);
	fstream output ("output.txt", ios::out);
	input >> K;
	input >> N;
	N++;
	int **Mass= new int*[N];	//создали массив для хранения коэф где номер строки это длинна слова а столбцы количество слэшей в данном
	for(int i=0; i<N; i++)      //слове. А само число это количество возможных способов поставить столько слэшей
		Mass[i]=new int[N];

	for(int i=0; i<N;i++)      //инициализация нулями 
		for(int j=0; j<N;j++)
			Mass[i][j]=0;

	for(int i=1;i<N;i++)    //заполняем единичками там где 0 скобок
	{
		Mass[i][0]=1;
	}
	
	int X=1;

	for(int i=3;i<N;i++,X++) //заполняем сразу для удобства столбик где колво скобок 1
	{
		Mass[i][1]=X;
	}

	for(int i=2;i<N;i++)      //заполнили всю таблицу
		for(int j=5;j<N;j++)
	{
		Mass[j][i]=Mass[j-2][i-1]+Mass[j-1][i];
	}

	int *MassRez=new int[N];
	for(int i=1; i<N;i++)
	{
		MassRez[i]=0;
	}

	for(int i=1; i<N;i++)
			for(int k=i,j=0;k<N;k++,j++)
	{
		MassRez[i]+=Mass[k][j];
		
	}
	int pw=1;
	int REZULT=0;
	for(int i=1;i<N;i++)
	{
		for(int j=0;j<i;j++)
		{
			pw*=K;
		}
		REZULT+=pw*MassRez[i];
		pw=1;
	}
	output<<REZULT;
	
	return 0;
}
