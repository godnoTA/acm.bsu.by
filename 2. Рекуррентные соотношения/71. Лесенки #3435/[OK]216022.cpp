#include <iostream>
#include <limits>
#include <vector>
#include <fstream>
using namespace std;

typedef unsigned long long  T_int;
typedef std::vector<T_int>  T_row;
typedef std::vector<T_row>  T_matr;

template<class T>
bool  successful_inc(T&  val,T   added_val)
{
	bool  bool_res = val < std::numeric_limits<T>::max() - added_val;

	if (bool_res)
	{
		val += added_val;
	}
	return  bool_res;
}

bool  L(int N,T_int&  res)
{
	bool    bool_res = false;
	T_matr  matr(N + 1, T_row(N + 1));

	for (int n = 1; n <= N; ++n)
	{
		for (int max_summand = 1; max_summand <= n; ++max_summand)
		{
			if (max_summand == n)
			{
				matr[n][max_summand] = 1;
			}
			else
			{
				for (int i = 1; i < max_summand; ++i)
				{
					bool_res = successful_inc
						(
						matr[n][max_summand],
						matr[n - max_summand][i]
						);

					if (!bool_res)
					{
						return  bool_res;
					}
				}
			}
		}
	}

	res = 0;

	for (int i = 1; i <= N; ++i)
	{
		bool_res = successful_inc
			(
			res,
			matr[N][i]
			);

		if (!bool_res)
		{
			return  bool_res;
		}
	}
	return  bool_res;
}

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int  n = 0;
	fin >> n;
	T_int   res = 0;
	bool    bool_res = L(n, res);
	fout << res;
}