#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");
int main()
{
	int n;
	in>>n;
	int mas[100001];
	for(int i=0;i<n;i++)
		in>>mas[i];
	if(n==1)
	{
		out<<1;
		exit(0);
	}
	vector<int>lastElem(n+1);
	int length=0;
	lastElem[0] = INT_MIN;
	for (int i=1; i<n+1; i++) 
		lastElem[i] = INT_MAX;
	for (int i=0;i<n;i++)
	{
       int j = int (upper_bound (lastElem.begin(),lastElem.end(),mas[i]) - lastElem.begin());
       if (lastElem[j - 1] < mas[i] && mas[i] < lastElem[j])
	   {
           lastElem[j] = mas[i];
		   length=max(j,length);
	   }
	}
	out<<length;
	in.close();
	out.close();
	return 0;
}