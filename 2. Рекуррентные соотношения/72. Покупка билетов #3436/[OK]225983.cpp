#include <fstream>
#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <algorithm>

using namespace std;

int n;
int dp[1000000];
int x[1000000][4];

int main()
{
    ofstream cout("output.txt");
    ifstream cin("input.txt");
    cin>>n;
    for(int i=0;i<n;i++)
        dp[i] = 1000000000;
    dp[n+1] = dp[n+2] = 1000000000;
    for(int i=0;i<n;i++)
        cin>>x[i][1]>>x[i][2]>>x[i][3];
    for(int i=n-1;i>=0;i--)
        for(int j=1;j<=3;j++)
            dp[i] = min(dp[i], dp[i+j] + x[i][j]);
    cout<<dp[0]<<endl;
}
