#include<iostream>
#include<fstream>
#include<set>
using namespace std;
class MyElement {
	set<int> dates;
public: 
	MyElement() { }
	void toStr(ofstream &out) {
		string str = "";
		int k = dates.size();
		out << k << " ";
		for (set<int> ::iterator t = dates.begin(); t != dates.end(); t++)
			out << *t << " ";
		out<<"\n";
	}
	void addToMas(int t) {
		dates.insert(t);
	}
};
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, m,a,b;
	in >> n >> m;
	MyElement **mas = new MyElement*[n];
	for (int i = 0; i<n; i++)
		mas[i] = new MyElement();
		for (int i = 0; i<m; i++)
		{
			in >> a >> b;
			mas[a - 1]->addToMas(b);
			mas[b - 1]->addToMas(a);
		}
		for (int i = 0; i<n; i++)
			mas[i]->toStr(out);
		out.close();
		in.close();
}
