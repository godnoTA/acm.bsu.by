#include <algorithm>
#include <random>
#include <vector>
#include <iostream>
#include <fstream>
#include <set>
#include <map>

using namespace std;

int main() {
    ifstream in("input.txt");
    map <long long, long long> n2;
    long long sum = 0;
    long long a;
    while(in) {
        in >> a;
        n2[a]++;
        if(n2[a] == 1) {
            sum += a;
        }
    }
    ofstream out("output.txt");
    out << sum;
    out.close();
    in.close();
    return 0;
}
