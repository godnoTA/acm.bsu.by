#include <iostream>
#include <fstream>

using namespace std;

const int n = 49;
bool brick[n] ={0};
bool label1,label2,label3,label4,label5,label6,label7;
bool label11,label22,label33,label44,label55,label66,label77;
int bricks[n][2] = { { 0,0 },{ 1,1 },{ 2,2 },{ 3,3 },{ 4,4 },{ 5,5 },{ 6,6 },
                     { 0,1 }, { 1,0 },{ 0,2 },{ 2,0 },{ 0,3 }, { 3,0 },
                     { 0,4 },{ 4,0 },{ 0,5 }, { 5,0 },{ 0,6 }, { 6,0 },
                     { 1,2 },{ 2,1 },{ 1,3 },{ 3,1 },{ 1,4 }, { 4,1 }, { 1,5 },{ 5,1 },{ 1,6 }, { 6,1 },
                     { 2,3 }, { 3,2 },{ 2,4 },{ 4,2 },{ 2,5 },{ 5,2 },{ 2,6 }, { 6,2 },
                     { 3,4 }, { 4,3 },{ 3,5 },{ 5,3 },{ 3,6 },{ 6,3 },
                     { 4,5 }, { 5,4 },{ 4,6 },{ 6,4 }, { 5,6 }, { 6,5 }
};
int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt",  "w", stdout);


    int count = 0, counter= 0;
    for (int i1 = 0; i1 < n; i1++) {
        if (i1<7)
            brick[i1]= true;
        else{
            label1= true;
            if (i1%2)
            {
                label11= true;
                brick[i1]=true;
                brick[i1+1]=true;
            }
            else{
                label11=false;
                brick[i1]=true;
                brick[i1-1]=true;
            }
        }
        for (int i2 = 0; i2 < n; i2++) {
            if (brick[i2] )
                continue;
            count = bricks[i1][0]+bricks[i1][1] + bricks[i2][0]+bricks[i2][1];

            if (i2<7)
                brick[i2]= true;
            else{
                label2= true;
                if (i2%2)
                {
                    label22= true;
                    brick[i2]=true;
                    brick[i2+1]=true;
                }
                else{
                    label22=false;
                    brick[i2]=true;
                    brick[i2-1]=true;
                }
            }
            for (int i3 = 0; i3 < n; i3++) {
                if (brick[i3] )
                    continue;

                if(bricks[i1][0] + bricks[i3][1] > count)
                    continue;
                if(bricks[i1][0] + bricks[i3][0] > count )
                    continue;
                if( bricks[i1][1] + bricks[i3][1]> count)
                    continue;
                if (i3<7)
                    brick[i3]= true;
                else{
                    label3= true;
                    if (i3%2)
                    {
                        label33= true;
                        brick[i3]=true;
                        brick[i3+1]=true;
                    }
                    else{
                        label33=false;
                        brick[i3]=true;
                        brick[i3-1]=true;
                    }
                }

                for (int i4 = 0; i4 < n; i4++) {
                    if (brick[i4] )
                        continue;

                    if(bricks[i1][0] + bricks[i3][0]+bricks[i4][0] > count )
                        continue;
                    if (bricks[i2][1]+bricks[i4][1]>count)
                        continue;
                    if (bricks[i1][1] + bricks[i3][1]+bricks[i4][1]> count)
                        continue;
                    if (i4<7)
                        brick[i4]= true;
                    else{
                        label4= true;
                        if (i4%2)
                        {
                            label44= true;
                            brick[i4]=true;
                            brick[i4+1]=true;
                        }
                        else{
                            label44=false;
                            brick[i4]=true;
                            brick[i4-1]=true;
                        }
                    }

                    for (int i5 = 0; i5 < n; i5++) {
                        if (brick[i5])
                            continue;

                        if (bricks[i1][0] + bricks[i3][0]+bricks[i4][0]+bricks[i5][0] != count)
                            continue;
                        if (bricks[i1][1] + bricks[i3][1]+bricks[i4][1]+bricks[i5][1] != count)
                            continue;
                        if (bricks[i2][1] + bricks[i5][0]+bricks[i4][1] > count)
                            continue;
                        if (i5<7)
                            brick[i5]= true;
                        else{
                            label5= true;
                            if (i5%2)
                            {
                                label55= true;
                                brick[i5]=true;
                                brick[i5+1]=true;
                            }
                            else{
                                label55=false;
                                brick[i5]=true;
                                brick[i5-1]=true;
                            }
                        }

                        for (int i6 = 0; i6 < n; i6++) {
                            if (brick[i6] )
                                continue;

                            if (bricks[i3][0]+bricks[i3][1] + bricks[i6][0]+bricks[i6][1] != count)
                                continue;
                            if (bricks[i2][1] + bricks[i6][0] + bricks[i5][0]+bricks[i4][1]!= count)
                                continue;
                            if(bricks[i2][1] + bricks[i6][1] > count)
                                continue;
                            if (bricks[i2][0] + bricks[i6][0] > count)
                                continue;
                            if (i6<7)
                                brick[i6]= true;
                            else{
                                label6= true;
                                if (i6%2)
                                {
                                    label66= true;
                                    brick[i6]=true;
                                    brick[i6+1]=true;
                                }
                                else{
                                    label66=false;
                                    brick[i6]=true;
                                    brick[i6-1]=true;
                                }
                            }

                            for (int i7 = 0; i7 < n; i7++) {
                                if (brick[i7])
                                    continue;


                                if (bricks[i1][0] + bricks[i3][1] + bricks[i7][0] > count)
                                    continue;
                                if(bricks[i2][1] + bricks[i6][1]+bricks[i7][1] > count)
                                    continue;
                                if (bricks[i2][0] + bricks[i6][0]+bricks[i7][0] > count)
                                    continue;
                                if (bricks[i4][0]+bricks[i4][1] + bricks[i7][0]+bricks[i7][1] != count)
                                    continue;

                                if (i7<7)
                                    brick[i7]= true;
                                else{
                                    label7= true;
                                    if (i7%2)
                                    {
                                        label77= true;
                                        brick[i7]=true;
                                        brick[i7+1]=true;
                                    }
                                    else{
                                        label77=false;
                                        brick[i7]=true;
                                        brick[i7-1]=true;
                                    }
                                }

                                for (int i8 = 0; i8 < n; i8++) {
                                    if (brick[i8])
                                        continue;
                                    if (bricks[i5][0]+bricks[i5][1] + bricks[i8][0]+bricks[i8][1] != count)
                                        continue;
                                    if (bricks[i2][1] + bricks[i6][1] + bricks[i7][1] + bricks[i8][1] != count)
                                        continue;
                                    if (bricks[i2][0] + bricks[i7][0] + bricks[i6][0] + bricks[i8][0] != count)
                                        continue;
                                    if (bricks[i3][1] + bricks[i1][0] + bricks[i8][1] + bricks[i7][0] != count)
                                        continue;

                                    counter++;
                                    printf("--------\n");
                                    printf("%d %d %d %d\n",bricks[i1][0], bricks[i1][1],bricks[i2][0],bricks[i2][1]);
                                    printf("%d %d %d %d\n",bricks[i3][0], bricks[i3][1],bricks[i6][0],bricks[i6][1]);
                                    printf("%d %d %d %d\n",bricks[i4][0], bricks[i4][1],bricks[i7][0],bricks[i7][1]);
                                    printf("%d %d %d %d\n",bricks[i5][0], bricks[i5][1],bricks[i8][0],bricks[i8][1]);
                                }
                                brick[i7] = false;
                                if(label7)
                                {
                                    if (!label77)
                                        brick[i7-1]=false;
                                    else
                                        brick[i7+1]=false;
                                }
                                label7 =false;
                            }
                            brick[i6]  = false;
                            if(label6)
                            {
                                if (!label66)
                                    brick[i6-1]=false;
                                else
                                    brick[i6+1]=false;
                            }
                            label6 =false;
                        }
                        brick[i5] = false;
                        if(label5)
                        {
                            if (!label55)
                                brick[i5-1]=false;
                            else
                                brick[i5+1]=false;
                        }
                        label5 =false;
                    }
                    brick[i4]  = false;
                    if(label4)
                    {
                        if (!label44)
                            brick[i4-1]=false;
                        else
                            brick[i4+1]=false;
                    }
                    label4 =false;
                }
                brick[i3] = false;
                if(label3)
                {
                    if (!label33)
                        brick[i3-1]=false;
                    else
                        brick[i3+1]=false;
                }
                label3 =false;
            }
            brick[i2] = false;
            if(label2)
            {
                if (!label22)
                    brick[i2-1]=false;
                else
                    brick[i2+1]=false;
            }
            label2 =false;
        }
        brick[i1]  = false;
        if(label1)
        {
            if (!label11)
                brick[i1-1]=false;
            else
                brick[i1+1]=false;
        }
        label1 =false;
    }
    printf("Count=%d",counter);
    return 0;
}