#include <bits/stdc++.h>

using namespace std;

int main()
{
    const int Nmax = 2147483647;
    FILE *in,*out;
    int z = 0;
    int h;
    long long sum=0;
    set<int> arr;
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    if (!fin)
    {
        cout << "Файл не найден" << endl;
        return -1;
    }
    if ((in=fopen("input.txt", "r"))!=NULL)
    {
        if (feof(in))
        {
            cout << "Файл пустой" << endl;
            return -1;
        }
        else
        {
            while (!feof(in))
            {
                fscanf(in, "%d", &h);
                arr.insert(h);
            }
        }
    }
    else
    {
        cout << "Файл не открывается"<<endl;
        return -1;
    }
    out = fopen("output.txt", "w");
    string s;
    char c;
    for(set<int>::iterator it = arr.begin(); it != arr.end(); it++)
        sum+=*it;
    fout << sum << endl;
    if ( sum == 0 )
        s = "0";
    while(sum)
    {
        c=(sum%10)+'0';
        s=c+s;
        sum=sum/10;
    }
    const char* str;
    str = s.c_str();
    fclose(in);
    return 0;
}
