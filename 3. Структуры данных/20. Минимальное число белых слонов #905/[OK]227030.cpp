#include<stdio.h>
#include<malloc.h>

struct Coordinate{
	int x;
	int y;
};

class Elephant{
private:
	int **board, i, j;
	int i_first, i_end;
	int N, M, count;
	Coordinate *arr_coord;
public:
	Elephant(){
		N = 0, M = 0, count = 0;
		i_first=0, i_end = 0;
		i=0, j=0;
		arr_coord = (Coordinate*)malloc(500000 * sizeof(Coordinate));
	}
	void read(){
		freopen("in.txt", "r", stdin);
		freopen("out.txt", "w", stdout);
		scanf("%d %d", &N, &M);
		board = (int**)malloc(N*sizeof(int*));
		for (i = 0; i < N; i++)
		{
			board[i] = (int*)malloc(M*sizeof(int));
			for (j = 0; j < M; j++)
				scanf("%d", &board[i][j]);
		}
	}
	void algorihm(){
		read();
		for (i = 0; i < N; i++)
		for (j = 0; j<M; j++)
		if (board[i][j] == 0)
		{
			board[i][j] = 1;
			i_first = 0; i_end = 1; arr_coord[0].x = i; arr_coord[0].y = j;

			count++;
			while (i_first<i_end)
			{
				if (arr_coord[i_first].x>0)
				{
					if (arr_coord[i_first].y>0 && board[arr_coord[i_first].x - 1][arr_coord[i_first].y - 1] == 0)
					{
						arr_coord[i_end].x = arr_coord[i_first].x - 1; arr_coord[i_end++].y = arr_coord[i_first].y - 1; board[arr_coord[i_first].x - 1][arr_coord[i_first].y - 1] = 1;
					}
					if (arr_coord[i_first].y < M - 1 && board[arr_coord[i_first].x - 1][arr_coord[i_first].y + 1] == 0)
					{
						arr_coord[i_end].x = arr_coord[i_first].x - 1; arr_coord[i_end++].y = arr_coord[i_first].y + 1; board[arr_coord[i_first].x - 1][arr_coord[i_first].y + 1] = 1;
					}
				}
				if (arr_coord[i_first].x<N - 1)
				{
					if (arr_coord[i_first].y>0 && board[arr_coord[i_first].x + 1][arr_coord[i_first].y - 1] == 0)
					{
						arr_coord[i_end].x = arr_coord[i_first].x + 1; arr_coord[i_end++].y = arr_coord[i_first].y - 1; board[arr_coord[i_first].x + 1][arr_coord[i_first].y - 1] = 1;
					}
					if (arr_coord[i_first].y < M - 1 && board[arr_coord[i_first].x + 1][arr_coord[i_first].y + 1] == 0)
					{
						arr_coord[i_end].x = arr_coord[i_first].x + 1; arr_coord[i_end++].y = arr_coord[i_first].y + 1; board[arr_coord[i_first].x + 1][arr_coord[i_first].y + 1] = 1;
					}
				}
				i_first++;
			}
		}
		printf("%d", count);
	}

};

int main()
{
	Elephant el;
	el.algorihm();
	return 0;
}