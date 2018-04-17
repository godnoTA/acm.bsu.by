#include <iostream>
#include <fstream>
using namespace std;

struct cell {
	int x, y;
};
struct Queue {
	cell*elem;
	int head, tail, size;
};
void create(Queue&q, int n) {
	q.head = q.tail = 0;
	q.size = n;
	q.elem = new cell[n + 1];
}
void del(Queue&q) {
	if (q.elem) delete[]q.elem;
}

void push(Queue&q, int x, int y) {
	q.elem[q.tail].x = x;
	q.elem[q.tail].y = y;
	q.tail++;
}
void pop(Queue&q) {
	q.head++;
}
bool IsEmpty(Queue&q) {
	return q.head == q.tail;
}
bool inside(int x, int y, int n, int m) {
	return (0 <= x && x < n && 0 <= y && y < m);
}

const int dx[] = { -1, 0, 1, 0 };
const int dy[] = { 0, -1, 0, 1 };
int main() {

	ifstream in("in.txt");
	ofstream out("out.txt");

	int n, m;
	in >> n >> m;

	int **array = new int*[n];
	for (int i = 0; i<n; i++)
		array[i] = new int[m];

	for (int i = 0; i < n; i++)
		for (int j = 0; j <m; j++)
			in >> array[i][j];

	bool **mas = new bool*[n];
	for (int i = 0; i<n; i++)
		mas[i] = new bool[m];

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) {
			if (array[i][j] == 0) mas[i][j] = false;
			else mas[i][j] = true;
		}

	Queue q;
	create(q, n*m);
	int res = 0;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) {
			if (!mas[i][j]) {
				push(q, i, j);
				mas[i][j] = true;
				res++;
				while (!IsEmpty(q)) {

					cell temp = q.elem[q.head];
					pop(q);
					for (int nom = 0; nom < 4; nom++) {
						int x = temp.x + dx[nom];
						int y = temp.y + dy[nom];
						if (inside(x, y, n, m) == true) {
							if (mas[x][y]) continue;
							if (!mas[x][y]) {
								push(q, x, y);
								mas[x][y] = true;
							}
						}
					}
				}
			}
		}

	cout << res << endl;
	out << res << endl;


	return 0;
}

