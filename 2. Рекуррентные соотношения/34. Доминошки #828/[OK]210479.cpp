#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <algorithm>

struct SumInfo{
    std::vector<int> count;
    bool canSum;
    SumInfo(): count(13, 0), canSum(false) {}
};

int main() {
    std::ifstream in("in.txt");
    std::size_t size;
    in >> size;
    std::unordered_map<int, std::size_t> count;
    std::vector<int> numbers{1, 2, 3, 4, 5, 6, -6, -5, -4, -3, -2, -1};
    int sum = 0;
    int posCount = 0;
    int negCount = 0;
    for (std::size_t i = 0; i < size; ++i) {
        int up, down;
        in >> up >> down;
        int difference = up - down;
        if (difference < 0) {
            ++negCount;
        } else if (difference > 0) {
            ++posCount;
        }
        int absoluteDifference = std::abs(difference);
        ++count[difference];
        sum += absoluteDifference;
    }
    int median = sum / 2;
    std::vector<SumInfo> subsetWithSum(median + 1);
    subsetWithSum[0].canSum = true;
    for (int j = 0; j < 12; ++j) {
        int value = numbers[j];
        int sumand = std::abs(value);
        for (int i = 0; i < (int)subsetWithSum.size()-sumand; ++i) {
            if (subsetWithSum[i].canSum && !subsetWithSum[i+sumand].canSum) {
                if (subsetWithSum[i].count[value+6] < count[value]) {
                    subsetWithSum[i+sumand] = subsetWithSum[i];
                    ++subsetWithSum[i+sumand].count[value+6];
                }
            }
        }
    }
    int smallestReverses = 0;
    for (int i = median; i > 0; --i) {
        if (subsetWithSum[i].canSum) {
            int negatives = 0;
            for (int j = 0; j < 6; ++j) {
                negatives += subsetWithSum[i].count[j];
            }
            int positives = 0;
            for (int j = 7; j < 13; ++j) {
                positives += subsetWithSum[i].count[j];
            }
            smallestReverses = std::min(negCount-negatives+positives, posCount-positives+negatives);
            break;
        }
    }
    std::reverse(numbers.begin(), numbers.end());
    subsetWithSum = std::vector<SumInfo>(median + 1);
    subsetWithSum[0].canSum = true;
    for (int j = 0; j < 12; ++j) {
        int value = numbers[j];
        int sumand = std::abs(value);
        for (int i = 0; i < (int)subsetWithSum.size()-sumand; ++i) {
            if (subsetWithSum[i].canSum && !subsetWithSum[i+sumand].canSum) {
                if (subsetWithSum[i].count[value+6] < count[value]) {
                    subsetWithSum[i+sumand] = subsetWithSum[i];
                    ++subsetWithSum[i+sumand].count[value+6];
                }
            }
        }
    }
    int newSmallestReverses = 0;
    for (int i = median; i > 0; --i) {
        if (subsetWithSum[i].canSum) {
            int negatives = 0;
            for (int j = 0; j < 6; ++j) {
                negatives += subsetWithSum[i].count[j];
            }
            int positives = 0;
            for (int j = 7; j < 13; ++j) {
                positives += subsetWithSum[i].count[j];
            }
            newSmallestReverses = std::min(negCount-negatives+positives,
                                           posCount-positives+negatives);
            break;
        }
    }
    std::ofstream out("out.txt");
    out << std::min(smallestReverses, newSmallestReverses);
    return 0;
}