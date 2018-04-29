#include <fstream>
long long n;
int main() {
    std::ifstream in("input.txt");
    std::ofstream out("output.txt");
    in >> n;
    for(int i = 0; i < 64; ++i)
        if((n >> i) & 1)
            out << i << std::endl;
}
