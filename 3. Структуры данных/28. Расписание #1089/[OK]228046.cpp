#include <vector>
#include <unordered_set>
#include <queue>
#include <stack>
#include <fstream>

struct Work{
    std::vector<size_t> prev;
    std::unordered_set<size_t> next;
    int64_t deadline;
    int64_t time;
    size_t index;

    bool operator<(const Work& other) const {
        return deadline < other.deadline;
    }
};


int main() {
    std::ifstream in("input.txt");
    std::vector<Work> works;
    size_t numberOfWorks;
    in >> numberOfWorks;
    int64_t totalTime = 0;
    for (size_t i = 0; i < numberOfWorks; ++i) {
        Work work;
        work.index = i;
        in >> work.time >> work.deadline;
        totalTime += work.time;
        works.push_back(work);
    }
    size_t m;
    in >> m;
    for (size_t i = 0; i < m; ++i) {
        size_t first, second;
        in >> first >> second;
        --first;
        --second;
        works[first].next.insert(second);
        works[second].prev.push_back(first);
    }
    std::priority_queue<Work> queue;
    for (auto &work : works) {
        if (work.next.empty()) {
            queue.push(work);
        }
    }
    std::stack<size_t> order;
    int64_t maxIndex = 0;
    int64_t maxValue = 0;
    while(!queue.empty()) {
        Work current = queue.top();
        size_t currentIndex = current.index;
        queue.pop();
        for (auto prevWork : current.prev) {
            works[prevWork].next.erase(currentIndex);
            if (works[prevWork].next.empty()) {
                queue.push(works[prevWork]);
            }
        }
        int64_t currentValue = totalTime - current.deadline;
        if (currentValue > maxValue) {
            maxValue = currentValue;
            maxIndex = currentIndex;
        }
        totalTime -= current.time;
        order.push(currentIndex);
    }
    std::ofstream out("output.txt");
    out << maxIndex + 1 << " " << maxValue << "\n";
    while (!order.empty()) {
        out << order.top() + 1 << "\n";
        order.pop();
    }
    out.close();
    in.close();
    return 0;
}