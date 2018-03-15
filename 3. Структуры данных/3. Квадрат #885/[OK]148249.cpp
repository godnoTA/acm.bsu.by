#include <fstream>
#include <utility>

using namespace std;
ofstream out("out.txt");

int main()
{
	ifstream in;
	in.open("in.txt");

	int k;
	in>>k;
	in.close();

	int n=pow(4.0, k);
	int s=pow(2.0, k);

	pair<int, int> **m=new pair<int, int>*[s];
	for(int i=0; i<s; i++)
		m[i]=new pair<int, int>[s];

	m[0][0].first=1;
	m[0][0].second=n;

	for(int i=0; i<k; i++)
	{
		n/=2;
		int g=pow(2.0, i);
		for(int j=0, f=2*g-1; j<g; j++, f--)
		{
			for(int h=0; h<g; h++)
			{
				if((h+j)%2==0)
				{
					m[f][h].second=m[j][h].second;
					m[j][h].second-=n;
					m[f][h].first=m[j][h].second;
				}
				else
				{
					m[f][h].first=m[j][h].first;
					m[j][h].first+=n;
					m[f][h].second=m[j][h].first;
				}
			}
		}
		n/=2;
		for(int h=0, e=2*g-1; h<g; h++, e--)
		{
			for(int j=0; j<2*g; j++)
			{
				if((h+j)%2==0)
				{
					m[j][e].second=m[j][h].second;
					m[j][h].second-=n;
					m[j][e].first=m[j][h].second;
				}
				else
				{
					m[j][e].first=m[j][h].first;
					m[j][h].first+=n;
					m[j][e].second=m[j][h].first;
				}
			}
		}
	}

	for(int i=0; i<s; i++)
		for(int j=0; j<s; j++)
		{
			out<<m[i][j].second;
			if(i+j!=2*s-2)
				out<<" ";
		}
	return 0;
}