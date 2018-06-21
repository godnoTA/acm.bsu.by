#include <iostream>
#include <vector>
#include <queue>
#include <fstream>

int main ()
{
    std::ifstream input ("input.in");
    int vertexCount;
    input >> vertexCount;

    std::vector <std::vector <bool> > matrix (vertexCount, std::vector <bool> (vertexCount, false));
    for (unsigned int row = 0; row < vertexCount; row++)
    {
        for (unsigned int col = 0; col < vertexCount; col++)
        {
            int data;
            input >> data;
            matrix [row] [col] = data != 0;
        }
    }
    input.close ();

    std::vector <int> marks (vertexCount, 0);
    bool errors = false;

    for (unsigned int index = 0; index < vertexCount; index++)
    {
        if (marks [index] == 0)
        {
            std::queue <std::pair <int, int> > vertexQueue;
            int firstCount = 0;
            int secondCount = 0;

            for (unsigned int vertex = 0; vertex < vertexCount; vertex++)
            {
                if (marks [vertex] == 1)
                {
                    firstCount++;
                }
                else if (marks [vertex] == 2)
                {
                    secondCount++;
                }
            }
            vertexQueue.emplace (std::make_pair (index, firstCount > secondCount ? 2 : 1));

            while (!vertexQueue.empty ())
            {
                int vertex = vertexQueue.front ().first;
                int mark = vertexQueue.front ().second;
                vertexQueue.pop ();

                if (marks [vertex] != 0 && marks [vertex] != mark)
                {
                    errors = true;
                    break;
                }

                if (marks [vertex] == 0)
                {
                    marks [vertex] = mark;
                    int anotherMark = mark == 1 ? 2 : 1;

                    for (unsigned int anotherVertexIndex = 0; anotherVertexIndex < vertexCount; anotherVertexIndex++)
                    {
                        if (matrix[vertex][anotherVertexIndex])
                        {
                            if (marks[anotherVertexIndex] == 0)
                            {
                                vertexQueue.emplace (std::make_pair (anotherVertexIndex, anotherMark));
                            }
                            else if (marks[anotherVertexIndex] != anotherMark)
                            {
                                errors = true;
                                break;
                            }
                        }
                    }

                    if (errors)
                    {
                        break;
                    }
                }
            }

            if (errors)
            {
                break;
            }
        }
    }

    std::ofstream output ("output.out");
    output << (errors ? "NO" : "YES") << std::endl;

    if (!errors)
    {
        for (unsigned int index = 0; index < vertexCount; index++)
        {
            if (marks [index] == 1)
            {
                output << (index + 1) << " ";
            }
        }
    }

    output << std::endl;
    output.close ();
    return 0;
}
