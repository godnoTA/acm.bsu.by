#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

static int ans = 0;
static int k = 0;

void Read(int i,int b,vector<int> &v,int** const arr) {
	if(ans!=0) {}
	else {
		int n = v.size();
		if(v[i]==b) { ans=1; }
		else {
			if(v[i]==0) {
				if(b==1) {
					v[i]=2;
				}
				else {
					v[i]=1;
					k++;
				}
				int j = 0;
				while(j<n) {
					if(ans!=0) { break; }
					int t = arr[i][j];
					if(t==1) {
						if(b==1) { Read(j,2,v,arr); }
						if(b==2) { Read(j,1,v,arr); }
					}
					j++;
				}
			}
			else {}
		}
	}
}

int main()
{
	ifstream fin("input.in");
	ofstream fout("output.out");
	int n;
	fin >> n;

	vector<int> v(n,0);
	int** arr = new int*[n];
	
	for(int i=0;i<n;i++) {
		arr[i] = new int[n];
		for(int j=0;j<n;j++) {
			int s;
			fin >> s;
			arr[i][j] = s;
		}
	}

	int l = 0;
	while(l<n) {
		if(ans!=0) { break; }
		if(v[l]==0) {
			Read(l,2,v,arr);
		}
		l++;
	}

	if(ans!=0) {
		fout << "NO";
	}
	else {
		fout << "YES" << endl;
		int g = 0;
		int h = n/2;
		int t = 0;
		if(k==n) {

			while(g<h) {
				if (t!=0) fout << " ";
				else t = 1;
				fout  << (g+1);                
				g++;
			}
		}
		else {
			if(k<=h) {
				while(g<n) {
					if(v[g]==1) {
						if (t!=0) fout << " ";
						else t = 1;
						fout << " " << (g+1);
					}
					g++;
				}
			}
			else {
				while(g<n) {
					if(v[g]==2) {
						if (t!=0) fout << " ";
						else t = 1;
						fout << (g+1) << " ";
					}
					g++;
				}
			}
		}
	}

	fout.close();
	fin.close();
	return 0;
}