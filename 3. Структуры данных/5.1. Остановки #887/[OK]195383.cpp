#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cmath>
using namespace std;
struct StopInfo
{
	int WayIndex;//Индекс маршрута
	int VectorIndex;//Индекс остановки в маршруте
	int Value;//Значение индекса остановки
	int Time;//Время, нужное для достижения данной остановки
	vector <pair<int, int>> Points;//Точки остановки до текущей не включая
/*	int PrevWayIndex;//Индекс маршрута остановки, из которой мы попали в эту
	int PrevVectroIndex;// Индекс остановки в маршруте, из которой мы попали в эту*/
};
int main()
{
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	int n, r, I, J;
	fin >> n >> r >> J >> I;
	if (I == J)//Проверка на равенство I и J
	{
		fout << 0;
		fin.close();
		fout.close();
		return 0;
	}
	vector <int> Amount(n);
	for (int i = 0; i < n; i++)
	{
		Amount[i] = 0;
	}
	vector <pair<int, int>> *Ways = new vector <pair<int, int>>[r];
	for (int i = 0; i < r; i++)
	{
		int Buff;
		fin >> Buff;
		for (int j = 0; j < Buff; j++)
		{
			int Buff2;
			fin >> Buff2;
			Ways[i].push_back(make_pair(Buff2, -1));
			Amount[Buff2 - 1]++;
		}
	}
	if (Amount[I - 1] == 0 || Amount[J - 1] == 0)//Проверка на присутствие остановок I и J
	{
		fout << "NoWay";
		fin.close();
		fout.close();
		return 0;
	}
	queue <StopInfo> Q;
	int MaxI = Amount[I - 1];
//	cout << MaxI << endl << endl;
	//Первичная загрузка всех вариантов стартовой остановки в очередь
	for (int i = 0; i < r; i++)
	{
		for (int j = 0; j < Ways[i].size(); j++)
		{
			if (Ways[i][j].first == I && MaxI != 0)
			{
				Ways[i][j].second = 0;
				StopInfo Buff;
				Buff.WayIndex = i;
				Buff.VectorIndex = j;
				Buff.Value = I;
				Buff.Time = 0;
//				Buff.PrevWayIndex = -1;
//				Buff.PrevVectroIndex = -1;
				Q.push(Buff);
				MaxI--;
			}
		}
	}
	//Конец первичной загрузки
	int Min = -1;
	vector <StopInfo> SuccessList;
	while (!Q.empty())
	{
		StopInfo Buff = Q.front();
		Q.pop();
		if (Buff.Value == J)//Success!!!!! Положить в список успешных путей
		{
			if (Min > Buff.Time || Min == -1)
			{
				Min = Buff.Time;
				SuccessList.push_back(Buff);
			}
		}
		else
		{//Переезд на следующую остановку в текущем маршруте
			if ((Buff.VectorIndex != Ways[Buff.WayIndex].size() - 1) && (Ways[Buff.WayIndex][Buff.VectorIndex + 1].second == -1 || Ways[Buff.WayIndex][Buff.VectorIndex + 1].second >= Buff.Time + 1))
			{
				StopInfo Buff2;
				Buff2.WayIndex = Buff.WayIndex;
				Buff2.VectorIndex = Buff.VectorIndex + 1;
				Buff2.Value = Ways[Buff.WayIndex][Buff.VectorIndex + 1].first;
				Buff2.Time = Buff.Time + 1;
//				Buff2.PrevWayIndex = Buff.WayIndex;
//				Buff2.PrevVectroIndex = Buff.VectorIndex;
				for (int d = 0; d < Buff.Points.size(); d++)
				{
					Buff2.Points.push_back(Buff.Points[d]);
				}
				Buff2.Points.push_back(make_pair(Buff.WayIndex, Buff.VectorIndex));
				Q.push(Buff2);
				Ways[Buff.WayIndex][Buff.VectorIndex + 1].second = Buff2.Time;
			}
			//Переезд на предыдущую остановку в текущем маршруте
			if ((Buff.VectorIndex != 0) && (Ways[Buff.WayIndex][Buff.VectorIndex - 1].second == -1 || Ways[Buff.WayIndex][Buff.VectorIndex - 1].second >= Buff.Time + 1))
			{
				StopInfo Buff2;
				Buff2.WayIndex = Buff.WayIndex;
				Buff2.VectorIndex = Buff.VectorIndex - 1;
				Buff2.Value = Ways[Buff.WayIndex][Buff.VectorIndex - 1].first;
				Buff2.Time = Buff.Time + 1;
				//				Buff2.PrevWayIndex = Buff.WayIndex;
				//				Buff2.PrevVectroIndex = Buff.VectorIndex;
				for (int d = 0; d < Buff.Points.size(); d++)
				{
					Buff2.Points.push_back(Buff.Points[d]);
				}
				Buff2.Points.push_back(make_pair(Buff.WayIndex, Buff.VectorIndex));
				Q.push(Buff2);
				Ways[Buff.WayIndex][Buff.VectorIndex - 1].second = Buff2.Time;
			}
			for (int i = 0; i < r; i++)
			{//Пересадка на другой маршрут или простая смена автобуса с сохранением маршрута
				for (int j = 0; j < Ways[i].size(); j++)
				{
					if (Ways[i][j].first == Buff.Value && (Ways[i][j].second == -1 || Ways[i][j].second >= Buff.Time + 3))
					{
						StopInfo Buff2;
						Buff2.WayIndex = i;
						Buff2.VectorIndex = j;
						Buff2.Value = Buff.Value;
						Buff2.Time = Buff.Time + 3;
						//							Buff2.PrevWayIndex = Buff.WayIndex;
						//							Buff2.PrevVectroIndex = Buff.VectorIndex;
						for (int d = 0; d < Buff.Points.size(); d++)
						{
							Buff2.Points.push_back(Buff.Points[d]);
						}
						Buff2.Points.push_back(make_pair(Buff.WayIndex, Buff.VectorIndex));
						Q.push(Buff2);
						Ways[i][j].second = Buff2.Time;
					}
				}
			}
		}
	}


	if (SuccessList.empty())
	{
		fout << "NoWay";
		fin.close();
		fout.close();
		return 0;
	}


	StopInfo Success;
	for (int i = 0; i < SuccessList.size(); i++)
	{
		if (SuccessList[i].Time == Min)
		{
			Success = SuccessList[i];
		}
	}
	Success.Points.push_back(make_pair(Success.WayIndex, Success.VectorIndex));
	fout << Success.Time << endl;

	for (int i = 0; i < Success.Points.size() - 1; i++)
	{
		if (Success.Points[i].first == Success.Points[i + 1].first && (abs(Success.Points[i].second - Success.Points[i + 1].second) == 1))
		{
			fout << "StopNo " << Ways[Success.Points[i].first][Success.Points[i].second].first << " ";
			fout << "BusNo " << Success.Points[i].first + 1 << endl;
		}
	}
	int i = Success.Points.size() - 1;
	fout << "StopNo " << Ways[Success.Points[i].first][Success.Points[i].second].first << " ";
	fout << "BusNo " << Success.Points[i - 1].first + 1;

/*	for (int i = 0; i < r; i++)
	{
		for (int j = 0; j < Ways[i].size(); j++)
		{
			cout << Ways[i][j] << " ";
		}
		cout << endl;
	}*/
	fin.close();
	fout.close();
}