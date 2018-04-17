#include <iostream>
#include <fstream>
#include <iomanip>

typedef struct {
    unsigned int additionally;
    unsigned int  performance;
    unsigned int term;
} ITEM;

int compare(const void * x1, const void * x2)   // функция сравнения элементов массива
{
    if((*(ITEM*)x1).term - (*(ITEM*)x2).term == 0){
        return (*(ITEM*)x2).additionally - (*(ITEM*)x1).additionally;
    }
    return(*(ITEM*)x1).term - (*(ITEM*)x2).term;
}

class HEAP {
public:
    ITEM *h;
    int  size;

    HEAP(unsigned int n) {
        size = 0;
        h = (ITEM*) malloc( sizeof(ITEM) * n);
    }

    ~HEAP() {
        if(h) free(h);
    }

    int add(ITEM x) {
        h[++size]=x;
        checkup(size);
        return 1;
    }

    int extract_max(ITEM *x) {
        if(size ==0) return 0;
        *x = h[1];
        h[1] = h[size--];
        checkdown(1);
        return 1;
    }

private:
    void checkup(int c) {
        int p;
        p = c / 2;
        if( p == 0 )return;
        if(h[p].additionally < h[c].additionally) {
            ITEM tmp;
            tmp = h[p]; h[p] = h[c]; h[c] = tmp;
            checkup(p);
        }
    }

    void checkdown(int p) {
        int c;
        c = 2*p;
        if( c > size ) return;
        if( c+1 <= size && h[c + 1].additionally > h[c].additionally ) c++;

        if( h[c].additionally > h[p].additionally ) {
            ITEM tmp;
            tmp =  h[c]; h[c] = h[p]; h[p] = tmp;
            checkdown(c);
        }
    }
};

int main() {
    std::ifstream input("lazy.in");
    std::ofstream output("lazy.out");
    unsigned int size;
    input >> size;
    ITEM *mas = new ITEM[size];
    for (int i = 0; i < size; i++) {
        input >> mas[i].additionally >> mas[i].performance >> mas[i].term;
    }
    qsort(mas, size, sizeof(ITEM), compare);
    HEAP heap(size + 1);
    ITEM *item = new ITEM;
    unsigned int time = 0, requiredTime = 0;
    double money = 0;
    if (mas[0].performance > mas[0].term) {
        requiredTime = mas[0].performance - mas[0].term;
        money = requiredTime * 1.0 / mas[0].additionally;
        mas[0].performance = mas[0].term;
    }
    time = mas[0].performance;
    if (mas[0].performance != 0) {
        heap.add(mas[0]);
    }
    for (int i = 1; i < size; i++) {
        if (time + mas[i].performance > mas[i].term) {
            requiredTime = time + mas[i].performance - mas[i].term;
            time = mas[i].term;
            while (requiredTime != 0 && heap.extract_max(item)) {
                if(mas[i].additionally >= item->additionally) {
                    heap.add(*item);
                    break;
                }
                if (requiredTime >= item->performance) {
                    requiredTime -= item->performance;
                    money += item->performance * 1.0 / item->additionally;
                } else {
                    money += requiredTime * 1.0 / item->additionally;
                    item->performance -= requiredTime;
                    requiredTime = 0;
                    heap.add(*item);
                }
            }
            if (requiredTime != 0) {
                money += requiredTime * 1.0 / mas[i].additionally;
                mas[i].performance -= requiredTime;
            }
            if (mas[i].performance != 0) {
                heap.add(mas[i]);
            }
        } else {
            time += mas[i].performance;
            if(mas[i].performance != 0)
                heap.add(mas[i]);
        }
    }
    output << std::fixed << std::setprecision(2) << money;
    return 0;
}