#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;

int main() {

	ifstream fin("input.txt");
	
	int n;
	fin >> n; // число чисел, составляющих сумму

	map<long, long> map_sums; 
	int *res = new int[n];

	int s;

	for (int i = 0; i<n*n; i++) {
		fin >> s;
		map_sums[s]++;
	}
	fin.close();


	map<long, long>::iterator it = map_sums.begin();

	res[0] = it->first / 2; //число, с которого начинается итератор
	it->second--; // уменьшаем количество раз, сколько число повторялось


	for (int j = 1; j < n; j++) {
		while (!it->second) // если число повторений равно 0, то итератор идет дальше по map
			it++;
		res[j] = it->first - res[0]; // число уменьшается на 0-й элемент массива (так мы находим новое число)
		for (int i = 0; i < j; i++) { // j- составное число, находящееся в map, i- число, сумма которого с j дает число из map-a
			map_sums[res[i] + res[j]] -= 2; //уменьшаем значение по ключу на 2, т.к. происходит сумма двух чисел

		}
		map_sums[res[j] + res[j]]--; //сумма одинаковых элементов
	}

	ofstream fout("output.txt");

	for (int j = 0; j < n; j++) {
		fout << res[j] << endl;
	}
	fout.close();


	return 0;


}