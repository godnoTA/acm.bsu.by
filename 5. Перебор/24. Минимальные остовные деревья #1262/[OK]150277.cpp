#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int n, m;
vector<int> otkuda, kuda, cost;
int ans;
vector<int> p, num;
int kolE = 0;
vector<int> res;
vector<vector<int>> allMST;

int dsu_get(int v){
        return (v == p[v]) ? v : (p[v] = dsu_get(p[v]));
}

void dsu_unite(int a, int b){
        a = dsu_get(a);
        b = dsu_get(b);
        /*if (rand() & 1)
                swap(a, b);*/
        if (a != b){
                p[a] = b;
        }
}

int mst(vector<int> on, vector<int> off){
        int locAns = 0;
        res.clear();
        kolE = 0;
        p.clear();
        for (int i = 0; i <= n; i++){
                p.push_back(i);
        }
        for (int i = 0; i < on.size(); i++){
                kolE++;
                dsu_unite(otkuda[on[i]], kuda[on[i]]);
                locAns += cost[on[i]];
        }
        for (int i = 0; i < m; i++){
                if (dsu_get(otkuda[i]) != dsu_get(kuda[i]) && off[i] == 0) {
                        locAns += cost[i];
                        res.push_back(i);
                        dsu_unite(otkuda[i], kuda[i]);
                        kolE++;
                }
        }
        if (kolE == n - 1 && (locAns == ans || ans == 0)){
                vector<int> cur;
                for (int i = 0; i < on.size(); i++){
                        cur.push_back(num[on[i]] + 1);
                }
                for (int i = 0; i < res.size(); i++){
                        cur.push_back(num[res[i]] + 1);
                }
                allMST.push_back(cur);
        }
        return locAns;
}

void rec(vector<int> on, vector<int> off, vector<int> open){
        for (int i = 0; i < open.size(); i++){
                if (i){
                        off[open[i-1]] = 0;
                        on.push_back(open[i - 1]);
                }
                off[open[i]] = 1;
                if (mst(on, off) == ans){
                        rec(on, off, res);
                }
        }
}

void qs(int l, int r){
        int i = l; 
        int j = r;
        int mid = cost[(l + r) / 2];
        while (i < j){
                while (cost[i] < mid) i++;
                while (cost[j] > mid) j--;
                if (i > j) break;
                int t = cost[i]; cost[i] = cost[j]; cost[j] = t;
                t = otkuda[i]; otkuda[i] = otkuda[j]; otkuda[j] = t;
                t = kuda[i]; kuda[i] = kuda[j]; kuda[j] = t;
                t = num[i]; num[i] = num[j]; num[j] = t;
                i++; j--;
        }
        if (i < r) qs(i, r);
        if (l < j) qs(l, j);
}

int main(){
        fin >> n >> m;
        for (int i = 0; i < m; i++){
                int a, b, c;
                fin >> a >> b >> c;
                otkuda.push_back(a);
                kuda.push_back(b);
                cost.push_back(c);
                num.push_back(i);
        }

        //sort(cost.begin(), cost.end());
        qs(0, m - 1);

        vector<int> inc;
        vector<int> block(m,0);

        ans = mst(inc, block);
        rec(inc, block, res);
        fout << allMST.size() << endl;
        for (int i = 0; i < allMST.size(); i++){
                for (int j = 0; j < allMST[i].size(); j++){
                        fout << allMST[i][j] << ' ';
                }
                fout << "\n";
        }
        return 0;
}