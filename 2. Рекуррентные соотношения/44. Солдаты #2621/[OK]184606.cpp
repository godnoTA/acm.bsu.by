#include <stdio.h> 
#include <stdlib.h> 
#include <fstream> 
#include <string> 
#include <algorithm> 
#include <iostream> 
#include <queue> 
#include <cstring> 
#include <set> 
#include <iterator> 
#include <limits.h> 
#include <queue> 
#pragma comment(linker, "/STACK:67108864") 
#pragma warning(disable : 4996) 
#define _CRT_SECURE_NO_WARNINGS 
using namespace std; 
 
int function(long N) { 
if (N == 1)
    return 0; 
if (N == 2)
    return 1; 
if (N % 2 == 0)
    return 2 * function(N / 2); 
return function(N / 2) + function(N / 2 + 1); 
} 

int main() { 
    FILE * in = fopen("input.txt", "r");
    ofstream fout("output.txt"); 
    int N; 
    fscanf(in, "%d", &N); 
    int ans = function(N);
    fout<<ans; 
    fout.close(); 
    return 0; 
}