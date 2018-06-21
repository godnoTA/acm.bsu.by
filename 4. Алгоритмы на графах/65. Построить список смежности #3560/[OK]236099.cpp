#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main ()
{
    ifstream fin ("input.txt");
    int N, M;
    fin >> N >> M;
    std::vector <std::vector <int> > lists (N, vector <int> ());

    while (M > 0)
    {
        int u, v;
        fin >> u >> v;
        lists [u - 1].push_back (v);
        lists [v - 1].push_back (u);
        --M;
    }

    ofstream fout ("output.txt");
    for (int i = 0; i < N; i++)
    {
        fout << lists [i].size () << " ";
        for (int j = 0; j < lists [i].size (); j++)
        {
            fout << lists [i] [j] << " ";
        }
        fout << endl;
    }
    fout.close ();
    return 0;
}