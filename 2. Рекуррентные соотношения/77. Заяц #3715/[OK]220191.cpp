#include <fstream>

using namespace std;

int n;
long long dp[11111111];

int main()
{
ifstream in("input.txt");
ofstream out("output.txt");
in >> n;
dp[0] = 1;
dp[1] = 1;
for(int i=2; i<=n;++i)
{
dp[i]=(dp[i-1]+dp[i-2])%1000000007;
}
out << dp[n] << endl;
}
