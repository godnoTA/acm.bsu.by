#include<iostream>
#include <fstream>
#include <vector>
#include <queue>
using namespace std;
struct top
{
	int num;//номер остановки
	int index;//индекс в маршруте
	int marsh;//маршрут 
	bool visited;
	top(int x, top* f, int y, int o)
	{
		num = x;
		marsh = y;
		index = o;
		visited = false;
	}

};
struct priority
{
	top* vershina;//вершина
	priority* pred;//предок
	long long int level;//приоритет
	priority(priority*p,top*t,long long int lev)
	{
		vershina = t;
		pred = p;
		level = lev;
	}
};

int main()
{
	ifstream filein("in.txt");
	ofstream fileout("out.txt");
	///////////////////////////
	int number_of_stops;
	int number_of_marsh;
	int nachalo, konec, x, y, z;
	filein >> number_of_stops >> number_of_marsh>>nachalo>>konec;
	if (nachalo == konec)
	{
		fileout << "0";
		fileout.close();
		return 0;
	}
	vector<vector<top*>> full(number_of_stops);//в этот массив будем запихивать список смежности
	nachalo = nachalo - 1;konec = konec - 1;
	///////////////////////////////////////////
	bool *attached = new bool[number_of_stops];
	for (int t = 0; t < number_of_stops; t++)
	{attached[t] = false;}
	///////////////////////////////////////////
	queue<priority*>little_ones;
	queue<priority*>big_ways;
	for (int u = 0; u < number_of_marsh; u++)
	{
		filein >> z >> x;top*ver = new top(x - 1, nullptr, u, 0);
		if (x == nachalo + 1)
			little_ones.push(new priority(nullptr, ver, 0));
		for (int t = 1; t < z; ++t)
		{
			filein >> y;
			top* vers = new top(y - 1, nullptr, u, t);
			if (y == nachalo + 1)
				little_ones.push(new priority(nullptr,vers, 0));
			full[y - 1].push_back(ver);
			full[x - 1].push_back(vers);
			ver = vers;
			x = y;

		}
	}
	//////////////////////////////////
	priority* currrent_stop;//ntreofz jcnfyjdrf
	priority* aim = nullptr;
	//////////////////////////////////
	while ((!little_ones.empty()) || (!big_ways.empty()))
	{

		if (big_ways.empty())
		{
			currrent_stop = little_ones.front();
			little_ones.pop();
		}
		else if ((little_ones.empty()) || (big_ways.front()->level <= little_ones.front()->level))
		{
			currrent_stop = big_ways.front();
			big_ways.pop();
		}
		else
		{
			currrent_stop = little_ones.front();
			little_ones.pop();
		}

		if (currrent_stop->vershina->visited)
			continue;

		currrent_stop->vershina->visited = true;
		if (!attached[currrent_stop->vershina->num])
		{
			if (currrent_stop->vershina->num == konec)
			{
				aim = currrent_stop;
				attached[currrent_stop->vershina->num] = true;
				break;
			}
		}
		attached[currrent_stop->vershina->num] = true;
		int size = full[currrent_stop->vershina->num].size();
		for (int i = 0; i < size; ++i)
		{
			auto step_by_step = full[currrent_stop->vershina->num].at(i);
			if (!step_by_step->visited)
			{
				if ((step_by_step->marsh != currrent_stop->vershina->marsh) || (abs(currrent_stop->vershina->index - step_by_step->index) > 1))
					big_ways.push(new priority(currrent_stop,  step_by_step, currrent_stop->level + 4));
				else little_ones.push(new priority(currrent_stop, step_by_step,  currrent_stop->level + 1));
			}
		}
	}
	////////////////////////////////////////////////////////////////////
	if (!attached[konec])fileout << "NoWay" << endl;
	else
	{fileout << aim->level << endl;while (aim != nullptr){fileout <<aim->vershina->num+1  << " " << aim->vershina->marsh+1 << endl;aim = aim->pred;}}
	return 0;
}