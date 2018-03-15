#include <iostream>
#include <fstream>
#include <set>

using namespace std;

void main() {
    ifstream in("input.txt");

    set<long long> integers;
    while(!in.eof()) {
        long long a;
        in >> a;
        integers.insert(a);
    }

    long long sum = 0;

    for (long long integer : integers) {
        sum += integer;
    }

    ofstream out("output.txt");
    out << sum;

    out.close();
}