#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main(){
	int m, c, n;
	ifstream cin ("input.txt");
	ofstream cout("output.txt");
	cin >> m >> c >> n;
	vector<int> v(m, -1);
	for(int i = 0; i < n; i++){
		int temp;
		cin >> temp;
		int l = 0;
		int j = 0;
		bool w = true;
		while(w){
			j = (temp % m + c * l) % m;
			if(v[j] == -1 || v[j] == temp){
				v[j] = temp;
				w = false;
			}
			else
				l++;
		}
		
	}
	for(int i = 0; i < m; i++)
		cout << v[i] << " ";

	return 0;

}