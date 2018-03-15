#include <iostream>
#include <fstream>
#include <vector>
#include <list>
#include <queue>
using namespace std;

typedef list <int>::iterator lit;

bool ModifiedWideSearch(vector<pair <int, int>> &Ribs, bool *Saturated, int N, int K, vector <list <int>> Graf)
{
	vector <int> FreeNodes;//Массив ненасыщенных вершин
	for (int i = 0; i < 2 * N; i++)
	{
		if (!Saturated[i])
		{
			FreeNodes.push_back(i);
		}
	}
	if (FreeNodes.size() == 0)
	{
		return true;
	}
	/*
	Массив посещений вершин:
	-1 - не посещена
	0 - посещена и чётна
	1 - посешена и нечётна
	*/
	int *IsVisited = new int[2 * N];
	/*
	Массив предков
	*/
	int *Ancestors = new int[2 * N];
	for (int i = 0; i < 2 * N; i++)
	{
		IsVisited[i] = -1;
		Ancestors[i] = -1;
	}

	queue <int> Q;
	/*
	Флаг, сообщающий нам, вышли ли мы из очереди,
	найдя ненасыщенную вершину или нет
	*/
	bool QControl = false;
	/*
	Переменная для запоминания найденной ненасыщенной вершины
	*/
	int ControlInt = -1;
	for (int i = 0; i < FreeNodes.size(); i++)
	{
		IsVisited[FreeNodes[i]] = 0;
		Q.push(FreeNodes[i]);
		while (!Q.empty())
		{
			int Buff = Q.front();
			Q.pop();
			if (IsVisited[Buff] == 0)//вершина чётна
			{
				for (lit j = Graf[Buff].begin(); j != Graf[Buff].end(); j++)
				{
					if (IsVisited[*j] == -1)
					{
						IsVisited[*j] = 1;
						Ancestors[*j] = Buff;
						Q.push(*j);
					}
				}
			}
			else///////////////////////вершина нечётна
			{
				if (Saturated[Buff] == true)
				{
					for (int i = 0; i < Ribs.size(); i++)
					{
						if (Ribs[i].first == Buff)
						{
							IsVisited[Ribs[i].second] = 0;
							Ancestors[Ribs[i].second] = Buff;
							Q.push(Ribs[i].second);
							break;
						}
						else if (Ribs[i].second == Buff)
						{
							IsVisited[Ribs[i].first] = 0;
							Ancestors[Ribs[i].first] = Buff;
							Q.push(Ribs[i].first);
							break;
						}
					}
				}
				else
				{
					ControlInt = Buff;
					QControl = true;//Найдена ненасыщенная вершина, а значит и увеличивающая цепь
					break;
				}
			}
		}
		if (QControl)
		{
			break;
		}
	}
	if (!QControl)
	{
		delete IsVisited;
		delete Ancestors;
		return true;
	}
	

	vector <pair <int, int>> IncreasingChain;
	int buff2 = ControlInt;
	while (Ancestors[buff2] != -1)
	{
		int buff3 = Ancestors[buff2];
		if (buff3 < buff2)
		{
			IncreasingChain.push_back(make_pair(buff3, buff2));
			buff2 = buff3;
		}
		else
		{
			IncreasingChain.push_back(make_pair(buff2, buff3));
			buff2 = buff3;
		}
	}

	Saturated[ControlInt] = true;//Последний элемент цепи
	Saturated[buff2] = true;//Первый элемент цепи

	for (int i = 0; i < IncreasingChain.size(); i++)
	{
		if (i % 2 == 0)
		{
			Ribs.push_back(IncreasingChain[i]);
		}
		else
		{
			for (vector <pair <int, int>>::iterator vit = Ribs.begin(); vit != Ribs.end(); vit++)
			{
				if (vit->first == IncreasingChain[i].first && vit->second == IncreasingChain[i].second)
				{
					Ribs.erase(vit);
					break;
				}
			}
		}
	}
	delete IsVisited;
	delete Ancestors;
	return false;
}

int main()
{
	ifstream fin("input.in");
	ofstream fout("output.out");
	int N, K;
	fin >> N >> K;
	int **Mass = new int *[N];
	for (int i = 0; i < N; i++)
	{
		Mass[i] = new int[K];
		for (int j = 0; j < K; j++)
		{
			fin >> Mass[i][j];
			cout << Mass[i][j] << " ";
			Mass[i][j]--;
		}
		cout << endl;
	}
	fin.close();
	/*
	Список смежности для задания графа
	*/
	vector <list <int>> Graf(2 * N, list <int>());
	for (int i = 0; i < N; i++)
	{
		bool *Flags = new bool[N] {false};
		for (int j = 0; j < K; j++)
		{
			if (!Flags[Mass[i][j]])
			{
				Flags[Mass[i][j]] = true;
				Graf[i].push_back(Mass[i][j] + N);
				Graf[Mass[i][j] + N].push_back(i);
			}
		}
		delete Flags;
	}
	//Массив насыщения
	bool *Saturated = new bool[2 * N] {false};
	//Случайным образом сгенерируем паросочетание, необязательно наибольшее
	vector<pair <int, int>> Ribs;
	for (int i = 0; i < N; i++)
	{
		for (lit j = Graf[i].begin(); j != Graf[i].end(); j++)
		{
			if (!Saturated[*j])
			{
				Saturated[*j] = true;
				Saturated[i] = true;
				Ribs.push_back(make_pair(i, *j));
				break;
			}
		}
	}
	bool flag = false;
	while (!flag)
	{
		flag = ModifiedWideSearch(Ribs, Saturated, N, K, Graf);
	}
	int *Result = new int [N];
	for (int i = 0; i < N; i++)
	{
		Result[Ribs[i].first] = Ribs[i].second;
	}
	for (int i = 0; i < N; i++)
	{
		fout << Result[i] - N + 1 << " ";
	}
	fout.close();
	delete Result;
	delete Saturated;
	for (int i = 0; i < N; i++)
	{
		delete Mass[i];
	}
	delete []Mass;
	return 0;
}