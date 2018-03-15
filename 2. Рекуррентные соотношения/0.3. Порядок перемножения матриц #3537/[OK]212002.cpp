#include <iostream>
#include <fstream>

using namespace std;

class MatrixSize{
public:
    int k=0;
    int m=0;

    MatrixSize(){}

    MatrixSize(int a, int b)
    {
        k=a;
        m=b;
    }
};

int mQOORecursiveHelper(int** matrixOfMatrixes, MatrixSize* mass, int& n, int i, int j)
{
    if (i == j)
    {
        return 0;   
    }
    
    if (j == i + 1)
    {
        matrixOfMatrixes[i-1][j-1] = mass[i-1].k * mass[i-1].m * mass[i].m;
        return matrixOfMatrixes[i-1][j-1];
    }
    
    int minimum= mQOORecursiveHelper(matrixOfMatrixes, mass, n, i + 1, j) + mass[i - 1].k * mass[i - 1].m * mass[j - 1].m;
    
    for (int q = i + 1; q < j; ++q)
    {
        if(matrixOfMatrixes[i - 1][q - 1]==0)
            mQOORecursiveHelper(matrixOfMatrixes, mass, n, i, q);

        if(matrixOfMatrixes[q][j - 1]==0) 
            mQOORecursiveHelper(matrixOfMatrixes, mass, n, q + 1, j);

        minimum = std::min(minimum, matrixOfMatrixes[i-1][q-1] + matrixOfMatrixes[q][j-1] + mass[i-1].k*mass[q-1].m*mass[j-1].m);
    }
    
    matrixOfMatrixes[i-1][j-1] = minimum;
    
    return minimum;
}

int minimumQuantityOfOperations(MatrixSize* array, int& n)
{
    int** matrixOfMatrixes = new int*[n];

    for (int i = 0; i < n; ++i)
    {
        matrixOfMatrixes[i] = new int[n];

        for (int j = 0; j < n; ++j)
            matrixOfMatrixes[i][j] = 0;
    }

    return mQOORecursiveHelper(matrixOfMatrixes, array, n, 1, n);
}

int main()
{
    ifstream fin("input.txt");
    ofstream fout("output.txt");

    int n, k, m;
    fin >> n;
    
    MatrixSize* array = new MatrixSize[n];
    
    for (int i = 0; i < n; ++i)
    {
        fin >> k;
        fin >> m;
        MatrixSize temp = MatrixSize(k, m);
        array[i] = temp;
    }

    fout<<minimumQuantityOfOperations(array, n);
}