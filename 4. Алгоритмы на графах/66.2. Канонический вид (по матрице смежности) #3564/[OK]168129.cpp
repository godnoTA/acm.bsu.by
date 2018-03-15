#include<iostream>
#include<vector>
#include<fstream>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int main(){
	int n;
	fin >> n;

	vector<vector<int>> v(n);

	while ( !fin.eof() ){				 // Пока непусто
		for ( int i = 0; i < n; i++ )	 // Идем по строкам
			for (int j = 0; j < n; j++){ // Идем по столбцам
				int el;
				fin >> el;				 // Читаем из файла элемент
				v[i].push_back(el);		 // Добавляем его в массив
			}
	}
	vector<int> v0(n, 0);

	for (int i = 0; i < n;i++){			
		for (int j = 0; j < n; j++){
			if (v[i][j] == 1) // Если элемент равен 1, значит есть ребро из верш i в верш j
				v0[j] = i+1;
		}
	}

	for (int i = 0; i < n; i++) 
		fout << v0[i] << " ";

	return 0;
}