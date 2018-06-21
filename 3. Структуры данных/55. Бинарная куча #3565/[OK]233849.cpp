#include <iostream> 
#include <fstream> 
#include <string> 

using namespace std;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n;
	in >> n;
	n++;
	string str="Yes";
	int*mas=new int[n];
	for(int i=1; i<n+1; i++)
		in>>mas[i];
	int i=1;
	while(i<(n)/2){
		//cout<<str<<endl;
		if(mas[i]>mas[2*i]||mas[i]>mas[2*i+1]){
			str="No";
			//break;
		}
		i++;
	}
	if(i==(n)/2){
		if(mas[i]>mas[2*i]){
			str="No";
			
		}
	}
	out<<str<<endl;
	delete[]mas;
	return 0;
}