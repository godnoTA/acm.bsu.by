#include <cassert>
#include <cmath>
#include <vector>
#include <algorithm>
#include <limits>
#include <iostream>
#include <fstream>

using namespace std;

struct Point2D { int x, y; };

static vector<vector<double>> solutions;

double distance(const Point2D &p1, const Point2D &p2)
{
	Point2D delta = { p2.x - p1.x, p2.y - p1.y };
	return sqrt((double)delta.x * delta.x + (double)delta.y * delta.y);
}

double findMindDSum(const Point2D points[], unsigned n, unsigned i, unsigned j)
{
	assert(i < j && j < n);

	if (j == i + 1)
		return 0;

	if (solutions[i][j] >= 0)
		return solutions[i][j];


	double weightIJ = i > 0 || j < n - 1 ? distance(points[i], points[j]) : 0;

	double minWeight = numeric_limits<double>::max();
	for (unsigned k = i + 1; k < j; ++k)
	{
		double weight = findMindDSum(points, n, i, k) + findMindDSum(points, n, k, j);
		minWeight = min(minWeight, weight);
	}


	double weight = weightIJ + minWeight;

	solutions[i][j] = weight;

	return weight;
}

double findMindDSum(const Point2D points[], unsigned n)
{
	return findMindDSum(points, n, 0, n - 1);
}

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int n = 0;
	fin >> n;

	Point2D *points = new Point2D[n];

	for (int i = 0; i < n; i++) {
		int x = 0;
		int y = 0;
		fin >> x;
		fin >> y;
		points[i].x = x;
		points[i].y = y;
	}

	solutions.resize(n);
	for (auto &s : solutions) s.resize(n, -1);

	fout << findMindDSum(points, n) << std::endl;
	return 0;
}