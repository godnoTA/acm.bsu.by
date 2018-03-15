#include <iostream>
#include <fstream>
#include <list>
#include <iterator>
#include <cmath>
using namespace std;
void dobavka(int *Mas, int n, int s, int x)
{	
	int popitka=0;
	while(true)
	{	
		int a =((x%n)+s*popitka)%n;
		if(Mas[a]==-1)
		{
			Mas[a]=x;
			break;
		}
		else if(Mas[a]==x)
			break;
		else
			popitka++;
	}
}
int main()
{
	int fo,co,N;
	ifstream input("input.txt");
	ofstream output("output.txt");
	if(input == NULL )
        return 1;
	input >> fo >> co >> N ;
	if((fo<2)||(fo>10000)||(co<1)||(co>fo-1)||(N<0)||(N>10000))
		return 1;
	int *Mas = new int[fo];
	for(int i=0; i<fo ; i++)
		Mas[i]=-1;
	if(N!=0)
	{
		while(!input.eof())
		{
			int num;
			input >> num;
			dobavka(Mas,fo,co,num);
		}
	}
	for(int i=0; i<fo; i++)
		output << Mas[i] << " ";
	return 0;
}