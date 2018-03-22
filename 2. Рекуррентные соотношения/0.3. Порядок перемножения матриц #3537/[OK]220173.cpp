#include <iostream>
#include <bits/stdc++.h>

using namespace std;
//int *arr, **arr2;
int mult(int k, int n, int *arr, int **arr2)
{
    if (arr2[k][n] == -1)
    {
        if (k == n - 1)
            arr2[k][n] = 0;
        else
        {
            arr2[k][n] = 1000000000;
            for (int i = k + 1; i <= n - 1; i++)
                arr2[k][n] = min(arr2[k][n], arr[k] * arr[i] * arr[n] + mult(k, i, arr, arr2) + mult(i, n, arr, arr2));
        }
    }
    return arr2[k][n];
}
int main()
{
    setlocale(LC_ALL, ".1251");
    srand((unsigned)time(NULL));
    const int Nmax = 2147483647;
    FILE *in,*out;
    int z = 0;
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
    int *uuu;
    int **arr2;
    arr = new int [h+1];
    uuu = new int [h+1];
    arr2 = new int *[h+1];
    for(int i=0; i<=h; i++)
        arr2[i]= new int [h+1];
    for(int i=0; i<=h; i++)
        for(int j=0; j<=h; j++)
            arr2[i][j]=-1;
    while (!feof(in))
    {
        fscanf(in, "%d", &arr[z]);
        fscanf(in, "%d", &uuu[z++]);
    }
    arr[h]=uuu[h-1];
    for(int i=0; i<=h; i++)
        cout<<arr[i]<<endl;
    out = fopen("output.txt", "w");
    cout<<mult(0, h, arr, arr2)<<endl;
    fout<<mult(0, h, arr, arr2)<<endl;
    fclose(in);
    return 0;
}
