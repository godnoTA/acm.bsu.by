#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<fstream>
#include<stack>
#include<string>
#include<sstream>
using namespace std;

static int sizeMatrix;
static int mNumberSubstance;
void main() 
{
	ifstream in("in.txt");
	in >> sizeMatrix >> mNumberSubstance;
	long** matrix = new long*[sizeMatrix];
	for (int i = 0; i < sizeMatrix; i++)
		matrix[i] = new long[sizeMatrix];
	for (int i = 0; i<sizeMatrix; i++)
		for (int j = 0; j<sizeMatrix; j++)
		    in>>matrix[i][j];
	stack <long>myDates;
	long t;
	in >> t;
	myDates.push(t);
	long current;
	in >> current;
	while (!in.eof())
	{	
		long previos = myDates.top();
		myDates.pop();
		while (true) {
			if(current==previos){
				myDates.push(previos);
				break;
			}
			else if (matrix[current - 1][previos - 1] == 0)
			{
				myDates.push(previos);
				myDates.push(current);
				break;
			}
			else if (myDates.empty()) {
				myDates.push(matrix[current - 1][previos - 1]);
				break;
			}
			else {
				current = matrix[current - 1][previos - 1];
				previos = myDates.top();
				myDates.pop();
			}
		}
		in >> current;
	}
	ofstream out("out.txt");
	string str = "";
	while (!myDates.empty())
	{
		if (!myDates.empty())
			str+= to_string(myDates.top()) + " ";
		else
			str+= to_string(myDates.top());
		myDates.pop();
	}
	for(int i=0;i<str.length()-1;i++)
		out << str[i];
	out << endl;
	out.close();
	in.close();
}
