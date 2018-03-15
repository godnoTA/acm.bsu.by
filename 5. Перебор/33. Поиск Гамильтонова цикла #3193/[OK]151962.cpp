#include <fstream>
#include <iomanip>
#include <vector>
#include <queue>
#include <limits>
#include <algorithm>

#include <cmath>
#include <memory.h>
#include <climits>
#include <limits.h>
#include <ctime>

using namespace std;
vector<int> finalAnswersX;
vector<int> finalAnswersY;
vector<int> tempoAnswersX;
vector<int> tempoAnswersY;
int minSteps = std::numeric_limits<int>::max();;
typedef long long ll;

const ll INF = LONG_MAX;

ll optimalLength = INF;

const int maxN = 100;

ll ans[maxN], cur[maxN], a[maxN][maxN], n;

ll xN[maxN], yN[maxN];


bool used[maxN];
vector <int> r;

void calc(vector<vector<int>> arr, int n, vector<int> xP, vector<int> yP, int k, vector<int> tX, vector<int> tY) {
	//
	//copy matrix
	//sub min elements from rows and columns until get zero everywhere
	//and add this min elemens to steps
	//
	vector<int> x;
	vector<int> y;
	for (int i = 0; i < xP.size(); i++) {
		x.push_back(xP[i]);
	}
	for (int i = 0; i < yP.size(); i++) {
		y.push_back(yP[i]);
	}
	vector<vector<int>> ranges;
	for (int i = 0; i < n; i++) {
		vector<int> temp;
		for (int j = 0; j < n; j++) {
			temp.push_back(arr[j][i]);
		}
		ranges.push_back(temp);
	}
	int steps = k;
	for (int j = 0; j < n; j++) {
		int min = std::numeric_limits<int>::max();
		for (int i = 0; i < n; i++) {
			if (ranges[i][j] < min)
				min = ranges[i][j];
		}
		for (int i = 0; i < n; i++) {
			ranges[i][j] -= min;
		}
		steps += min;
	}
	for (int i = 0; i < n; i++) {
		int min = numeric_limits<int>::max();
		for (int j = 0; j < n; j++) {
			if (ranges[i][j] < min) {
				min = ranges[i][j];
			}
		}
		if (min != 0) {
			for (int j = 0; j < n; j++) {
				ranges[i][j] -= min;
			}
			steps += min;
		}
	}

	//
	//find zero and store his coord and value(min(rowsdata*) + min(columndata*))
	//*skip zero keeper cell
	//find max value (maxZero)
	//
	vector<int> zerosX;
	vector<int> zerosY;
	vector<int> zerosVal;
	int maxZero = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (ranges[j][i] == 0) {
				zerosX.push_back(i);
				zerosY.push_back(j);
				int minX = std::numeric_limits<int>::max();
				int minY = std::numeric_limits<int>::max();
				for (int g = 0; g < n; g++) {
					if (ranges[j][g] < minY && g != i)
						minY = ranges[j][g];
					if (ranges[g][i] < minX && g != j)
						minX = ranges[g][i];
				}
				zerosVal.push_back(minX + minY);
				if (minX + minY > maxZero)
					maxZero = minX + minY;
			}
		}
	}
	//
	//get real* coords of zero's cell (iZ,jZ)
	//store real* coords to tempo lists
	//if its last call of function and most effective way, store coords to finalValue lists
	//if its not then  create new (n-1) matrix and call calc
	//
	if (n == 2) {
		if (steps >= minSteps)
			return;
		finalAnswersX.clear();
		finalAnswersY.clear();
		for (int i = 0; i < tX.size(); i++) {
			finalAnswersX.push_back(tX[i]);
		}

		for (int i = 0; i < tY.size(); i++) {
			finalAnswersY.push_back(tY[i]);
		}

		for (int i = 0; i < zerosVal.size(); i++) {
			finalAnswersX.push_back(y[zerosY[i]]);
			finalAnswersY.push_back(x[zerosX[i]]);
		}
		minSteps = steps;
		return;
	}

	for (int g = 0; g < zerosVal.size(); g++) {
		if (zerosVal[g] == maxZero) {
			int iZ = zerosX[g];
			int jZ = zerosY[g];
			tempoAnswersX.clear();
			tempoAnswersY.clear();
			for (int i = 0; i < tX.size(); i++) {
				tempoAnswersX.push_back(tX[i]);
			}
			for (int i = 0; i < tX.size(); i++) {
				tempoAnswersY.push_back(tY[i]);
			}
			tempoAnswersX.push_back(y[jZ]);
			tempoAnswersY.push_back(x[iZ]);
			vector<vector<int>> arrNew;
			for (int i = 0; i < n - 1; i++) {
				vector<int> temp;
				for (int j = 0; j < n - 1; j++) {
					if (i >= iZ && j >= jZ)
						temp.push_back(ranges[j + 1][i + 1]);
					else {
						if (i >= iZ)
							temp.push_back(ranges[j][i + 1]);
						else {
							if (j >= jZ) {
								temp.push_back(ranges[j + 1][i]);
							}
							else
								temp.push_back(ranges[j][i]);
						}

					}

				}
				arrNew.push_back(temp);
			}
			vector<int> xx;
			vector<int> yy;
			for (int i = 0; i < x.size(); i++) {
				xx.push_back(x[i]);
			}
			for (int i = 0; i < y.size(); i++) {
				yy.push_back(y[i]);
			}
			auto it = xx.begin();
			for (int i = 0; i < iZ; i++) {
				it++;
			}
			xx.erase(it);
			it = yy.begin();
			for (int i = 0; i < jZ; i++) {
				it++;
			}
			yy.erase(it);
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n - 1; j++) {
					bool isBig = false;
					for (int l = 0; l < n - 1; l++) {
						if (arrNew[i][l] > 1e9 || arrNew[l][j] > 1e9) {
							isBig = true;
							break;
						}
					}
					if (!isBig)
						arrNew[i][j] = std::numeric_limits<int>::max();
				}
			}
			calc(arrNew, n - 1, xx, yy, steps, tempoAnswersX, tempoAnswersY);
			tempoAnswersX.clear();
			tempoAnswersY.clear();
		}
	}
}
void dfs(int v, int cnt, ll len) {
	if (len > optimalLength) {
		return;
	}
	if (cnt == n) {
		if (len + a[v][1] < optimalLength) {
			optimalLength = len + a[v][1];
			for (int i = 1; i <= n; ++i) ans[i] = cur[i];
		}
		return;
	}
	for (int j = 0; j< n; j++)
	{
		int i = r[j];
		if (!used[i]) continue;
		used[i] = false;
		cur[cnt + 1] = i;
		dfs(i, cnt + 1, len + a[v][i]);
		used[i] = true;
		cur[cnt + 1] = 0;
	}
}
int main()
{


	ifstream in("input.txt");
	ofstream out("output.txt");
	in >> n;
	if (n < 15) {
		for (int i = 1; i <= n; i++)
		{
			in >> xN[i] >> yN[i];
		}
		srand(time(NULL));
		for (int i = 1; i <= n; i++)
			r.push_back(i);
		random_shuffle(r.begin(), r.end());

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				a[i][j] = abs(xN[i] - xN[j]) + abs(yN[i] - yN[j]);
			}
		}
		memset(used, true, sizeof(used));
		cur[1] = 1;
		used[1] = false;
		dfs(1, 1, 0);
		out << optimalLength << endl;
		for (int i = 1; i <= n; ++i) out << ans[i] << " ";
		out << ans[1] << endl;
	}
	else {

		vector<int> xAr;
		vector<int> yAr;
		for (int i = 0; i < n; i++) {
			int x, y;
			in >> x;
			in >> y;
			xAr.push_back(x);
			yAr.push_back(y);
		}
		vector<vector<int>> arr;
		for (int i = 0; i < n; i++) {
			vector<int> temp;
			for (int j = 0; j < n; j++) {
				if (i != j)
					temp.push_back(abs(xAr[i] - xAr[j]) + abs(yAr[i] - yAr[j]));
				else
					temp.push_back(numeric_limits<int>::max());
			}
			arr.push_back(temp);
		}
		vector<int> x;
		vector<int> y;
		for (int i = 1; i <= n; i++) {
			x.push_back(i);
			y.push_back(i);
		}
		vector<int> h;
		vector<int> hh;
		calc(arr, n, x, y, 0, h, hh);
		for (int i = 0; i < n - 1; i++) {
			int p = 0;
			for (int j = 0; j < finalAnswersX.size(); j++) {
				if (finalAnswersX[j] == finalAnswersY[i])
					p = j;
			}
			int buffX = finalAnswersX[p];
			int buffY = finalAnswersY[p];
			finalAnswersX[p] = finalAnswersX[i + 1];
			finalAnswersY[p] = finalAnswersY[i + 1];
			finalAnswersX[i + 1] = buffX;
			finalAnswersY[i + 1] = buffY;
		}
		out << minSteps << "\n";
		out << finalAnswersX[0] << " ";
		for (int i = 0; i < finalAnswersY.size(); i++) {
			if (i != (finalAnswersY.size() - 1))
				out << finalAnswersY[i] << " ";
			else {
				out << finalAnswersY[i];
			}
		}
	}
	out.flush();
}