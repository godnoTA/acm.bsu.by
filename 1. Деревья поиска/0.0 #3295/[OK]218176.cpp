#include <iostream>
#include <fstream>
#include <ctime>
#include <set>
#include <iterator>
//#include <cmath>

using namespace std;

int main()
{
	set<long> TreeSet;
	long num;
	long long sum = 0;

	std::ifstream input("input.txt");
	std::ofstream output("output.txt");

	if (!input.is_open())
		return 1;
	while (input >> num) 
		TreeSet.insert(num);
	
	/*copy(TreeSet.begin(), TreeSet.end(), ostream_iterator<long>(cout, " "));*/
	for (std::set<long>::iterator iter = TreeSet.begin(); iter != TreeSet.end(); ++iter) {
		sum += (*iter);
	}
	output << sum;
	
	system("pause");
	return 0;
}

