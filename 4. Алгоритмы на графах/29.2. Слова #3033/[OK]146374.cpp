#include <iostream>
#include <string>
#include <cstdio>
#include <vector>
#include <string>
#include <sstream>
#include <iostream>
#include <fstream>
#include <stack>
#include <set>
#include <cmath>
#include <map>
#include <algorithm>
using namespace std;

#define MAXN 30

int matrix[MAXN][MAXN];
int matrix_tran[MAXN][MAXN];
vector<bool> visited(MAXN, false);
vector<int> in_deg(MAXN, 0);
vector<int> out_deg(MAXN, 0);
vector<int> to_print;
int m;
int global_start = -10;

void walk(int v) {
    for(int i = 0; i < MAXN; i++) {
        if (matrix[v][i] > 0) {
            --matrix[v][i];
            walk(i);
            //break;
        }
    }
    to_print.push_back(v);
}

void dfs(int v) {
    visited[v] = true;
    for (int i = 0; i < MAXN; ++i) {
        if (matrix[v][i] != 0 && !visited[i])
            dfs(i);
    }
}

void dfs_tran(int v) {
    visited[v] = true;
    for (int i = 0; i < MAXN; ++i) {
        if (matrix_tran[v][i] != 0 && !visited[i])
            dfs_tran(i);
    }
}

bool is_SC() {
    fill(visited.begin(), visited.end(), false);
    int gstart = 0;
    for (int start = 0; start < MAXN; ++start) {
        for (int i = 0; i < MAXN; ++i)
            if (matrix[start][i] > 0) {
                gstart = start;
                break;
            }
        if (gstart != 0)
            break;
    }
    global_start = gstart;
    dfs(gstart);
    for (int i = 0; i < MAXN; ++i)
        if (visited[i] == false && (in_deg[i] > 0 || out_deg[i] > 0))
            return false;
    fill(visited.begin(), visited.end(), false);
    dfs_tran(gstart);
    for (int i = 0; i < MAXN; ++i)
        if (visited[i] == false && (in_deg[i] > 0 || out_deg[i] > 0))
            return false;
    return true;
}

int main(int argc, const char * argv[])
{
    std::ios::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    vector< vector<string> > ans(10000);
    string ws;
    getline(cin, ws);
    string str = "az";
    m = stoi(ws); // number of words
    for (int i = 0; i < m; ++i) {
        std::getline(cin, ws); // read a word
        int start = int(ws[0]);
        int finish = int(ws[ws.size() - 1]);
        if (start < int(str[0]) || start > int(str[1])) {
            cout << "No";
            return 0;
        }
        if (finish < int(str[0]) || finish > int(str[1])) {
            cout << "No";
            return 0;
        }
        int num = start * 30 + finish;
        ans[num].push_back(ws);
        start -= 96;
        finish -= 96;
        //cout << start << " " << ws[0] << endl;
        //cout << start << " " << finish << endl;
        in_deg[finish] += 1;
        out_deg[start] += 1;
        matrix[start][finish] += 1;
        matrix_tran[finish][start] += 1;
        
    }
    bool flag = true; // in_deg == out_deg for all v in V
    for (int i = 0; i < MAXN; ++i) {
        if (in_deg[i] != out_deg[i]) {
            flag = false;
            break;
        }
    }
    if (is_SC() && flag) {
        cout << "Yes" << endl;
        if (global_start == -10) {
            while(true) {;}
        }
        stack<int> st;
        st.push(global_start);
        while (!st.empty()) {
            int v = st.top();
            int i = 0;
            for (i = 0; i < MAXN; ++i)
                if (matrix[v][i])
                    break;
            if (i == MAXN) {
                to_print.push_back(v);
                st.pop();
            }
            else {
                --matrix[v][i];
                st.push(i);
            }
        }
//        for (int ans : to_print) {
//            cout << ans << endl;
//        }
//        cout << endl << endl;
        
        // cout << to_print.size() << endl << endl << endl;
//        for (pair<int, string> an : ans) {
//            cout << an.first << endl;
//        }
        for (unsigned long j = to_print.size() - 1; j >= 1; --j) {
            int for_search = (to_print[j] + 96) * 30 + to_print[j - 1] + 96;
            cout << ans[for_search][ans[for_search].size()-1] << endl;
            ans[for_search].erase(ans[for_search].end() - 1);
        }
    }
    else {
        cout << "No";
    }
    return 0;
}