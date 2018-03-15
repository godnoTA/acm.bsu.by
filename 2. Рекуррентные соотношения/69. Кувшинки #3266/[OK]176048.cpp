#include <iostream>
#include <fstream>
#include <list> 
#include <iterator>
#include <algorithm>
using namespace std;
struct hop
{
	int cord;
	int sum;
	hop(int cordy, int sumy)
	{
		cord=cordy;
		sum=sumy;
	}
};
list <hop> listy;
list <hop> listy2;
bool compare_nocase (const hop& first, const hop& second)
{
	if(first.cord>second.cord)
		return true;
	else if(first.cord==second.cord)
	{
		if(first.sum>second.sum)
			return true;
		else
			return false;
	}
	else
		return false;
}
bool pred (hop &first, hop &second)
{
	return (first.cord==second.cord);
}
int main()
{
	
	int N;
	int max=-1;
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	if(fin == NULL )
        return 1;
	fin >> N;
	if((N<1)||(N>1000))
		return 1;
	int *mas = new int[N];
	for(int i=0; i<N; i++)
		fin >>mas[i];
	hop ny(0,mas[0]);
	listy.push_back(ny);	
	int size=listy.size();
	while(size!=0)
	{
		list<hop>::iterator ite = listy.end();
		for(list<hop>::iterator itb=listy.begin(); itb!=ite; itb++)
		{
			if((*itb).cord+2<=N-1)
			{
				hop nuu((*itb).cord+2,(*itb).sum+mas[(*itb).cord+2]);
				listy2.push_back(nuu);	
			}
			if((*itb).cord+3<=N-1)
			{
				hop nuu((*itb).cord+3,(*itb).sum+mas[(*itb).cord+3]);
				listy2.push_back(nuu);	
			}
			if((*itb).cord==N-1)
			{
				if(max < (*itb).sum)
					max=(*itb).sum;
			}			
		}
		listy.clear();
		listy2.sort(compare_nocase);
		
		listy2.unique(pred);
		listy2.swap(listy);
		listy2.clear();
		size=listy.size();
	}
	fout << max;	
	delete [] mas;
    fin.close();
	fout.close();
    return 0;
}