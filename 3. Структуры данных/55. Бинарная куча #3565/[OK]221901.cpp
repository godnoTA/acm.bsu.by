#include <iostream>
#include <bits/stdc++.h>

using namespace std;
//int *arr, **arr2;
bool check(int n, int *arr)
{
    for(int i=1; 2*i<n; i++)
    {
        if (arr[i] > arr[2 * i] )
            return false;
        if(2*i+1<n)
            if( arr[i] > arr[2 * i + 1])
                return false;
    }
    return true;
}
int main()
{
    setlocale(LC_ALL, ".1251");
    srand((unsigned)time(NULL));
    const int Nmax = 2147483647;
    FILE *in,*out;
    int z = 1;
    int h;
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
            fscanf(in, "%d", &h);
    }
    else
    {
        cout << "Файл не открывается"<<endl;
        return -1;
    }
    int *arr;
    arr = new int [h+1];
    while (!feof(in))
    {
        fscanf(in, "%d", &arr[z++]);
    }
    for(int i=1; i<=h; i++)
        cout<<arr[i]<<endl;
    out = fopen("output.txt", "w");
    if(check(h+1, arr))
        fout<<"Yes"<<endl;
    else
        fout<<"No"<<endl;
    fclose(in);
    return 0;
}
