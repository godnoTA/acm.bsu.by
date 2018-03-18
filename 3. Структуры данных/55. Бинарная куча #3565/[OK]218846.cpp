// binHeap.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include <fstream>
#include <iterator> 
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
	ifstream in("input.txt");
	int size;
	in >> size;
	vector<int> numbers;
	copy(
		istream_iterator<int>(in),
		istream_iterator<int>(),
		back_inserter(numbers));
	ofstream out("output.txt");
	out << (is_heap(numbers.begin(), numbers.end(), [](int a, int b){ return a > b; }) ? "Yes" : "No");
	out.close();
	return 0;
}

