#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <vector>

const int NEUTRAL_SUM = 0;
const int NEUTRAL_MIN = INT_MAX;
const int NEUTRAL_MAX = INT_MIN;
const int NEUTRAL_SET = 1000001;


std::vector<int> list_sum;
std::vector<int> list_min;
std::vector<int> list_max;
std::vector<int> addition;
std::vector<int> set;

void lazy_propagation(int root, int left_range, int right_range) {
	if (set[root] != NEUTRAL_SET) {
		list_sum[root] = set[root] * (right_range - left_range + 1);
		list_min[root] = set[root];
		list_max[root] = set[root];
		if (left_range != right_range) {
			set[2 * root + 1] = set[root];
			set[2 * root + 2] = set[root];

			addition[2 * root + 1] = NEUTRAL_SUM;
			addition[2 * root + 2] = NEUTRAL_SUM;

		}
		set[root] = NEUTRAL_SET;
	}
	if (addition[root] != NEUTRAL_SUM) {
		list_sum[root] += addition[root] * (right_range - left_range + 1);
		list_min[root] += addition[root];
		list_max[root] += addition[root];
		if (left_range != right_range) {
			addition[2 * root + 1] += addition[root];
			addition[2 * root + 2] += addition[root];
		}
		addition[root] = NEUTRAL_SUM;
	}
}
void build_tree(int root, int left_range, int right_range, std::vector<int> &sequence) {
	addition[root] = NEUTRAL_SUM;
	set[root] = NEUTRAL_SET;
	if (left_range == right_range) {
		list_sum[root] = sequence[left_range];
		list_min[root] = sequence[left_range];
		list_max[root] = sequence[left_range];
		return;
	}
	int middle = left_range + (right_range - left_range) / 2;
	build_tree(2 * root + 1, left_range, middle, sequence);
	build_tree(2 * root + 2, middle + 1, right_range, sequence);
	list_sum[root] = list_sum[2 * root + 1] + list_sum[2 * root + 2];
	list_min[root] = fmin(list_min[2 * root + 1],list_min[2 * root + 2]);
	list_max[root] = fmax(list_max[2 * root + 1], list_max[2 * root + 2]);
}
int sum_query(int root, int left_range, int right_range, int left_query, int right_query) {
	lazy_propagation(root, left_range, right_range);
	if (right_query < left_range || left_query > right_range) {
		return NEUTRAL_SUM;
	}
	if (left_range >= left_query && right_range <= right_query) {
		return list_sum[root];
	}
	
	int middle = left_range + (right_range - left_range) / 2;
	int sum_left = sum_query(2 * root + 1, left_range, middle, left_query, right_query);
	int sum_right = sum_query(2 * root + 2, middle + 1, right_range, left_query, right_query);
	return sum_left + sum_right;
}
int min_query(int root, int left_range, int right_range, int left_query, int right_query) {
	lazy_propagation(root, left_range, right_range);
	if (right_query < left_range || left_query > right_range) {
		return NEUTRAL_MIN;
	}
	if (left_range >= left_query && right_range <= right_query) {
		return list_min[root];
	}
	int middle = left_range + (right_range - left_range) / 2;
	int min_left = min_query(2 * root + 1, left_range, middle, left_query, right_query);
	int min_right = min_query(2 * root + 2, middle + 1, right_range, left_query, right_query);
	return fmin(min_left, min_right);
}
int max_query(int root, int left_range, int right_range, int left_query, int right_query) {
	lazy_propagation(root, left_range, right_range);
	if (right_query < left_range || left_query > right_range) {
		return NEUTRAL_MAX;
	}
	if (left_range >= left_query && right_range <= right_query) {
		return list_max[root];
	}
	int middle = left_range + (right_range - left_range) / 2;
	int max_left = max_query(2 * root + 1, left_range, middle, left_query, right_query);
	int max_right = max_query(2 * root + 2, middle + 1, right_range, left_query, right_query);
	return fmax(max_left, max_right);
}
void modify(int root, int left_range, int right_range, int left_query, int right_query,int add_value) {
	lazy_propagation(root, left_range, right_range);
	if (right_query < left_range || left_query > right_range) {
		return;
	}
	if (left_query <= left_range && right_range <= right_query) {
		addition[root] = add_value;
		lazy_propagation(root, left_range, right_range);
		return;
	}
	int middle = left_range + (right_range - left_range) / 2;
	modify(2 * root + 1, left_range, middle, left_query, right_query,add_value);
	modify(2 * root + 2, middle + 1, right_range, left_query, right_query,add_value);
	list_sum[root] = list_sum[2 * root + 1] + list_sum[2 * root + 2];
	list_min[root] = fmin(list_min[2 * root + 1],list_min[2 * root + 2]);
	list_max[root] = fmax(list_max[2 * root + 1],list_max[2 * root + 2]);
}

void set_value(int root, int left_range, int right_range, int left_query, int right_query, int value) {
	lazy_propagation(root, left_range, right_range);
	if (right_query < left_range || left_query > right_range) {
		return;
	}
	if (left_query <= left_range && right_range <= right_query) {
		set[root] = value;
		lazy_propagation(root, left_range, right_range);
		return;
	}
	int middle = left_range + (right_range - left_range) / 2;
	set_value(2 * root + 1, left_range, middle, left_query, right_query, value);
	set_value(2 * root + 2, middle + 1, right_range, left_query, right_query, value);
	list_sum[root] = list_sum[2 * root + 1] + list_sum[2 * root + 2];
	list_min[root] = fmin(list_min[2 * root + 1], list_min[2 * root + 2]);
	list_max[root] = fmax(list_max[2 * root + 1], list_max[2 * root + 2]);
	
}



int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n, type = 6;
	std::cin >> n;
	std::vector <int> sequence(n, 0); 
	list_sum.resize(4 * n, NEUTRAL_SUM);
	list_min.resize(4 * n,NEUTRAL_MIN);
	list_max.resize(4 * n,NEUTRAL_MAX);
	addition.resize(4 * n,NEUTRAL_SUM);
	set.resize(4 * n, NEUTRAL_SUM);

	build_tree(0, 0, sequence.size() - 1, sequence);


	while (type != 0) {
		std::cin >> type;
		switch (type) {
		case 0: {
			break;
		}
		case 1: {
			int i, v;
			std::cin >> i >> v;
			set_value(0, 0, sequence.size() - 1, i, i, v);
			break;
		}
		case 2: {
			int a, b, v;
			std::cin >> a >> b >> v;
			modify(0, 0, sequence.size() - 1, a, b, v);
			break;
		}
		case 3: {
			int a, b;
			std::cin >> a >> b;
			int temp = 0;
			temp = sum_query(0, 0, sequence.size() - 1, a, b);
			std::cout << temp << std::endl;
			break;
		}
		case 4: {
			int a, b;
			std::cin >> a >> b;
			int temp = 0;
			temp = min_query(0, 0, sequence.size() - 1, a, b);
			std::cout << temp <<std::endl;
			break;
		}
		case 5: {
			int a, b;
			std::cin >> a >> b;
			int temp = 0;
			temp = max_query(0, 0, sequence.size() - 1, a, b);
			std::cout << temp << std::endl;
			break;

		}
		}

	}

	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}