#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <fstream>

using namespace std;
bool poisk (int arr[], int n, int r)
{
    for (int j=0; j < n; j++)
        if(r==arr[j])
            return false;
    return true;
}
int main()
{
    setlocale(LC_ALL, ".1251");
    srand((unsigned)time(NULL));
    const int Nmax = 2147483647;
    FILE *in,*out;
    int z = 0;
    int h;
    long long sum=0;
    int arr[10000];
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    if (!fin)
    {
        cout<<"Файл не найден"<<endl;
        return -1;
    }
    if ((in=fopen("input.txt", "r"))!=NULL)
    {
        if (feof(in))
        {
            cout<<"Файл пустой"<<endl;
            return -1;
        }
        else
        {

            while (!feof(in)&&z<Nmax)
            {
                fscanf(in, "%d", &h);
                if(poisk(arr,z,h))
                {
                    arr[z]=h;
                    sum+=h;
                    z++;
                }
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
    fout<<sum<<endl;
    if(sum==0)
        s="0";
    while(sum)
    {
        c=(sum%10)+'0';
        s=c+s;
        sum=sum/10;
    }
    const char* str;
    str=s.c_str();
    fclose(in);
    return 0;
}
