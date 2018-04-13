#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

struct Point{
	int x;
	int y;
	Point(int x, int y){
		this->x = x;
		this->y = y;
	}
};

void main(){
	int** matrix;
	int** result;
	int x1;
	int y1;
	int x2;
	int y2;
	int lines;

	ifstream fin("in.txt");
	fin >> lines;
	matrix = new int*[lines + 2];
	result = new int*[lines];
	for (int i = 0; i < lines + 2; i++){
		matrix[i] = new int[lines + 2];
	}
	for (int i = 0; i < lines; i++){
		result[i] = new int[lines];
	}
	fin >> x1 >> y1 >> x2 >> y2;
	x1--;
	y1--;
	for (int i = 1; i < lines + 1; i++){
		for (int j = 1; j < lines + 1; j++){
			fin >> matrix[i][j];
			result[i - 1][j - 1] = 0;
		}
	}
	for (int i = 0; i < lines + 2; i++){
		matrix[i][0] = matrix[i][lines + 1] = matrix[0][i] = matrix[lines + 1][i] = 0x7FFFFFFF;
	}

	queue<Point> myQueue;
	myQueue.push(Point(x1, y1));
	result[x1][y1] = matrix[x1+1][y1+1];
	while (!myQueue.empty()){
		int x = myQueue.front().x;
		int y = myQueue.front().y;
		if (result[x][y] > matrix[x + 1][y + 2] && result[x][y + 1] == 0){
			myQueue.push(Point(x, y + 1));
			result[x][y+1] = matrix[x + 1][y + 2];
		}
		if (result[x][y] > matrix[x + 2][y + 1] && result[x + 1][y] == 0){
			myQueue.push(Point(x + 1, y));
			result[x + 1][y] = matrix[x + 2][y + 1];
		}
		if (result[x][y] > matrix[x][y + 1] && result[x - 1][y] == 0){
			myQueue.push(Point(x-1, y));
			result[x - 1][y] = matrix[x][y + 1];
		}
		if (result[x][y] > matrix[x + 1][y] && result[x][y - 1] == 0){
			myQueue.push(Point(x, y-1));
			result[x][y - 1] = matrix[x + 1][y];
		}
		myQueue.pop();
	}

	ofstream fout("out.txt");
	for (int i = 0; i < lines - 1; i++){
		for (int j = 0; j < lines - 1; j++){
			fout << result[i][j] << " ";
		}
		fout << result[i][lines - 1] << "\n";
	}
	for (int j = 0; j < lines - 1; j++){
		 fout << result[lines - 1][j] << " ";
	}
	fout << result[lines - 1][lines - 1] << "";
}