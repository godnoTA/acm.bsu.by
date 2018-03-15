#include <iostream>
#include <vector>
#include <fstream>

using namespace std;


bool isK(vector<int>& v, int i){
	bool w = true;
	if(i < v.size()){
		if(isK(v, 2*i) == false)
			w = false;
		if(isK(v, 2*i + 1) == false)
			w = false;
		//(2i < v.size()/2  ){
			if(2*i-1 < v.size() && v[i-1] > v[2*i-1])
				w = false;
			if(2*i < v.size() && v[i-1] > v[2*i])
				w = false;	
		//}
	}
	return w;
}

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	
	int n;
	cin >> n;
	vector<int> v(n, 0);
	for(int i = 0; i < n; i++){
		cin >> v[i];
	}
	bool w = isK(v, 1);
	if(w)
		cout << "Yes\n";
	else
		cout << "No\n";
	return 0;
}