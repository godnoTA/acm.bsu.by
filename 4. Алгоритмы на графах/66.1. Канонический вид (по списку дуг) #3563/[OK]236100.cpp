#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main ()
{
    ifstream fin ("input.txt");
    int N;
    fin >> N;
    std::vector <int> result (N, 0);

    for (int m = 0; m < N - 1; m++)
    {
        int u, v;
        fin >> u >> v;
        result [v - 1] = u;
    }

    ofstream fout ("output.txt");
    for (int i = 0; i < result.size (); i++)
    {
        fout << result [i] << " ";
    }
    fout << endl;
    fout.close ();
    return 0;
}
