#include <stdio.h>
#include <list>
using std::list;

inline void pop(list<int> &m, list<int> &t) {
	t.front()--;
	if (t.front() == 0) {
		m.pop_front();
		t.pop_front();
	}
}

int main()
{
	int S1, S2;
	FILE *f = fopen("input.txt", "r");
	fscanf(f, "%d\n", &S1);
	fscanf(f, "%d\n", &S1);
	list<int> m1, t1, m2, t2;
	int m, l1 = 0, l2 = 0;
	for (int j = 0; j < S1; j++) {
		fscanf(f, "%d", &m);
		m1.push_back(m);
		fscanf(f, "%d", &m);
		t1.push_back(m);
		l1 += m;
	}
	fscanf(f, "%d\n", &S2);	
	for (int j = 0; j < S2; j++) {
		fscanf(f, "%d", &m);
		m2.push_back(m);
		fscanf(f, "%d", &m);
		t2.push_back(m);
		l2 += m;
	}
	fclose(f);
	int time = 0;
	while (!m1.empty() || !m2.empty()) {
		if (m1.empty()) {
			pop(m2, t2);
			l2--;
		}
		else if (m2.empty()) {
			pop(m1, t1);
			l1--;
		}
		else if (m1.front() != m2.front()) {
			pop(m1, t1);
			l1--;
			pop(m2, t2);
			l2--;
		}
		else if (l1 > l2) {
			pop(m1, t1);
			l1--;
		}
		else if (l2 > l1) {
			pop(m2, t2);
			l2--;
		}
		else if (t1.front() < t2.front()) {
			pop(m1, t1);
			l1--;
		}
		else {
			pop(m2, t2);
			l2--;
		}
		time++;
	}	
	f = fopen("output.txt", "w");
	fprintf(f, "%d", time);
	fclose(f);
	return 0;
}