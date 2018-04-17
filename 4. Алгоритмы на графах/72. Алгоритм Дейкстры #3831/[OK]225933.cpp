#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <list>
#include <conio.h>
#include <time.h>

using namespace std;

class duoheap
{
	int num[200005];
	long long int mas[200005];
	bool isex[200005];
	int whex[200005];
	int size;
public:
	duoheap()
	{ 
		size = 0; 
		for (int i = 0; i < 200005; i++) isex[i] = false; 
	}
	void add(int a, long long int b)
	{
		if (!isex[a])
		{
			isex[a] = true;
			mas[size] = b;
			num[size] = a;
			whex[a] = size;
			int h = size;
			size++;
			if (h != 0)
				while (mas[h / 2 - (h + 1) % 2] > mas[h])
				{
					swap(whex[num[h / 2 - (h + 1) % 2]], whex[num[h]]);
					swap(mas[h / 2 - (h + 1) % 2], mas[h]);
					swap(num[h / 2 - (h + 1) % 2], num[h]);
					h = h / 2 - (h + 1) % 2;
					if (h == 0) break;
				}
		}
		else
		{
			if (mas[whex[a]] > b)
			{
				mas[whex[a]] = b;
				int h = whex[a];
				if (h != 0)
					while (mas[h / 2 - (h + 1) % 2] > mas[h])
					{
						swap(whex[num[h / 2 - (h + 1) % 2]], whex[num[h]]);
						swap(mas[h / 2 - (h + 1) % 2], mas[h]);
						swap(num[h / 2 - (h + 1) % 2], num[h]);
						h = h / 2 - (h + 1) % 2;
						if (h == 0) break;
					}
			}
		}
	}
	long long int min_mas() { return mas[0]; }
	int min_num() { return num[0]; }
	void bal(int sq)
	{
		int left = 2 * sq + 1;
		int right = 2 * sq + 2;
		bool fl = true, fr = true;
		if (left < size)
			if (mas[left] < mas[sq])
				fl = false;
		if (right < size)
			if (mas[right] < mas[sq])
				fr = false;
		if (!fr && !fl)
		{
			if (mas[left] < mas[right])
			{
				swap(whex[num[sq]], whex[num[left]]);
				swap(mas[sq], mas[left]); 
				swap(num[sq], num[left]); 
				bal(left); 
			}
			else 
			{
				swap(whex[num[sq]], whex[num[right]]); 
				swap(mas[sq], mas[right]); 
				swap(num[sq], num[right]);  
				bal(right); 
			}
			return;
		}
		if (!fr)
		{
			swap(whex[num[sq]], whex[num[right]]);
			swap(num[sq], num[right]);
			swap(mas[sq], mas[right]);
			bal(right);
			return;
		}
		if (!fl)
		{
			swap(whex[num[sq]], whex[num[left]]);
			swap(num[sq], num[left]);
			swap(mas[sq], mas[left]);
			bal(left);
			return;
		}
	}
	void delmin()
	{
		if (size == 0) { cout << "Error! Empty heap!" << endl; return; }
		isex[num[0]] = false;
		mas[0] = mas[size - 1];
		num[0] = num[size - 1];
		whex[num[0]] = 0;
		size--;
		bal(0);
	}
	bool isempty()
	{
		if (size == 0) return true;
		else return false;
	}

	int get_size()
	{
		return size;
	}

	void out()
	{
		for (int i = 0; i < size; i++)
		{
			cout << "num = " << num[i] << ", mas = " << mas[i] << endl;
		}
	}
};

/*struct obj
{
	obj() {}
	obj(int a, long long int b) { num = a; dlin = b; }
	int num;
	long long int dlin;
};

bool comp(obj &a, obj &b)
{
	return (a.dlin < b.dlin);
}*/

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	duoheap *heap = new duoheap();
	int n, m, a, b;
	long long int c;
	bool metk[200005];
	f1 >> n >> m;
	vector <long long int> dlin(n + 1);
	for (int i = 0; i<n + 1; i++)
	{
		metk[i] = false;
		dlin[i] = -1;
	}
	vector <list<pair<int, long long int> > >mas(n + 1, list<pair<int, long long int> >(0));
	for (int i = 0; i<m; i++)
	{
		f1 >> a >> b >> c;
		mas[a].push_back(make_pair(b, c));
		mas[b].push_back(make_pair(a, c));
	}
	dlin[1] = 0;
	metk[1] = true;
	heap->add(1, 0);
	while (!heap->isempty() && !metk[n])
	{
		int ver = heap->min_num();
		heap->delmin();
		metk[ver] = true;
		for (list<pair<int, long long int> >::iterator i = mas[ver].begin(); i != mas[ver].end(); i++)
		{
			if (!metk[i->first])
			{
				if (dlin[i->first] == -1)
				{
					dlin[i->first] = i->second + dlin[ver];
				}
				else
				{
					if (dlin[i->first] > (i->second + dlin[ver]))
						dlin[i->first] = i->second + dlin[ver];
				}
				heap->add(i->first, dlin[i->first]);
			}
		}
	}
	f2 << dlin[n];
	f1.close();
	f2.close();
	delete heap;
	return 0;
}