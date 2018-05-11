#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>

using namespace std;

struct card
{
    long double a;
	long double b;
};

struct cover
{
	long double a;
	long double b;
};

int max;
int n;
card *aCard;
cover *aCover;
vector<vector<int>> cac;
vector<int> met;
vector<char> visit;

bool kun(int v) {
	if (visit[v])  return false;
	visit[v] = true;
	for (int i = 0; i < cac[v].size(); ++i) {
		int to = cac[v][i];
		if (met[to] == -1 || kun(met[to])) {
			met[to] = v;
			return true;
		}
	}
	return false;
}

bool cardInCover(int iCover, int iCard) {
	long double a, b, A, B;
	if (aCover[iCover].a >= aCover[iCover].b) {
		B = aCover[iCover].a;
		A = aCover[iCover].b;
	}
	else
	{
		B = aCover[iCover].b;
		A = aCover[iCover].a;
	}
	if (aCard[iCard].a >= aCard[iCard].b) {
		b = aCard[iCard].a;
		a = aCard[iCard].b;
	}
	else
	{
		b = aCard[iCard].b;
		a = aCard[iCard].a;
	}

	if (a <= A && b <= B)
		return true;

	if (sqrt(a*a + b*b) > sqrt(A*A + B*B))
		return false;

	if (A*B < a*b)
		return false;


	long double ss, p, q;
	ss = a*a+b*b;
	p = sqrt(ss - B*B);
	q = sqrt(ss - A*A);
	return A*B - p * q >= a*b * 2;
}

int main() {
	fstream f("input.in");
	f >> n;
	aCard = new card[n];
	aCover = new cover[n];
	for (int i = 0;i < n;i++) {
		f >> aCard[i].a >> aCard[i].b;
	}
	for (int i = 0;i < n;i++)
		f >> aCover[i].a >> aCover[i].b;
	f.close();
	
	vector<int> temp;
	cac.assign(n, temp);

	for (int iCard = 0; iCard < n; iCard++) {
		for (int iCover = 0; iCover < n; iCover++) {
			if (cardInCover(iCover, iCard))
			{
				cac[iCover].push_back(iCard);
			}
		}
	}

	max = 0;
	met.assign(n, -1);
	for (int v = 0; v<n; ++v) {
		visit.assign(n, false);
		if (kun(v))
			max++;
	}

	ofstream of("output.out");
	if (max == n)
		of << "YES";
	else
		of << "NO" << endl << max;
	of.close();
	return 0;
}