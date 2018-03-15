#include <iostream>
#include <fstream>
#include <vector>
#include <math.h>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	int a,b,c,i,min,j;
	int ans = 0;
	fin >> a;
	fin >> b;
	fin >> c;

	ofstream fout("output.txt");

	if(a<b) { ans = -1; }
	else {
		if(a<=c) { ans = 1; }
		else {
			float st = sqrt((float)a)+1;
			if(st<b) { ans = -1; }
			else {
				vector<int> s,s2;
				if(b!=1) { s.push_back(1); }

				for(i=b;i<=st;i++) {
					if(a%i==0) {
						s.push_back(i);
						s2.push_back(a/i);
					}
				}
				i=s2.size()-1;
				if(i>=0) {
					if(s.back()==s2[i]) { i--; }
				}
				for(;i>=0;i--) {
					s.push_back(s2[i]);
				}
				if(s.back()!=a) { s.push_back(a); }

				i = s.size();
				vector<int> d(i,0);
				if(b==1) { d[0] = 1; }
				if(b!=1) { d[0] = 0; }
				for(i=1;i<s.size();i++) {
					int k = s[i];
					min = a;
					if((k>=b)&&(k<=c)) {
						d[i] = 1;
					}
					else {
						for(j=0;j<i;j++) {						
							int l = s[j];
							if(k%l==0) {
								int f = k/l;						
								if((f>=b)&&(f<=c)) {
									int e = d[j];
									if(e<min) {
										min = e;
									}
								}					
							}
							d[i] = min+1;
						}					
					}
				}
				i = d.size()-1;
				ans = d[i];						
			}
			
		}
	}
	if(ans>=a) {ans = -1;}
	if((ans==1)&&(a>c)) { ans = -1; }
	if(ans == 0) { ans = -1;}
	fout << ans;
	fout.close();
	fin.close();
	return 0;
}