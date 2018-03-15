// data_structures_33_array_vector.cpp : Defines the entry point for the console application.
//

#include <fstream>
#include <map>
#include <vector>
#include <iostream>
#include <set>
using namespace std;

struct ordinate {
	int quantity, previous, next;
	ordinate() {
		quantity = 0;
		previous = next = -1;
	}
};

void del_from_vector(vector<ordinate> &ordinates, ordinate *to_delete, int &max_height, int y) {
	to_delete->quantity--;
	if(to_delete->quantity == 0) {
		if(to_delete->previous != -1 && to_delete->next != -1) {
			ordinates[to_delete->previous].next = to_delete->next;
			ordinates[to_delete->next].previous = to_delete->previous;
		}

		if(max_height < to_delete->next - to_delete->previous)
			max_height = to_delete->next - to_delete->previous;
	}
}

int main()
{
	ifstream in("input.txt");

	int n, x, y;
	in >> n >> x >> y;

	int xi, yi;
	map<int, vector<int>> abscesses;
	abscesses.insert(make_pair(0, NULL));
	ordinate new_ordinate;
	vector<ordinate> ordinates(y + 1, new_ordinate);
	set<int> abcde;
	abcde.insert(0);
	for(int i = 0; i < n; i++) {
		in >> xi >> yi;
		map<int, vector<int>>::iterator is_found = abscesses.find(xi);
		if(is_found != abscesses.end())
			is_found->second.push_back(yi);
		else {
			vector<int> corresponding_ordinates(1, yi);
			abscesses.insert(make_pair(xi, corresponding_ordinates));
		}
		ordinates[yi].quantity++;
		abcde.insert(yi);
	}
	abscesses.insert(make_pair(x, NULL));
	abcde.insert(y);

	in.close();

	set<int>::iterator set_it = abcde.begin();
	int previous_ordinate = *set_it;
	++set_it;
	int max_height = 1;
	while(set_it != abcde.end()) {
		ordinates[previous_ordinate].next = *set_it;
		ordinates[*set_it].previous = previous_ordinate;
		if(max_height < *set_it - previous_ordinate)
			max_height = *set_it - previous_ordinate;
		previous_ordinate = *set_it;
		set_it++;
	}

	int max_area = 1;

	int max_height_init = max_height;
	map<int, vector<int>>::iterator left_it = abscesses.begin();
	while(left_it != --abscesses.end()) {

		max_height = max_height_init;

		for(int i = 0; i < left_it->second.size(); i++)
			del_from_vector(ordinates, &ordinates[left_it->second[i]], max_height, y);

		map<int, vector<int>>::iterator right_it = --abscesses.end();
		while(right_it != left_it) {
			for(int i = 0; i < right_it->second.size(); i++)
				del_from_vector(ordinates, &ordinates[right_it->second[i]], max_height, y);

			if(max_area < (right_it->first - left_it->first) * max_height)
				max_area = (right_it->first - left_it->first) * max_height;

			right_it--;
		}

		++right_it;
		while(right_it != --abscesses.end()) {
			for(int i = 0; i < right_it->second.size(); i++) {
				ordinate *to_add = &ordinates[right_it->second[i]];
				if(to_add->quantity == 0 && to_add->previous != -1 && to_add->next != -1)
					ordinates[to_add->previous].next = ordinates[to_add->next].previous = right_it->second[i];
				to_add->quantity++;
			}
			right_it++;
		}

		left_it++;
	}

	ofstream out("output.txt");

	out << max_area;

	out.close();

	return 0;
}

