#include <iostream>
#include <fstream>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int min(long long a, long long b){
	if (a < b) return a;
	else return b;
}

int max(long long a, long long b){
	if (a > b) return a;
	else return b;
}

struct Node{
	int l;
	int r;
	long long min;
	long long max;
	long long sum;
	long long addSum;
};

Node tree[1000000];
int a[1000000];

void build(int cur, int left, int right){
	if (right < left) return;
	tree[cur].l = left;
	tree[cur].r = right;
	tree[cur].max = 0;
	tree[cur].min = 0;
	tree[cur].sum = 0;
	tree[cur].addSum = 0;
	if (right == left) return;
	build(2 * cur, left, (left + right) / 2);
	build(2 * cur + 1, (left + right) / 2 + 1, right);
}


void update(int cur){
	tree[cur * 2].addSum += tree[cur].addSum;
	tree[cur * 2].sum += (tree[cur * 2].r - tree[cur * 2].l + 1) * tree[cur].addSum;
	tree[cur * 2].min += tree[cur].addSum;
	tree[cur * 2].max += tree[cur].addSum;
	tree[cur * 2 + 1].addSum += tree[cur].addSum;
	tree[cur * 2 + 1].sum += (tree[cur * 2 + 1].r - tree[cur * 2 + 1].l + 1) * tree[cur].addSum;
	tree[cur * 2 + 1].min += tree[cur].addSum;
	tree[cur * 2 + 1].max += tree[cur].addSum;
	tree[cur].addSum = 0;
}

int findMin(int cur, int left, int right){
	if (left > right) return 22222222;
	if (tree[cur].addSum != 0) update(cur);
	if (tree[cur].l == left && tree[cur].r == right){
		return tree[cur].min;
	}
	return min(findMin(cur * 2, max(tree[cur*2].l, left), min(tree[cur*2].r, right)),findMin(cur * 2 + 1, max(tree[cur*2+1].l, left), min(tree[cur*2+1].r, right)));
}


int findMax(int cur, int left, int right){
	if (left > right) return -22222222;
	if (tree[cur].addSum != 0) update(cur);
	if (tree[cur].l == left && tree[cur].r == right){
		return tree[cur].max;
	}
	return max(findMax(cur * 2, max(tree[cur*2].l, left), min(tree[cur*2].r, right)),findMax(cur * 2 + 1, max(tree[cur*2+1].l, left), min(tree[cur*2+1].r, right)));
}

int findSum(int cur, int left, int right){
	if (left > right) return 0;
	if (tree[cur].addSum != 0) update(cur);
	if (tree[cur].l == left && tree[cur].r == right){
		return tree[cur].sum;
	}
	return findSum(cur * 2, max(tree[cur*2].l, left), min(tree[cur*2].r, right)) + findSum(cur * 2 + 1, max(tree[cur*2+1].l, left), min(tree[cur*2+1].r, right));
}

void changeVal(int cur, int left, int right, long long d){
	if (left > right) return;
	if (tree[cur].addSum != 0) update(cur);
	if (tree[cur].l == left && tree[cur].r == right){
		tree[cur].addSum = 0;
		tree[cur].sum = (tree[cur].r - tree[cur].l + 1) * d;
		tree[cur].min = d;
		tree[cur].max = d;
		return;
	}
	changeVal(cur * 2, max(tree[cur*2].l, left), min(tree[cur*2].r, right), d);
	changeVal(cur * 2 + 1, max(tree[cur*2 + 1].l, left), min(tree[cur*2+1].r, right), d);
	tree[cur].min = min(tree[cur * 2].min, tree[cur * 2 + 1].min);
	tree[cur].max = max(tree[cur * 2].max, tree[cur * 2 + 1].max);
	tree[cur].sum = tree[cur * 2].sum + tree[cur * 2 + 1].sum;
	tree[cur].addSum = 0;
}

void addVal(int cur, int left, int right, long long d){
	if (left > right) return;
	if (tree[cur].addSum != 0) update(cur);
	if (tree[cur].l == left && tree[cur].r == right){
		tree[cur].addSum = d;
		tree[cur].sum += (tree[cur].r - tree[cur].l + 1) * d;
		tree[cur].min += d;
		tree[cur].max += d;
		return;
	}
	addVal(cur * 2, max(tree[cur * 2].l, left), min(tree[cur * 2].r, right), d);
	addVal(cur * 2 + 1, max(tree[cur * 2 + 1].l, left), min(tree[cur * 2 + 1].r, right), d);
	tree[cur].min = min(tree[cur * 2].min, tree[cur * 2 + 1].min);
	tree[cur].max = max(tree[cur * 2].max, tree[cur * 2 + 1].max);
	tree[cur].sum = tree[cur * 2].sum + tree[cur * 2 + 1].sum;
}

int main(){
	int n;
	fin >> n;
	build(1, 1, n);
	int kk;
	while (fin >> kk){
		if (kk == 0) break;
		if (kk == 4){
			int l, r;
			fin >> l >> r;
			int min = findMin(1, l+1, r+1);
			fout << min << endl;
		}
		if (kk == 5){
			int l, r;
			fin >> l >> r;
			int max = findMax(1, l+1, r+1);
			fout << max << endl;
		}
		if (kk == 3){
			int l, r;
			fin >> l >> r;
			int sum = findSum(1, l+1, r+1);
			fout << sum << endl;
		}
		if (kk == 1){
			int l, val;
			fin >> l >> val;
			changeVal(1, l+1, l+1, val);
		}
		if (kk == 2){
			int l, r, val;
			fin >> l >> r >> val;
			addVal(1, l+1, r+1, val);
		}
	}
	return 0;
}
