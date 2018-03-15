#include <fstream>
#include <list>

using namespace std;

struct Location
{
	int N;
	int M;
};

Location searchLocation(int ** arr, int n, int m, int value)
{
	Location loc;
	for (int i = value; i < n; ++i)
	{
		for (int j = 0; j < m; ++j)
			if (!arr[i][j])
			{
				loc.N = i;
				loc.M = j;
				return loc;
			}
	}
	loc.N = -1;
	loc.M = -1;
	return loc;
}

bool algoritm(int ** arr, int n, int m, Location prev_loc, Location &new_loc)
{
	if (!n || !m) return 0;
	if (!prev_loc.M && !prev_loc.N)
	{
		if (!arr[prev_loc.M + 1][prev_loc.N + 1])
		{
			new_loc.M = prev_loc.M + 1;
			new_loc.N = prev_loc.N + 1;
			return 1;
		}
	}
	else
		if (!prev_loc.N && prev_loc.M == m)
		{
			if (!arr[prev_loc.N + 1][prev_loc.M - 1])
			{
				new_loc.N = prev_loc.N + 1;
				new_loc.M = prev_loc.M - 1;
				return 1;
			}
		}
		else
			if (prev_loc.N == n && prev_loc.M == m)
			{
				if (!arr[prev_loc.N - 1][prev_loc.M - 1])
				{
					new_loc.N = prev_loc.N - 1;
					new_loc.M = prev_loc.M - 1;
					return 1;
				}
			}
			else
				if (!prev_loc.M && prev_loc.N == n)
				{
					if (!arr[prev_loc.N - 1][prev_loc.M + 1])
					{
						new_loc.N = prev_loc.N - 1;
						new_loc.M = prev_loc.M + 1;
						return 1;
					}
				}
				else
					if (!prev_loc.M && (!arr[prev_loc.N - 1][prev_loc.M + 1] || !arr[prev_loc.N + 1][prev_loc.M + 1]))
					{
						if (!arr[prev_loc.N - 1][prev_loc.M + 1])
						{
							new_loc.N = prev_loc.N - 1;
							new_loc.M = prev_loc.M + 1;
						}
						else
						{
							new_loc.N = prev_loc.N + 1;
							new_loc.M = prev_loc.M + 1;
						}
						return 1;
					}
					else
						if (!prev_loc.N && (!arr[prev_loc.N + 1][prev_loc.M - 1] || !arr[prev_loc.N + 1][prev_loc.M + 1]))
						{
							if (!arr[prev_loc.N + 1][prev_loc.M - 1])
							{
								new_loc.N = prev_loc.N + 1;
								new_loc.M = prev_loc.M - 1;
							}
							else
							{
								new_loc.N = prev_loc.N + 1;
								new_loc.M = prev_loc.M + 1;
							}
							return 1;
						}
						else
							if (prev_loc.M == m && (!arr[prev_loc.N - 1][prev_loc.M - 1] || !arr[prev_loc.N + 1][prev_loc.M - 1]))
							{
								if (!arr[prev_loc.N - 1][prev_loc.M - 1])
								{
									new_loc.N = prev_loc.N - 1;
									new_loc.M = prev_loc.M - 1;
								}
								else
								{
									new_loc.N = prev_loc.N + 1;
									new_loc.M = prev_loc.M - 1;
								}
								return 1;
							}
							else
								if (prev_loc.N == n && (!arr[prev_loc.N - 1][prev_loc.M - 1] || !arr[prev_loc.N - 1][prev_loc.M + 1]))
								{
									if (!arr[prev_loc.N - 1][prev_loc.M - 1])
									{
										new_loc.N = prev_loc.N - 1;
										new_loc.M = prev_loc.M - 1;
									}
									else
									{
										new_loc.N = prev_loc.N - 1;
										new_loc.M = prev_loc.M + 1;
									}
									return 1;
								}
								else
									if ((prev_loc.N && prev_loc.M && (prev_loc.N < n) && (prev_loc.M < m)) &&
										(!arr[prev_loc.N - 1][prev_loc.M - 1] ||
										!arr[prev_loc.N - 1][prev_loc.M + 1] ||
										!arr[prev_loc.N + 1][prev_loc.M - 1] ||
										!arr[prev_loc.N + 1][prev_loc.M + 1]))
									{
										if (!arr[prev_loc.N - 1][prev_loc.M - 1])
										{
											new_loc.N = prev_loc.N - 1;
											new_loc.M = prev_loc.M - 1;
										}
										else
											if (!arr[prev_loc.N - 1][prev_loc.M + 1])
											{
												new_loc.N = prev_loc.N - 1;
												new_loc.M = prev_loc.M + 1;
											}
											else
												if (!arr[prev_loc.N + 1][prev_loc.M - 1])
												{
													new_loc.N = prev_loc.N + 1;
													new_loc.M = prev_loc.M - 1;
												}
												else
												{
													new_loc.N = prev_loc.N + 1;
													new_loc.M = prev_loc.M + 1;
												}
										return 1;
									}
				new_loc.N = -1;
	new_loc.M = -1;
	return 0;
}

int main()
{
	ifstream input("in.txt");
	int N, M;
	input >> N >> M;
	int ** array = new int*[N];
	for (int i = 0; i < N; ++i)
	{
		array[i] = new int[M];
		for (int j = 0; j < M; ++j)
			input >> array[i][j];
	}
	input.close();

	list<Location> list_location;
	list<Location>::const_iterator busy_loc;
	int count = 0;
	Location free, busy;
	free.N = 0;
	for (; ((searchLocation(array, N, M, free.N).N != -1) && (searchLocation(array, N, M, free.N).M != -1));)
	{
		count++;
		free = searchLocation(array, N, M, free.N);
		list_location.push_back(free);
		busy_loc = list_location.begin();
		array[free.N][free.M] = 1;
		for (; algoritm(array, N - 1, M - 1, free, busy);)
		{
			list_location.push_back(busy);
			array[busy.N][busy.M] = 1;
		}
		for (; busy_loc != list_location.end();)
		{
			for (; algoritm(array, N - 1, M - 1, *busy_loc, busy);)
			{
				list_location.push_back(busy);
				array[busy.N][busy.M] = 1;
			}
			busy_loc++;
		}
		list_location.clear();
	}

	ofstream output("out.txt");
	output << count << endl;
	output.close();

	return 0;
}