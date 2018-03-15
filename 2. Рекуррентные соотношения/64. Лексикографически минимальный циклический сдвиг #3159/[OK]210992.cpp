#include<iostream>
#include<string>
#include<fstream>
#include<vector>
#include <algorithm>
using namespace std;


int z_function (const vector<int>& sequence, vector<int>& z) {
    int size = sequence.size();
    for (int pos = 1, greatPos = 0, border = 0; pos < size; ++pos) {
        if (pos <= border)
            z[pos] = min (border-pos+1, z[pos-greatPos]);
        while (pos + z[pos] < size && sequence[z[pos]] == sequence[pos + z[pos]])
            ++z[pos];
        if (pos + z[pos] - 1 > border)
            greatPos = pos,  border = pos + z[pos] - 1;
        if (pos + z[pos] == size)
            return pos;
    }
    return size;
}


int rotate (string s) {
    s += s;
    int size = (int)s.length();
    int i = 0, j, k;
    int res = 0;
    while (i < size/2) {
        res = i;
        j = i + 1;
        k = i;
        while (j < size && s[k] <= s[j]) {
            if (s[k] == s[j]) ++k;
            else k = i;
            ++j;
        }
        while (i <= k) i += j - k;

    }
    return res;
}


int main() {
    string input;
    string all = "";
    string first = "";
    string result = "";
    ifstream in("littera.in");
    ofstream out("littera.out");
    int k;
    in >> k;
    while (in >> input) {
        all += input;
    }
    first = all.substr(0, all.length() / 2);
    result = all.substr(all.length() / 2, all.length() / 2);
    vector<int> difference;
    for (int i = 0; i < first.length(); ++i) {
        if (first[i] >= 'a' && first[i] <= 'z') {
            int num = result[i] - first[i];
            if (num <= 0)
                num += 26;
            difference.push_back(num);
        } else if (first[i] >= 'A' && first[i] <= 'Z') {
            int num = result[i] - first[i];
            if (num <= 0)
                num += 26;
            difference.push_back(num);
        }
    }

    vector<int> z(difference.size() + 5, 0);

    int suffix = z_function(difference, z);

    string itog = "";
    for (int i = 0; i < suffix; ++i) {
        itog += (char)('a' + difference[i] - 1);
    }

    int min = rotate(itog);
    for (int i = 0; i < itog.size(); ++i) {
        out << itog[(min + i)%itog.size()];
    }

    return 0;
}