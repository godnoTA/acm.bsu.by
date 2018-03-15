#include <iostream>
#include <fstream>
#include <vector>
#include <queue>


std::vector<std::vector<int> > data;
std::vector<size_t> border;
size_t rows, columns;


void input(std::istream &in){

    in >> columns >> rows;//строки стобцы
    data.resize(columns);
    border.resize(columns);
    for(size_t index = 0; index < columns; ++index){
        data[index].resize(rows);
        for(size_t i = 0; i < rows; ++i){
            in >> data[index][i];
        }
    }

}

bool findZeroInRows(){

    bool happy_end = false;
    bool ind = false;
    for(size_t index = 0; index < border.size(); ++index){
        size_t i = border[index];
        while(i < rows){
            if(data[index][i] == 0){
                border[index] = i;
                happy_end = true;
                //std::cout << " border[index] = " << border[index] << std::endl;
                ind = true;
                break;
            }
            ++i;
        }
        if(!ind)
            border[index] = rows;
    }
    return happy_end;
}



int solve(){

    size_t solution = 0;
   // bool kapec = false;
   // bool polny_kapec = false;
    while(findZeroInRows()){
        //std::cout << "hi" << std::endl;
       //for(size_t k = 0; k < border.size(); ++ k){
       /*for(size_t k = 0; k < border.size(); ++ k){
            std::cout << border[k] << " ";
        }
        //std::cout << std::endl;
        /*if(kapec){
           polny_kapec = true;
        }
        kapec = true;*/
        std::queue<std::pair<size_t, size_t> > q;
        for(size_t index = 0; index < border.size(); ++index){
            if(border[index] < rows){
                q.emplace(index, border[index]);
                data[index][border[index]] = 2;
                //border[index];
                //std::cout << "JHIOHIH " << std::endl;

                break;
            }
        }
        while(!(q.empty())){
            auto &zu = q.front();
            //std::cout << "    " << q.front().first << ' ' << q.front().second << std::endl;

            //std::cout << zu.first << ' ' << zu.second << std::endl;
            size_t nextRow = zu.first + 1;
            nextRow = nextRow >= columns ? 0 : nextRow;
            size_t previousRow = zu.first == 0 ? columns - 1 : zu.first - 1;
            if(data[previousRow][zu.second] == 0){
                //std::cout << '1' << std::endl;
                q.emplace(previousRow, zu.second);
                data[previousRow][zu.second] = 2;
                //kapec = false;
            }
            if(zu.second > 0 && data[zu.first][zu.second - 1] == 0){
                //std::cout << '2' << std::endl;
                q.emplace(zu.first, zu.second - 1);
                data[zu.first][zu.second - 1] = 2;
                //kapec = false;
            }
            if(data[nextRow][zu.second] == 0){
                //std::cout << '3' << std::endl;
                q.emplace(nextRow, zu.second);
                data[nextRow][zu.second] = 2;
                //kapec = false;
            }
            if(zu.second < rows - 1 && data[zu.first][zu.second + 1] == 0){
                //std::cout << '4' << std::endl;
                q.emplace(zu.first, zu.second + 1);
                data[zu.first][zu.second + 1] = 2;
                //kapec = false;
            }
            q.pop();
            //std::cout << "zu " << zu.first << zu.second << std::endl;
        }
       // std::cout << " solution = " << solution <<std::endl;
        ++solution;
        /*if(kapec && polny_kapec){
            ++solution;
            findZeroInRows();
            }*/

    }
    return solution;
}

int main(){

    std::ifstream in("in.txt");
    input(in);
    std::ofstream out("out.txt");
    out << solve() << std::endl;
    return 0;
}
