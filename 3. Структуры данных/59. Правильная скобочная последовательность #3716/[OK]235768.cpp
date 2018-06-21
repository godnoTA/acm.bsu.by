#pragma comment (linker, "/STACK:64000000")
#include<iostream>
#include<fstream>
#include<stack>
#include<functional>
#include<algorithm>
#include<vector>
#include<cstdlib>

std::ifstream fin("input.txt");
std::ofstream fout("output.txt");

int func(std::vector<char> v, std::stack<char> s){
	for (int i = 0; i < v.size(); i++) {
		char c = v[i];
		switch (c) {
		case '(': case '{': case '[':
			s.push(c);
			break;
		case ')': case '}': case ']':
			if (s.empty() || (c == ')' && s.top() != '(') ||
				(c == '}' && s.top() != '{') || (c == ']' && s.top() != '[') ) {
				if (i == 0)
					return -1;
				return i;
			}
			s.pop();
			break;
		}
	}
	if (s.size() != 0)
		return v.size();
	else
	return s.size();
}
int main() {
	char c;
	std::stack<char> s;
	int kolv=0;
	std::vector<char> vect;
	fin >> c;
	while (!fin.eof())
	{
		vect.push_back(c);
		fin >> c;
	}
	int res = func(vect, s);
	if (res==0) {
		fout << "YES";
		std::cout << "YES";
	}
	else {
		fout << "NO"<<"\n";
		std::cout << "NO" << std::endl;
		if (res != -1){
			int kol = res;
			fout << kol;
			std::cout << kol<<std::endl;
		}
		else {
			std::cout << 0<<std::endl;
			fout << 0;
		}
	}

	system("pause");
	return 0;
}