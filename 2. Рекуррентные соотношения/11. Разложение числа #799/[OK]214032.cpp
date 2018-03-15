#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

struct Pair
{
	long long vel;
	int count;
};

int main()
{
	long long a, b, c, b1, a1;
	ifstream f("input.txt");
	f >> a;
	f >> b;
	f >> c;
	f.close();
	if (a >= b && a<=c)
	{
		ofstream of("output.txt");
			of << 1;
		of.close();
		return 0;
	}
	vector<long long> delitel;
	if (b == 1)
		b1 = 2;
	else
		b1 = b;
	for (long long i = b; i <= a / b1 && i <= c; i++)
	{
		if (a%i == 0)
			if(a/i>=b)
			delitel.push_back(i);
	}
	int i = 2;
	a1 = a;
	while (a1!=1)
	{
		if (a1%i == 0)
			a1 /= i;
		else
			i++;
		if (i > c)
		{
			ofstream of("output.txt");
			of << -1;
			of.close();
			return 0;
		}
	}
	if (delitel.size()==0)
	{
		ofstream of("output.txt");
		of << -1;
		of.close();
		return 0;
	}
	//cout << a<<"  "<<b<<"  "<<c<<" ";

    vector<long long>::iterator it, it2;
	/*for (it = delitel.begin(); it != delitel.end(); ++it)
		{
			cout << *it<<" ";
		}
		*/
	
	vector<Pair> p;
	int min = 0;
	vector<Pair>::iterator pit;
	Pair pair;
	long long k, k2;
	int coun, j;
	

	reverse(delitel.begin(), delitel.end());



	for (it = delitel.begin(); it != delitel.end(); it++)
	{
		pair.vel = *it;
		pair.count = 1;
		p.push_back(pair);
		j = p.size();
		for (int i=0; i<j;i++){
			k = p[i].vel;
			coun=p[i].count;
			k2 = (*it)*k;
			while (k2<=a)
			{
				pair.count = coun + 1;
				pair.vel = k2;
				if (k2 == a)
				{
					if (min == 0)
						min = pair.count;
					else
						if (pair.count < min)
							min = pair.count;
				}
				else
				p.push_back(pair);
				k2 = (*it)*k2;
				coun++;
			}
		}
		if (min != 0)
		{
			ofstream of("output.txt");
			of << min;
			of.close();
			return 0;
		}
	}
	/*for (pit = p.begin(); pit != p.end(); pit++)
	{
		cout << pit->vel << "  "<< pit->count<< endl;
	}*/
        ofstream of("output.txt");
	if (min != 0)
	{
		of << min;
	}
	else
	{
		of << -1;
	}
		of.close();
		return 0;
}