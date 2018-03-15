#include <fstream>
#include <stack>
#include <set>
#include <map>
 
using namespace std;
 
int main()
{
    ifstream in("input.txt");
    ofstream out("output.txt");
 
    int N, M, k, *x, *D, buf = 0, **matr;
    bool **met;
 
    in >> N >> M >> k;
 
    x = new int[k];
    matr = new int*[N + 2];
    met = new bool*[N + 2];
    D = new int[M + 2];
 
    for (int i = 0; i < k; i++)
    {
        in >> x[i];
    }
   
    for (int i = 0; i < M + 2; i++)
    {
        D[i] = 0;
    }
 
    set<int> Y = set<int>();
 
    for (int i = 0; i < k; i++)
    {
        in >> buf;
        Y.insert(buf);
    }
 
    for (int i = 0; i < N + 2; i++)
    {
        matr[i] = new int[M + 2];
        met[i] = new bool[M + 2];
    }
   
    for (int i = 0; i < M + 2; i++)
    {
        matr[0][i] = 1;
        met[0][i] = false;
        matr[N + 1][i] = 1;
        met[N + 1][i] = false;
    }
 
    for (int i = 0; i < N + 2; i++)
    {
        matr[i][0] = 1;
        met[i][0] = false;
        matr[i][M + 1] = 1;
        met[i][M + 1] = false;
    }
 
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            in >> matr[i][j];
            met[i][j] = false;
        }
    }
 
    stack<pair<int, int>> Stack = stack<pair<int,int>>();
 
    int count = 0;
 
    for (int i = 0; i < k; i++)
    {
        Stack.push(pair<int, int>(1, x[i]));
 
        int f = x[i];
 
        met[1][f] = true;
 
        for (int j = 1; true;)
        {
            if (matr[j - 1][f] == 0 &&
                !met[j - 1][f] && (met[j][f + 1] || matr[j][f + 1] == 1))
            {
                j--;
                Stack.push(pair<int, int>(j, f));
                met[j][f] = true;
            }
            else if (((matr[j][f - 1] == 0 &&   !met[j][f - 1])  ||
                    (j == N  && Y.find(f - 1) != Y.end())) && (met[j - 1][f] || matr[j - 1][f] == 1))
            {
                f--;
                Stack.push(pair<int, int>(j, f));
                met[j][f] = true;
            }
            else if (((matr[j + 1][f] == 0 &&   !met[j + 1][f])   ||
                    (j == N - 1 && Y.find(f) != Y.end())) && (met[j][f - 1] || matr[j][f - 1] == 1))
            {
                j++;
                Stack.push(pair<int, int>(j, f));
                met[j][f] = true;
            }
            else if (((matr[j][f + 1] == 0 &&   !met[j][f + 1]) ||
                    (j == N && Y.find(f + 1) != Y.end())) && (met[j + 1][f] || matr[j + 1][f] == 1))
            {
                f++;
                Stack.push(pair<int, int>(j, f));
                met[j][f] = true;
            }
            else
            {
                Stack.pop();
                if (Stack.empty())
                    break;
                j = Stack.top().first;
                f = Stack.top().second;
            }
 
            if (j == N )
            {
                if (Y.find(Stack.top().second) != Y.end())
                {
                    count++;
                    break;
                }
            }
        }
 
        if (!Stack.empty())
        {
            if (D[Stack.top().second] > Stack.size())
            {
                Stack.pop();
            }
            else
            {
                D[Stack.top().second] = Stack.size();
            }
 
            while (!Stack.empty())
            {
                matr[Stack.top().first][Stack.top().second] = i + 2;
                Stack.pop();
            }
        }
    }
   
    out << count << "\n";
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j < M; j++)
        {
            out << matr[i][j] << " ";
        }
        out << matr[i][M];
        out << "\n";
    }
 
    in.close();
    out.close();
    return 0;
}