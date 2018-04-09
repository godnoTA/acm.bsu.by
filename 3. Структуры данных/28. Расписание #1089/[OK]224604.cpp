#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <algorithm>

int main() {
    std::ifstream fin("input.txt");
    int m, n;

    fin >> n;//quantity of jobs

    std::vector<int> sequence(n, 0);//contains optimal sequence
    std::vector<long long> processTimes(n, 0ll);//contains process time for each job
    std::vector<long long> deadlines(n, 0ll);//contains deadline for each job

    //getting process time and deadline for each job
    for (int i = 0; i < n; ++i) {
        fin >> processTimes[i];
        fin >> deadlines[i];
    }

    fin >> m;//quantity of job orders

    std::set<int> fromToMatrix[n];//contains job orders from -> to
    std::vector<int> toFromMatrix[n];//contains job orders to -> from

    int from, to;
    for (int i = 0; i < m; ++i) {
        fin >> from;
        fin >> to;
        fromToMatrix[--from].insert(--to);
        toFromMatrix[to].push_back(from);
    }

    fin.close();

    int place = 0;

    std::set< std::pair<long long, int> > candidatesForSequence; //sorted by decreasing deadline, contains number of job and its deadline

    //inserts candidates with zero jobs after and these candidates automatically sorted by decreasing deadlines
    for (int i = 0; i < n; ++i)
        if (fromToMatrix[i].empty())
            candidatesForSequence.insert(std::make_pair(-deadlines[i], i));

    while (place < n) {

        //get the job which we'll be made (from last to the first)
        sequence[place] = candidatesForSequence.begin()->second;
        candidatesForSequence.erase(candidatesForSequence.begin());

        //we have deleted job and now we have to delete it from fromToMatrix
        //we run through toFromMatrix because it contains numbers of jobs which we have to delete the job from
        for (auto it : toFromMatrix[sequence[place]]) {
                fromToMatrix[it].erase(sequence[place]);
            //if all jobs after it are processed, it becomes the candidate
            if (fromToMatrix[it].empty()) {
                candidatesForSequence.insert(std::make_pair(-deadlines[it], it));
            }
        }

        ++place; //iterates to the next member of sequence
    }

    long long c = 0ll, maximum = -1ll;
    int maximumIndex = -1;
    long long t;

    //finds job with max fee and its index from the optimal sequence
    for (int i = --place; i >= 0; --i) {
        c += processTimes[sequence[i]];
        t = std::max(c - deadlines[sequence[i]], 0ll);
        if (t >= maximum) {
            maximum = t;
            maximumIndex = sequence[i];
        }
    }

    std::ofstream fout("output.txt");

    //outputs job's index and max fee
    fout << ++maximumIndex << " " << maximum << std::endl;

    //outputs optimal sequence
    for (int i = place; i > 0; --i)
        fout << sequence[i] + 1 << std::endl;
    fout << sequence[0] + 1;

    fout.close();
    return 0;
}