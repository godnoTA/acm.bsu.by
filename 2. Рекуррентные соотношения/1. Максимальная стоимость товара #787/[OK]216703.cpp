#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");


	int n, m, summa, d;
	summa = 0;

	fin >> n;
	fin >> m;

	int *mas = new int [n+m];

	for(int i = 0; i < n; i++)
	{
		fin >> d;
		mas[i] = d;
		summa = summa + d;
	}

	for(int i = n; i < n + m; i++)
	{
		fin >> d;
		mas[i] = d;
	}
	
	 int temp = 0; // временная переменная для хранения элемента массива
	bool exit = false; // болевая переменная для выхода из цикла, если массив отсортирован
 
 while (!exit) // пока массив не отсортирован
 {
  exit = true;
  for (int int_counter = 0; int_counter < n + m-1; int_counter++) // внутренний цикл
    //сортировка пузырьком по возрастанию - знак >
    //сортировка пузырьком по убыванию - знак <
    if (mas[int_counter] > mas[int_counter + 1]) // сравниваем два соседних элемента
    {
     // выполняем перестановку элементов массива
     temp = mas[int_counter];
     mas[int_counter] = mas[int_counter + 1];
     mas[int_counter + 1] = temp;
     exit = false; // на очередной итерации была произведена перестановка элементов
    }
 }

	int summa1 = 0;
	int y = 0;
	while (y < n + m && mas[y] <= summa1 + 1)
	{
		summa1 = summa1 + mas[y];
		y++;
	}

	if(summa1 >= summa)
		fout <<"YES";
	else
		fout <<"NO" <<"\n" << summa - summa1 - 1;

	return 0;
}