#include <bitset>
#include <iostream>
#include <fstream>
int main() {
	std::ifstream fin("input.txt");
	std::ofstream fout("output.txt");
	long long value,count=0;
	fin >> value;
	std::string s = std::bitset< 64 >(value).to_string();
	for (int i = s.length()-1; i >= 0; --i) {
		if (s[i] == '1') {
			fout << s.length() - i - 1 << std::endl;
			count++;
		}
	}
	fin.close();
	if (count == 0)
		fout << "-1";
	fout.close();
	return 0;
}