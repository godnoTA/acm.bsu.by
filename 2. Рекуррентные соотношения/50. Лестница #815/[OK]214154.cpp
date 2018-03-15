#include <iostream>
#include <fstream>

using namespace std;

long long fibonacci(int number) {
	long long temp1 = 1;
	long long temp2 = 2;
	if (number == 1) {
		return 1;
	}
	if (number == 2) {
		return 2;
	}
	for (int i = 3; i <= number; i++) {
		long long temp = temp2;
		temp2 = (temp1 + temp2)%1000000009;
		temp1 = temp%1000000009;
	}
	return temp2;
}

long multiplication(long long num, int grade) {
	long long count = (((fibonacci(num) % 1000000009)*(fibonacci(num) % 1000000009)) + 1)%1000000009;
	long long tmp = 1;
	for (int i = 0; i < grade; i++) {
		tmp *= count;
		tmp = tmp % 1000000009;
	}
	return tmp;
}

void main() {
	ifstream in("input.txt");

	ofstream out("output.txt");
	

	int numberF;

	int numberS;

	in >> numberF;
	in >> numberS;

	out << multiplication(numberS, (numberF-1));

	in.close();
	out.close();
}