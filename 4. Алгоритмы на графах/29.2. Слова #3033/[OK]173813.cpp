#include <iostream>
#include <fstream>
#include <deque>
#include <string>
#include <stack>
#include <vector>
#include <iterator>

using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");

int main()
{
	int n;
	string temp;
	vector< vector<string> > masOne;
	deque<string> second;
	stack<string> first;
	int masTwo[26]={};
	for(int i=0;i<26;i++)
		masOne.push_back(vector<string>());
	in>>n;
	if(n==1)
	{
		in>>temp;
		out<<"Yes"<<endl;
		out<<temp;
		out.close();
		exit(0);
	}
	bool flag = true;
	char ch=0;
	for(int i=0;i<n;i++)
	{
		in>>temp;
		char f=temp[0];
		char s=temp[temp.length()-1];
		masOne[f-'a'].push_back(temp);
		masTwo[s-'a']++;
		if(flag)
			ch=f;
		flag=false;
	}
	for(int i=0;i<26;i++)
	{
		if(masOne[i].size()==masTwo[i])
			continue;
		else
		{
			out<<"No";
			out.close();
			exit(0);
		}			
	}
	temp=masOne[ch-'a'][masOne[ch-'a'].size()-1];
	first.push(temp);
	masOne[ch-'a'].erase(masOne[ch-'a'].end()-1);
	while(!first.empty())
	{
		if(!masOne[temp[temp.length()-1]-'a'].empty())
		{
			first.push(masOne[temp[temp.length()-1]-'a'][masOne[temp[temp.length()-1]-'a'].size()-1]);
			masOne[temp[temp.length()-1]-'a'].erase(masOne[temp[temp.length()-1]-'a'].end()-1);
		}
		else
		{
			second.push_back(first.top());
			first.pop();
		}
		if(!first.empty())
			temp=(string)first.top(); 
		else
			break;
	}
	char fr=second.back()[0];
	string sd=second.front();
	if(second.size()==n && fr==sd[sd.length()-1])
	{
		out<<"Yes"<<endl;
		while(!second.empty())
		{
			out<<second.back()<<endl;
			second.pop_back();
		}
		out.close();
	}
	else
		out<<"No";
	out.close();
	return 0;
}