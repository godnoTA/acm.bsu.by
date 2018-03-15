#include <iostream>
#include <fstream>
#include <list>
#include <iterator>
#include <cmath>
using namespace std;
list <long> degrees;
int deg(long long n)

{	
	while(n>0)
	{
		bool not=true;
		long long del=1;
		long degree=0;
		while(true)
		{
			if(n-del>=0)
			{
				if(n-del==0)
				{
					degrees.push_front(degree);
					return -1;
				}
				del*=2;
				degree++;
			}
			else if(n==1)				
				return 0;
			else if(n==2)
				return 1;
			else
			{
				degree--;
				degrees.push_front(degree);
				n-=(del/2);
				break;
			}
		}
	}
}
int main()
{
	long long n;
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	if(fin == NULL )
        return 1;
	fin >> n ;
	if((n<1)||(n>pow(10.0,18)))
	{
		fout << -1;
		return 0;
	}
	int ii=deg(n);
	if((ii==1)||(ii==0))
		degrees.push_front(ii);
	degrees.unique();
	for(list<long>::iterator it1=degrees.begin(); it1!=degrees.end(); it1++)
		fout << *it1 << "\n";
    fin.close();
	fout.close();
    return 0;
}
