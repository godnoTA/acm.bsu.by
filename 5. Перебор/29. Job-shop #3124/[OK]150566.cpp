#include <fstream>
#include <utility>

using namespace std;
ofstream out("output.txt");
int record;

pair<int, int>* dec(pair<int, int> p[], int i, int s)
{
	pair<int, int> *q=new pair<int, int>[s];
	for(int j=0; j<s; j++)
		q[j]=p[j];
	q[i].second--;
	return q;
}

void perebor(pair<int, int> a[], pair<int, int> b[], int time, int i1, int i2, int s1, int s2, int t1, int t2, int r)
{
	if((time!=0)&&(r<record)&&(max(t1, t2)<=time))
	{
		if(a[i1].second==0)
			i1++;

		if(b[i2].second==0)
			i2++;

		if((s1>i1)&&(s2>i2))
		{
			if(a[i1].first!=b[i2].first)
			{
				perebor(dec(a, i1, s1), dec(b, i2, s2), time-2, i1, i2, s1, s2, t1-1, t2-1, r+1);
			}

			else
			{
				perebor(dec(a, i1, s1), b, time-1, i1, i2, s1, s2, t1-1, t2, r+1);
				perebor(a, dec(b, i2, s2), time-1, i1, i2, s1, s2, t1, t2-1, r+1);
			}
		}
		else
		{
			r+=time;
			time=0;
			if(r<record)
				record=r;
		}
	}
	else
		if((r<record)&&(max(t1, t2)<=time))
			record=r;
}

int main()
{
	int n, s1, s2;
	int t=0, t1=0, t2=0;

	ifstream in;
	in.open("input.txt");
	in>>n;
	in>>s1;
	pair<int, int> *job1=new pair<int, int>[s1];

	for(int i=0; i<s1; i++)
	{
		in>>job1[i].first;
		in>>job1[i].second;
		t+=job1[i].second;
	}
	t1=t;

	in>>s2;
	pair<int, int> *job2=new pair<int, int>[s2];

	for(int i=0; i<s2; i++)
	{
		in>>job2[i].first;
		in>>job2[i].second;
		t+=job2[i].second;
	}

	in.close();
	t2=t-t1;

	record=t;
	perebor(job1, job2, t, 0, 0, s1, s2, t1, t2, 0);

	out<<record<<endl;

	return 0;
}