#include <iostream>
#include <cstring>
#include <queue>
#include <fstream>
#include <algorithm>
 
using namespace std;
 
ifstream in_txt("input.txt");
ofstream out_txt("output.txt");

int size; int sum = 0;
int graph[100][100]; bool visit[100];
queue<int> queue1;
 
void input1() {
    in_txt >> size;
	int i = 0;
    while(i < size) {
	int j = 0;
        while(j < size) {
            in_txt >> graph[i][j];
            sum = sum + graph[i][j];
	    j++;
        }
	i++;
    }
}
 
void foo(bool *visit, int key) {
    queue1.push(key);
    visit[key] = true;
    while (!queue1.empty()) {
        key = queue1.front();
        queue1.pop();
	int i = 0;
        while(i < size) {
            if ((!visit[i]) && (graph[key][i] == 1)) {
                queue1.push(i);
                visit[i] = true;
            }
	    i++;
	}
    }
}
 
 
int main()
{
    input1();
    memset(visit, 0, sizeof visit);
    foo(visit, 0);

	int number = 0;	int i = 0;
    while(i < size) {
        if (!visit[i]) {
            number++;
            break;
        }
	i++;
    }

    if ((size - 1 == (int)(sum / 2)) && (number == 0))
        out_txt << "Yes";
    else out_txt << "No";
    return 0;
}