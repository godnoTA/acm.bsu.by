#include <fstream>
#include <iostream>
#include <string>

using namespace std;

string TransferDecimalToAny(long long int number, const unsigned int &base)
{
    const char *DIGITS = "0123456789abcdefghijklmnopqrstuvwxyz";
    bool FlagInverse = false;
    string reversString, stringInBase;

    if (base < 2 || base > 36)
    {
       
        return "-1";
    }
    if (number < 0)
    {
        FlagInverse = true;
        number *= -1;
    }

    stringInBase += DIGITS[number % base];
    number = number / base;
    while (number)
    {
        stringInBase += DIGITS[number % base];
        number = number / base;
    }

    if (FlagInverse == true)
        reversString = "-";

    for (int i = stringInBase.length() - 1; i >= 0; i--)
        reversString += stringInBase[i];

    return reversString;
}


int main ()
{

    ifstream fin ("input.txt");
    ofstream fout ("output.txt");

	long long int n;

	fin >> n;

	string s;

	s = TransferDecimalToAny(n, 2).c_str();
	int size, size1;
	size = s.size();
	size1 = size - 1;
	for (int i = size - 1; i >= 0; i--)
	{
		if (s.at(i) == '1')
			fout << size1 - i << endl;
	}

	fin.close();
	fout.close();

	return 0;
}