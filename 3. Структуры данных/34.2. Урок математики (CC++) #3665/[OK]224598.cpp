#include <iostream>
#include <fstream>
#include <queue>
#include <vector>

using namespace std;

int* numb;
int* kol_sum;
int k = 0;

struct idx
{
    int i;
};

bool operator <(const idx& a, const idx& b)
{
    int si = numb[a.i] + numb[kol_sum[a.i]];
    int sj = numb[b.i] + numb[kol_sum[b.i]];
    return si >= sj;
}

int main() {
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    priority_queue<idx> cur_id, wait_id;
    int n, a;
    fin >> n;
    numb = new int[n];
    kol_sum = new int[n];
    fin >> a;
    numb[0] = a / 2;
    fout << a / 2 << endl;
    kol_sum[0] = 1;
    k++;
    cur_id.push({ 0 });
    while (k < n) {
        fin >> a;
        if (cur_id.empty() || kol_sum[cur_id.top().i] >= k || numb[cur_id.top().i] + numb[kol_sum[cur_id.top().i]] > a)
        {
            numb[k] = a - numb[0];
            fout << numb[k] << endl;
            kol_sum[k] = 1;
            k++;
            cur_id.push({ k - 1 });
            while (!wait_id.empty()) {
                int top =  wait_id.top().i;
                wait_id.pop();
                cur_id.push({ top });
            }
        }
        else {
            int top =  cur_id.top().i ;
            cur_id.pop();
            ++kol_sum[top];
            if (kol_sum[top] < k)
                cur_id.push({top });
            else
                wait_id.push({ top });
        }
    }
    delete[] numb;
    delete[] kol_sum;
    fout.close();
}