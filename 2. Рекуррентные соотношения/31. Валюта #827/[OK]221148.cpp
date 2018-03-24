#include <fstream>
#include <iostream>
 
using namespace std;

 
int out_table (int n, long double **table)
{
    for (int i=0; i<n; i++)
    {
        for (int j=0; j<n; j++) cout << table[i][j] << " ";
        cout << endl;
    };
    return 0;
}
 
 
int main ()
{
    int i,j,n;
  
    ifstream fin ("in.txt");
    fin >> n;
    //typedef long double ld;
        long double **course_table = new long double* [n];
    for (i=0; i<n; i++) course_table[i]=new long double [n];
 
    for (i=0; i<n; i++)
        for (j=0; j<n; j++)
            fin >> course_table[i][j];
 
  
    bool dmo = false;
    bool cd = true;
   
    while (cd && !dmo)
    {
        cd = false;
 
        for (i=0; i<n; i++)
            for (j=0; j<n; j++)
            {
                long double max = course_table[i][j];
                for (int k=0; k<n; k++)
                    if (course_table[i][k]*course_table[k][j]>max) 
                    {
                    	cd = true;
                    	max = course_table[i][k]*course_table[k][j];
                        
                    };
                course_table [i][j]=max;
                if ((i==j) && (max>1)) {dmo = true; break;};
            };
    }
 
    long double max=0;
    ofstream fout ("out.txt");
    for (i=0; i<n; i++) if (course_table[i][i]>max) max = course_table[i][i];
    //if (max==1) fout << "yes"; else fout << "no";
    if (max>1) fout << "yes"; else fout << "no";
    fout << endl;
 
    fin.close();
    fout.close();
    return 0;
}