#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <algorithm>

int main() {
    std::ifstream in("input.txt");
    size_t firstSize;
    size_t secondSize;
    in >> firstSize >> secondSize;
    std::vector<int> firstText;
    for (size_t i = 0; i < firstSize; ++i) {
        int letter;
        in >> letter;
        firstText.push_back(letter);
    }
    std::unordered_map<int, int> secondText;
    for(int i = 0; i < secondSize; ++i) {
        int letter;
        in >> letter;
        secondText[letter] = i + 1;
    }
    std::vector<int> commonLetters;
    for (int letter : firstText) {
        if (secondText[letter] != 0) {
            commonLetters.push_back(secondText[letter]);
        }
    }
    std::vector<int> lastCommonLetter(commonLetters.size() + 1, 2000000001);
    lastCommonLetter[0] = -1;
    for (int commonLetter : commonLetters) {
        size_t j = static_cast<size_t>(
                std::upper_bound(lastCommonLetter.begin(),
                                 lastCommonLetter.end(), commonLetter) -
                        lastCommonLetter.begin());
        if (lastCommonLetter[j-1] < commonLetter &&
                lastCommonLetter[j] > commonLetter) {
            lastCommonLetter[j] = commonLetter;
        }
    }
    int i = 0;
    for (; i < lastCommonLetter.size() && lastCommonLetter[i] != 2000000001; ++i) {}
    --i;
    std::ofstream out("output.txt");
    out << i;
    return 0;
}