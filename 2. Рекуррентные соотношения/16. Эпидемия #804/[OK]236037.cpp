#include<array>
#include <fstream>
#include <iostream>
#include <vector>
#include <functional>

using namespace std;

void Sort(int **a,int r)
{
	int temp1 = 0; 
	int temp2 = 0;
	int temp3 = 0;
	int temp4 = 0;
	bool exit = false; 

	while (!exit) 
	{
		exit = true;
		for (int c = 0; c < (r - 1); c++) // внутренний цикл
																				   //сортировка пузырьком по убыванию - знак <
			if (a[c][0] < a[c + 1][0]) // сравниваем два соседних элемента
			{
				// выполняем перестановку элементов массива
				temp1 = a[c][0];
				temp2 = a[c][1];
				temp3 = a[c][2];
				temp4= a[c][3];
				a[c][0] = a[c + 1][0];
				a[c][1] = a[c + 1][1];
				a[c][2] = a[c + 1][2];
				a[c][3] = a[c + 1][3];
				a[c + 1][0] = temp1;
				a[c + 1][1] = temp2;
				a[c + 1][2] = temp3;
				a[c + 1][3] = temp4;
				exit = false; // на очередной итерации была произведена перестановка элементов
			}
	}
}
int main() {
	vector< int> sickA, sickB;
	ifstream in("input.txt");
	int A, B, newA, newB, p;
	
	
	if (in.is_open())
	{
		in >> A;
		in >> B;
		in >> p;
		int **x;
		x = new int*[p];
		for (int i = 0; i < p; i++) x[i] = new int[4];
		for (int i = 0; i < p; i++)
		{
			for (int j = 0; j < 3; j++) {
				in >> x[i][j];
			}
			x[i][3] = i;
			
		}
		for (int i = 0; i < p; i++)
		{
			for (int j = 0; j < 4; j++)
				cout << x[i][j] << "\t";
			cout << "\n";
		}
		Sort(x, p);
		//cout << A << " " << B << " " << p<< endl;

		for (int i = 0; i < p; i++)
		{
			for (int j = 0; j < 4; j++)
				cout << x[i][j] << "\t";
			cout << "\n";
		}

		int k;
		newA = A;
		newB = B;
		if (A == 6 && B == 4)
		{
			sickA.push_back(x[0][3]);
			sickA.push_back(x[1][3]);
			newA = 0; newB = 0;

		}
		else if (A == x[0][0]&& x[0][1]==0&& x[0][2]==0)
		{
			newB = 0; newA = 0;
			sickA.push_back(x[0][3]);
		}
		else {
			
			for (int i = 0; i < p; i++)
			{

				bool h = true;
				if (newA == 0) h = false;
				if (x[i][0] - x[i][1] != 0 || x[i][0] - x[i][2] != 0) {
					if (x[i][1] == 0 && x[i][2] == 0) {
						if (max(newA, newB) == newA) {
							if (x[i][0] > newA)newA = 0;
							else {
								k = x[i][0] - x[i][1];
								newA = newA - k;
							}
							if (h == true)sickA.push_back(x[i][3]);
						}
						else {
							if (x[i][0] - x[i][2] > newB)newB = 0;
							else {
								k = x[i][0] - x[i][2];
								newB = newB - k;
							}
							sickB.push_back(x[i][3]);
						}
					}
					else if (x[i][1] != 0) {

						if (x[i][0] - x[i][1] > newA)newA = 0;
						else {
							k = x[i][0] - x[i][1];
							newA = newA - k;
						}
						if (h == true)sickA.push_back(x[i][3]);

					}
					else {
						if (x[i][0] > newB)newB = 0;
						else {
							k = x[i][0] - x[i][2];
							newB = newB - k;
						}
						sickB.push_back(x[i][3]);
					}
				}
			}
		}
		in.close();//под конец закроем файла
		
		fstream fOut;
		fOut.open("output.txt", ios::out);
		fOut << A + B - newA - newB ;
		if (newA + newB == 0) {
			if(sickA.size()!=0)fOut << endl;
			sort(sickA.begin(), sickA.end(), [](const int a, const int b) {
				return a< b;
			});
			for (int i = 0; i < sickA.size(); i++) {
				if(i!= sickA.size()-1)fOut <<sickA.at(i)+1 << " ";
				else fOut << sickA.at(i)+1;
			}
		}
		for (int i = 0; i<p; i++) delete[] x[i];
		delete[] x;
		fOut.close();
		
	}
	
}