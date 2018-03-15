#include <iostream>
#include <set>
#include <numeric>
#include <fstream>

int main() {
    std::set<int> numbers;
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int number;
    while (input >> number) {
        numbers.insert(number);
    }
    output << std::accumulate(numbers.begin(), numbers.end(), (long long)0);
}