#include <cassert>
#include <cmath>
#include <vector>
#include <algorithm>
#include <limits>
#include <iostream>
#include <fstream>
 
struct Point { int x, y; };
 
double distance(const Point &p1, const Point &p2)
{
  Point delta = { p2.x - p1.x, p2.y - p1.y };
  return std::sqrt((double) delta.x * delta.x + (double) delta.y * delta.y);
}
 
static std::vector<std::vector<double>> s_solutions;
 
double MWT(const Point poly[], unsigned n, unsigned i, unsigned j)
{
  assert(i < j && j < n);
 
  if (j == i + 1)
    // Исходное ребро - возвращаем вес 0
    return 0;
 
  if (s_solutions[i][j] >= 0)
    // Такую подзадачу мы уже решали - возвращаем готовое решение
    return s_solutions[i][j];
 
  // Такую подзадачу мы еще не решали - решаем ее полностью
 
  // Вес диагонали
  double weight_ij = i > 0 || j < n - 1 ? distance(poly[i], poly[j]) : 0;
  // Не забываем, что диагональ [0, n - 1] - это исходное ребро и ее вес равен 0
 
  double min_weight = std::numeric_limits<double>::max();
  for (unsigned k = i + 1; k < j; ++k)
  {
    double weight = MWT(poly, n, i, k) + MWT(poly, n, k, j);
    min_weight = std::min(min_weight, weight);
  }
 
  // Решение нашей подзадачи
  double weight = weight_ij + min_weight;
  
  // Запоминаем решение на будущее
  s_solutions[i][j] = weight;
 
  return weight;
}
 
double MWT(const Point poly[], unsigned n)
{
  return MWT(poly, n, 0, n - 1);
}

double round(double d){
	d *= 1000;
	int tmp = d;
	d = tmp;
	d /=1000;
	if( (tmp%10 >= 0) && (tmp%10 < 5)){
		return d;
	}
	else{
		return d + 0.001;
	}
}
 
int main()
{
	std::ifstream fin("input.txt"); // открыли файл для чтения

	int nu;
	fin >> nu;
	Point *poly = new Point[nu];
	nu = 0;
	if (!fin.is_open())
        return 1;
    int number1, number2;
    while (fin >> number1 && fin >> number2){
		poly[nu].x = number1;
		poly[nu].y = number2;
		nu++;
	}
	fin.close();
	std::FILE* out;
	out = fopen("output.txt","w");
	Point tmp = poly[2];
 
	s_solutions.resize(nu);

	for(int i = 0; i < s_solutions.size(); i++){
		  s_solutions[i].resize(nu, -1);
	}
	double answer = round(MWT(poly, nu));
	if(out!=NULL){
		std::fprintf(out,"%.2f",answer);
		std::fclose(out);
	}
	std::cout << answer << std::endl;
	delete []poly;
	
  system("pause");
}