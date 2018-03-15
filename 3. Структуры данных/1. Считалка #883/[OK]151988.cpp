#pragma comment(linker, "/STACK:500000000")
#include <vector>
#include <algorithm>
#include <map>
#include <string>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
#include <ctime>
#include <queue>
#include <set>
#include <iostream>
#include <stack>
#include <list>
#include <bitset>
#include <unordered_map>
//#include <WinDef.h> 
//#include <iterator> map<string,int>::iterator i;
using namespace std;
typedef long long ll;
typedef long double ld;
typedef vector<ll> vc;
typedef unsigned int ui;
#define mp make_pair
#define forn(i,n)	for(ll i = 0; i < n; i++)
#define forn2(i,n)	for(ll i = 1; i <= n; i++)
#define forn3(i,n,s) for(ll i = s; i <= n; i++)
#define forn4(i,n)	for(ll i = n; i >= 1; i--)
#define forn5(i,n)	for(ll i = n - 1; i >= 0; i--)
#define bad {cout<<"NO"<<endl;return 0;}
//#define push(x,g) {cin>>x;g.push_back(x);}
int F(const void* l, const void* r) {
	return (*(int*)l - *(int*)r);
}
const ll INF = 1000000000000000003;
const ll MAXN = 50000;
const ll dd = 1000000007;
ll n, m, l;
int a[10002];
deque<int> dq;
int main()
{
	//FILE **fp = new FILE*;
	//freopen_s(fp,"input.txt", "r", stdin);
	//freopen_s(fp,"output.txt", "w", stdout);
	ifstream in("in.txt");
	ofstream out("out.txt");
	in >> n >> m >> l;
	for (int i = 1; i <= n; i++) {
		dq.push_back(i);
	}
	ll u = 0;
	int n2 = n;
	while (dq.size() - 1) {
		u += m - 1;
		if (u >= dq.size()) {
			u %= dq.size();
		}
		dq.erase(dq.begin() + u);
		
	}
	ll ans = *dq.begin();
	out << ans << endl;
	//
	ans = l - (ans - 1);
	if (ans <= 0)
		ans += n;
	out << ans;
	return 0;
}