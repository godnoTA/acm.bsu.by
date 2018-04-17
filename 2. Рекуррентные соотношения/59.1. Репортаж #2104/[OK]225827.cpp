#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

struct Day
{
	int number;
	int height;
	int parametrForFind;
	Day *previousDay; 

	Day() {
		number = 0;
		height = 0;
		parametrForFind = 0;
		previousDay = nullptr;
	}

	Day(const Day &d) {
		number = d.number;
		height = d.height;
		parametrForFind = d.parametrForFind;
		previousDay = d.previousDay;
	}

	friend ostream& operator <<(ostream& os, const Day &d) {
		os << d.number << "/" << d.height << "/" << d.parametrForFind;
		return os;
	}

};

int main() {

	ifstream in("report.in");
	ofstream out("report.out");

	int numberOfDays;
	in >> numberOfDays;

	Day temp;
	vector <Day> Original;
	for (int i = 0; i < numberOfDays; i++) {
		temp.number = i + 1;
		in >> temp.height;
		Original.push_back(temp);
	}

	vector <Day> Direct = Original;
	for (int i = 0; i < numberOfDays; i++) {
		int param = 0;
		for (int j = 0; j < i; j++) {
			if (Direct[j].height < Direct[i].height && param <= Direct[j].parametrForFind) {
				Direct[i].parametrForFind = Direct[j].parametrForFind + 1;
				Direct[i].previousDay = &Direct[j];
				param++;
			}
		}
	}

	vector <Day> Reserv = Original;
	reverse(Reserv.begin(), Reserv.end());
	for (int i = 0; i < numberOfDays; i++) {
		int param = 0;
		for (int j = 0; j < i; j++) {
			if (Reserv[j].height < Reserv[i].height && param <= Reserv[j].parametrForFind) {
					Reserv[i].parametrForFind = Reserv[j].parametrForFind + 1;
					Reserv[i].previousDay = &Reserv[j];
					param++;
				}
		}
	}

	for (int i = 0; i < numberOfDays; i++) {
		cout << Direct[i] << endl;
	}
	cout << "======" << endl;
	for (int i = 0; i < numberOfDays; i++) {
		cout << Reserv[i] << endl;
	}

	cout << "===" << endl;
	
	vector <Day> result;
	for (int i = 0; i < numberOfDays; i++) {
		int temp;
		Direct[i].parametrForFind <= Reserv[numberOfDays - 1 - i].parametrForFind ? temp = Direct[i].parametrForFind :
			temp = Reserv[numberOfDays - 1 - i].parametrForFind;
		if ((result.size() - 1)/2 <= temp || result.size() == 0 ) {
			if (result.size() != 0) result.clear();
			result.push_back(Direct[i]);
			if (temp != 0) {
				Day N1 = *Direct[i].previousDay;
				Day N2 = *Reserv[numberOfDays - 1 - i].previousDay;
				for (int i = 0; i < temp; i++) {
					result.push_back(N1);
					result.push_back(N2);
					if (i + 1 != temp) {
						N1 = *N1.previousDay;
						N2 = *N2.previousDay;
					}
				}
			}
		}
	}

	int ans = result.size();
	ans == 1 ? ans = 0 : ans = (result.size() - 1) / 2;

	vector <int> a;
	for (int i = 0; i < result.size(); i++) {
		a.push_back(result[i].number);
	}
	sort(a.begin(), a.end());
	for (int i = 0; i < a.size(); i++) {
		cout << a[i] << " ";
	}
	cout << endl;

	out << ans << endl;
	for (int i = 0; i < a.size(); i++) {
		out << a[i] << " ";
	}
	return 0;
}