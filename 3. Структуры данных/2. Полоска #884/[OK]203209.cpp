#include <cmath>
#include <iostream>
#include <vector>
#include <fstream>
#include <string>
using namespace std;

typedef std::vector<int> T_values;
typedef T_values T_strip;
typedef T_values T_end_values;
T_values get_indexes_of_bent_strip_with_size
(
	int len,
	T_end_values end_left_values = T_end_values(),
	T_end_values end_right_values = T_end_values()
)

{
	if (
		end_left_values.empty()
		)
	{
		end_left_values.push_back(0);
		end_right_values.push_back(len - 1);
	}

	if (len == 1)
	{
		return  end_left_values;
	}
	else
	{
		end_left_values.insert
		(
			end_left_values.end(),
			end_right_values.rbegin(),
			end_right_values.rend()
		);

		end_right_values = end_left_values;
		len /= 2;
		auto    delta = len - 1;

		for (size_t i{}; i < end_right_values.size(); ++i)
		{
			end_right_values[i] += (i % 2 ? -delta : delta);
		}

		return  get_indexes_of_bent_strip_with_size
		(
			len,
			end_left_values,
			end_right_values
		);
	}
}

void set_bent_strip_cells_numbering(T_strip &strip)
{
	auto    bent_strip_indexes = get_indexes_of_bent_strip_with_size
	(
		strip.size()
	);

	for (size_t i{}; i < bent_strip_indexes.size(); ++i)
	{
		strip[bent_strip_indexes[i]] = i + 1;
	}
}

int main() {
	ifstream fin("in.txt");
	ofstream fout("out.txt");

	int k;
	fin >> k;
	fin.close();

	T_strip strip(pow(2, k));

	set_bent_strip_cells_numbering(strip);

	string anw = "";

	for (int cell : strip)
	{
		anw += to_string(cell) + " ";
	}

	anw.pop_back();

	fout << anw;

	fout.close();
}