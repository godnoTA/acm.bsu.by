#include <iostream>
#include <fstream>
#include <map>

using namespace std;

struct Para {
	int x;
	int y;

	Para(int t=0,int s=0):x(t),y(s) {}

	void Add(int b) {
		switch(b) {
		case 0:
			this->y++;
			break;
		case 1:
			this->x++;
			this->y++;
			break;
		case 2:
			this->x++;
			break;
		case 3:
			this->x++;
			this->y--;
			break;
		case 4:
			this->y--;
			break;
		case 5:
			this->x--;
			this->y--;
			break;
		case 6:
			this->x--;
			break;
		case 7:
			this->x--;
			this->y++;
			break;
		}
	}
};

int main()
{
	ifstream fin("in.txt");
	int n;
	fin >> n;

	ofstream fout("out.txt");
	int ans = 0;
	
	map<pair<int,int>,int> v;
	Para q(0,0);
	v.insert(make_pair(make_pair(q.x,q.y),0));
	
	int i = 0;

	while(i<n) {
		int l;
		fin >> l;
		q.Add(l);		
		v.insert(make_pair(make_pair(q.x,q.y),l));
		i++;
		if((i+1)!=v.size()) {
			ans = 1;
			break;
		}
	}		
	if(ans == 0) {
		fout << "No";
	}
	else {
		fout << "Yes";
	}
	fout.close();
	fin.close();
	return 0;
}