// Example program
#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

vector<int> prefix (string s) {
	int n = (int) s.length();
	vector<int> p (n);
	for (int i = 1; i < n; ++i) {
		int j = p[i-1];
		while (j > 0 && s[i] != s[j]){
			j = p[j-1];
                }
		if (s[i] == s[j]) ++j;
		p[i] = j;
	}
	return p;
}

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    int n;
    fin >> n;
    string s;
    fin >> s;
    vector<int> pref = prefix(s);
    for (int i = 1; i < n; i++){
        if (pref[i] != 0 && (i+1) % ((i+1)-pref[i]) == 0 && (i+1) <= 2 * pref[i]){
            fout << (i+1) << ' ' << (i+1) / ((i+1)-pref[i]) << endl;
        }
    }
}

