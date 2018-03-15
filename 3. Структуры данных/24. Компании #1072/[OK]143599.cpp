#include <bits/stdc++.h>
using namespace std;
 
int input[200][200] = {{0}};
bool visited[200];
int sum[200];
int n = 0;
 
void dfs(int index)
{
    if (visited[index]) {
        return;
    }
    visited[index] = true;
 
    for (int i = 1; i <= n; ++i) {
        sum[i] = sum[i] + input[index][i]; // sum up 'the influence' from all 'owning' nodes
        if (sum[i] > 50) {
            dfs(i); // kinda update
        }
    }
}
 
int main() {
freopen("input.txt","r",stdin); freopen("output.txt","w",stdout);
    int dominator, subject, weight;
    while (cin >> dominator) {
        cin >> subject >> weight;
        if (dominator > n) n = dominator;
        if (subject > n) n = subject;
        input[dominator][subject] = weight;
    }
   
    for (int i = 1; i <= n; ++i) {
        fill_n(visited + 1, n, false);
        fill_n(sum + 1, n, 0);
        dfs(i);
        for (int ourCompany = 1; ourCompany <= n; ++ourCompany) {
            if (sum[ourCompany] > 50 && ourCompany != i) {
                cout << i << " " << ourCompany << endl;
            }
        }
    }
   
    return 0;
}