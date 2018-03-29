#include <iostream>
#include <bits/stdc++.h>
using namespace std;

size_t CountOnes(unsigned long long n)
{
    size_t i(0);
    for(; n; ++i) n&=n-1;
    return i;
}
long long sochet(int n, int k)
{
    if(k>n)
        return 0;
    long long B[n+1][n+1];
    for(int i=0; i<=n; i++)
    {
        B[i][0]=1;
        B[i][i]=1;
        for(int j=1; j<i; j++)
        {
            B[i][j]=B[i-1][j-1]+B[i-1][j];
        }
    }
    return B[n][k];
}
int largest_power(long long n)
{
    if(n==0)
        return 0;
    int i=0;
    while(n-pow(2,i)>=0)
        i++;
    return i-1;
}
int main()
{
    long long lower = 735627, higher = 67249026;
    int power = 20, lowerdigits = 0, higherdigits = 0;
    long long count = 0;
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    if (!fin)
    {
        cout<<"Cannot read!"<<endl;
        return -1;
    }
    fin>>lower;
    fin>>higher;
    fin>>power;
    if(power==0)
    {
        cout<<count<<endl;
        fout<<count<<endl;
        fout.close();
        return 0;
    }
    higherdigits=largest_power(higher);
    lowerdigits=largest_power(lower);
    int step=power-1;
    lower--;
    count = sochet(higherdigits, power);
    while(step>=0)
    {
        higher=higher-pow(2, higherdigits);
        higherdigits=largest_power(higher);
        //if(step==1)
        //    count+=sochet(higherdigits+1, step);
        //else
        count+=sochet(higherdigits, step);
        step--;
    }
    step=power-1;
    count-=sochet(lowerdigits, power);
    while(step>=0)
    {
        lower=lower-pow(2, lowerdigits);
        lowerdigits=largest_power(lower);
        count-=sochet(lowerdigits, step);
        step--;
    }
    cout<<count<<endl;
    fout<<count<<endl;
    fout.close();
    return 0;
}
