#include <iostream>
#include <stack>
#include <vector>
#include <fstream>
#include <list>
#include <algorithm>

using namespace std;

class Rect{
public:
	int color;
	bool done;
	int number;
	Rect(int c, bool d, int num){
		color = c;
		done = d;
		number = num;
	}
		
};

bool check(Rect r1, Rect r2){
	if(r1.color == r2.color)
		return true;
	else if(r1.number >= r2.number)
		return true;
	return false;
}



int main(){
	ifstream in("in.txt");
	ofstream out("out.txt");
	int A, B, N;
	in >> A >> B >> N;
	Rect r(1, 0, 0);
	vector<vector<Rect>> matrix(A+2, vector<Rect>(B+2, r));


	for (int k = 0; k < N; k++){   
        int xLD, yLD, xRU, yRU, color;
		in >> xLD >> yLD >> xRU >> yRU >> color;
        for (int i = yLD + A / 2 + 1 ; i < yRU + A / 2 + 1; i++)
			for (int j = xLD + B / 2 + 1 ; j < xRU + B / 2 + 1; j++){
				matrix[i][j].color = color;
				matrix[i][j].number = k+1;
			}
    }
		

	list<pair<int, int>> l;
            for (int i = 1; i < A+1; i++)
            {
                for (int j = 1; j < B+1; j++)
                {
                    if (!matrix[i][j].done)
                    {
                        int color = matrix[i][j].color;
                        matrix[i][j].done = true;
						stack<pair<int, int>> st;
                        int square = 0;
						st.push(make_pair(i, j));
                        do
                        {
							int x = st.top().first;
							int y = st.top().second;
							st.pop();
                            if (x > 1 && matrix[x - 1][y].done == false && matrix[x - 1][y].color == matrix[x][y].color)
                            {
								st.push(make_pair(x-1, y));
								matrix[x-1][y].done = true;
							//	square++;
                            }
                            if (y > 1 && matrix[x][y - 1].done == false && matrix[x][y - 1].color == matrix[x][y].color)
                            {
                                st.push(make_pair(x, y-1));
								matrix[x][y-1].done = true;
							//	square++;
                            }
                            if (x < A && matrix[x + 1][y].done == false && matrix[x + 1][y].color == matrix[x][y].color)
                            {
                                st.push(make_pair(x+1, y));
								matrix[x+1][y].done = true;
							//	square++;
                            }
                            if (y < B && matrix[x][y + 1].done == false && matrix[x][y + 1].color == matrix[x][y].color)
                            {
                                st.push(make_pair(x, y+1));
								matrix[x][y+1].done = true;
								//square++;
                            }


                            if (x > 1 && y > 1 && matrix[x - 1][y - 1].done == false && matrix[x - 1][y - 1].color == matrix[x][y].color)
							
                            {
								Rect last(1, 0 , 0);
								last.color = matrix[x-1][y-1].color;
								last.number = ((matrix[x-1][y-1].number > matrix[x][y].number) ? matrix[x-1][y-1].number : matrix[x][y].number);
								if(check(last, matrix[x-1][y]) && check(last,matrix[x][y-1])){
								 st.push(make_pair(x-1, y-1));
								matrix[x-1][y-1].done = true;
								}
								//square++;
                            }
                            if (x < A && y > 1 && matrix[x + 1][y - 1].done == false && matrix[x + 1][y - 1].color == matrix[x][y].color) 
                            {
								Rect last(1, 0 , 0);
								last.color = matrix[x+1][y-1].color;
								last.number = ((matrix[x+1][y-1].number > matrix[x][y].number) ? matrix[x+1][y-1].number : matrix[x][y].number);
								if(check(last, matrix[x+1][y]) && check(last,matrix[x][y-1])){
                                 st.push(make_pair(x+1, y-1));
								matrix[x+1][y-1].done = true;
								}
								//square++;
                            }
                            if (x < A && y < B && matrix[x + 1][y + 1].done == false && matrix[x + 1][y + 1].color == matrix[x][y].color) 
                            {
								Rect last(1, 0 , 0);
								last.color = matrix[x+1][y+1].color;
								last.number = ((matrix[x+1][y+1].number > matrix[x][y].number) ? matrix[x+1][y+1].number : matrix[x][y].number);
								if(check(last, matrix[x+1][y]) && check(last,matrix[x][y+1])){
                                  st.push(make_pair(x+1, y+1));
								matrix[x+1][y+1].done = true;
								}
							//	square++;
                            }
                            if (x > 1 && y < B && matrix[x - 1][y + 1].done == false && matrix[x - 1][y + 1].color == matrix[x][y].color)
                            {
								Rect last(1, 0 , 0);
								last.color = matrix[x-1][y+1].color;
								last.number = ((matrix[x-1][y+1].number > matrix[x][y].number) ? matrix[x-1][y+1].number : matrix[x][y].number);
								if(check(last, matrix[x-1][y]) && check(last,matrix[x][y+1])){
                                 st.push(make_pair(x-1, y+1));
								matrix[x-1][y+1].done = true;
								}
								//square++;
                            }
                            square++;
                        } while (st.size() != 0);
						l.push_back(make_pair(color, square));
                    }
                }


            }
		l.sort();
		list<pair<int, int>> :: iterator i;
		for (i = l.begin(); i != l.end(); i++)
			out << i->first << " " << i->second << "\n";
		return 0;
}