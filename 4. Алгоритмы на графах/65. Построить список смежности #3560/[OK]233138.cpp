#include <iostream> 
#include <fstream> 
#include <string> 

using namespace std;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n, m;
	in>>n;
	n++;
	in >> m;

	int* count = new int[n];
	string* str = new string[n];
	for (int i = 0; i < n; i++)
	{
		count[i] = 0;
		str[i] = "";
	}
	
	int a, b;
	for (int i = 0; i<m; i++) {
		in>>a;
		in>>b;
		count[a ]++;
		count[b ]++;
		str[a ]  += " " + to_string(b);
		str[b]  += " " + to_string(a);
	}
	
	for (int i = 1; i < n; i++)
		out <<to_string(count[i])<< str[i]<<endl;
	delete[] count;
	delete[]str;
	return 0;
}