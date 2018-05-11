//
// Created by aleksei on 27/04/18.
//

#include <cstring>
#include <fstream>
#include <vector>
// #include "zxc.h"


struct domino{
    int x;
    int y;
    domino(int x_, int y_): x(x_), y(y_) {
    }
};



int main(int argc, char* argv[]){

   // std::unique_ptr a;
   // run();

   // printf("%d", fff);

  // std::string str;*/

   // std::ofstream of("output.txt");

   // std::vector<domino> dominoes;

    int dominoes[28][2];



    FILE *output = fopen("output.txt", "w");



    /*for (int i = 0; i <= 6; i++){
        for (int j = i; j <= 6; j++){
            dominoes.push_back(domino(i, j));
        }
    }*/

    auto cnt = 0;

    for (int i = 0; i <= 6; i++){
        for (int j = i; j <= 6; j++){
            dominoes[cnt][0] = i;
            dominoes[cnt][1] = j;
            cnt++;
        }
    }


   // printf("%d", dominoes.size());
    long long s = 0;
    auto count = 0;


    for (int sum = 5; sum < 13; sum++) {

        for (int i1 = 0; i1 < 28; i1++) {
            auto s11 = dominoes[i1][0];
            auto s12 = dominoes[i1][1];

            auto is_swapped1 = false;


            mark1:

           // if (s11 == 3 && s12 == 2) std::cout << "Yes\n";

            // s++;
            for (int i2 = 0; i2 < 28; i2++) {

                if (i2 == i1) continue;
                auto s21 = dominoes[i2][0];
                auto s22 = dominoes[i2][1];
                auto is_swapped2 = false;


                auto r1 = s11 + s12 + s21 + s22;
               // if (r1 < 6 || r1 > 18) continue;

                mark2:
                if (r1 != sum) {
                    if (!is_swapped2) goto mark21;
                    else continue;
                }



                // if (s21 == 0 && s22 == 0 &&s11 == 3 && s12 == 2) std::cout << "Yes\n";

             //   s++;
                for (int i3 = 0; i3 < 28; i3++) {
                    if (i3 == i2 || i3 == i1) continue;

                    auto s31 = dominoes[i3][0];
                    auto s32 = dominoes[i3][1];
                    auto is_swapped3 = false;

                    mark3:

                 //   s++;
                    for (int i4 = 0; i4 < 28; i4++) {
                        if (i4 == i3 || i4 == i2 || i4 == i1) continue;

                        auto s41 = dominoes[i4][0];
                        auto s42 = dominoes[i4][1];

                        auto r2 = s31 + s32 + s41 + s42;

                        auto is_swapped4 = false;

                       // if (r2 < 6 || r2 > 18) continue;


                        mark4:


                       // if (s11 + s12 + s21 + s22 != s31 + s32 + s41 + s42) continue;
                        if (r2 != r1) {
                            if (!is_swapped4) goto mark41;
                            else continue;
                        }




                    //    s++;
                        for (int i5 = 0; i5 < 28; i5++) {
                            if (i5 == i4 || i5 == i3 || i5 == i2 || i5 == i1) continue;

                            auto s51 = dominoes[i5][0];
                            auto s52 = dominoes[i5][1];
                            auto is_swapped5 = false;

                            mark5:

                         //   s++;
                            for (int i6 = 0; i6 < 28; i6++) {
                                if (i6 == i5 || i6 == i4 || i6 == i3 || i6 == i2 || i6 == i1) continue;

                                auto s61 = dominoes[i6][0];
                                auto s62 = dominoes[i6][1];
                                auto is_swapped6 = false;

                                auto r3 = s51 + s52 + s61 + s62;

                                mark6:

                                // if (s51 + s52 + s61 + s62 != s31 + s32 + s41 + s42) continue;
                                if (r3 != r2) {
                                    if (!is_swapped6) goto mark62;
                                    else continue;
                                }


                             //   if (r3 < 6 || r3 > 18) continue;

                              //  s++;



                                for (int i7 = 0; i7 < 28; i7++) {
                                    if (i7 == i6 || i7 == i5 || i7 == i4 || i7 == i3 || i7 == i2 || i7 == i1) continue;


                                    auto s71 = dominoes[i7][0];
                                    auto s72 = dominoes[i7][1];
                                    auto is_swapped7 = false;


                                    mark7:


                                    auto c1 = s11 + s31 + s51 + s71;
                                    auto c2 = s12 + s32 + s52 + s72;
                                    auto s_d = s22 + s41 + s52 + s71;


                                    if (c1 != s12 + s32 + s52 + s72) {
                                        if (!is_swapped7) goto mark72;
                                        else continue;
                                    }
                                    // if (s11 + s31 + s51 + s71 != s12 + s32 + s52 + s72) continue; // vert
                                    if (c1 != r3) {
                                        if (!is_swapped7) goto mark72;
                                        else continue;
                                    }
                                    // if (s11 + s31 + s51 + s71 != s51 + s52 + s61 + s62) continue; // hor
                                    if (s_d != r3) {
                                        if (!is_swapped7) goto mark72;
                                        else continue;
                                    }
                                    // if (s22 + s41 + s52 + s71 != s51 + s52 + s61 + s62) continue; // second diag

                                  //  if (c1 < 6 || c1 > 18) continue;
                                 //   if (c2 < 6 || c2 > 18) continue;
                                  //  if (s_d < 6 || s_d > 18) continue;

                              //      s++;
                                    for (int i8 = 0; i8 < 28; i8++) {
                                        if (i8 == i7 || i8 == i6 || i8 == i5 || i8 == i4 || i8 == i3 || i8 == i2 ||
                                            i8 == i1)
                                            continue;


                                        auto s81 = dominoes[i8][0];
                                        auto s82 = dominoes[i8][1];
                                        auto is_swapped8 = false;

                                        mark8:

                                        auto c3 = s21 + s41 + s61 + s81;

                                        //mark8:

                                        if (s81 + s82 + s71 + s72 != r3){
                                            if (!is_swapped8) goto mark81;
                                            else continue;
                                        };
                                        //  if (s81 + s82 + s71 + s72 != s51 + s52 + s61 + s62) continue; // hor
                                        if (c3 != s22 + s42 + s62 + s82) {
                                            if (!is_swapped8) goto mark81;
                                            else continue;
                                        }
                                        // if (s21 + s41 + s61 + s81 != s22 + s42 + s62 + s82) continue; // vert
                                        if (c3 != r3){
                                            if (!is_swapped8) goto mark81;
                                            else continue;
                                        }
                                        // if (s21 + s41 + s61 + s81 != s51 + s52 + s61 + s62) continue;
                                        if (s_d != s11 + s32 + s61 + s82){
                                            if (!is_swapped8) goto mark81;
                                            else continue;
                                        }
                                        // if (s22 + s41 + s52 + s71 != s11 + s32 + s61 + s82) continue; // sec == prim

                                        /*char *ch = new char[60];
                                        int x = sprintf(ch,
                                                        "--------\n%d %d %d %d\n%d %d %d %d\n%d %d %d %d\n%d %d %d %d\n",
                                                        s11, s12, s21, s22, s31, s32, s41, s42, s51, s52, s61, s62, s71,
                                                        s72, s81, s82);

                                        str += ch;*/
                                         fprintf(output, "--------\n%d %d %d %d\n%d %d %d %d\n%d %d %d %d\n%d %d %d %d\n",
                                         s11, s12, s21, s22, s31, s32, s41, s42, s51, s52, s61, s62, s71, s72, s81, s82);

                                         if (sum != 12){
                                             fprintf(output, "--------\n%d %d %d %d\n%d %d %d %d\n%d %d %d %d\n%d %d %d %d\n",
                                             6 - s11, 6 - s12, 6 - s21, 6 - s22, 6 - s31, 6 - s32,
                                             6 - s41, 6 - s42, 6 - s51, 6 - s52, 6 - s61, 6 - s62,
                                             6 - s71, 6 - s72, 6 - s81, 6 - s82);
                                             count++;
                                         }

                                        count++;


                                        mark81:
                                        if (s81 != s82) {
                                            if (!is_swapped8) {
                                                std::swap(s81, s82);
                                                is_swapped8 = true;
                                                goto mark8;
                                            }
                                        }

                                    }

                                    mark72:
                                    if (s71 != s72) {

                                        if (!is_swapped7) {
                                            std::swap(s71, s72);
                                            is_swapped7 = true;
                                            goto mark7;
                                        }
                                    }

                                }

                                mark62:
                                if (s61 != s62) {
                                    if (!is_swapped6) {
                                        std::swap(s61, s62);
                                        is_swapped6 = true;
                                        goto mark6;
                                    }
                                }
                            }

                            mark51:
                            if (s51 != s52) {
                                if (!is_swapped5) {
                                    std::swap(s51, s52);
                                    is_swapped5 = true;
                                    goto mark5;
                                }
                            }

                        }

                        mark41:
                        if (s41 != s42) {
                            if (!is_swapped4) {
                                std::swap(s41, s42);
                                is_swapped4 = true;
                                goto mark4;
                            }
                        }

                    }

                    mark31:
                    if (s31 != s32) {
                        if (!is_swapped3) {
                            std::swap(s31, s32);
                            is_swapped3 = true;
                            goto mark3;
                        }
                    }

                }

                mark21:
                if (s21 != s22) {
                    if (!is_swapped2) {
                        std::swap(s21, s22);
                        is_swapped2 = true;
                        goto mark2;
                    }
                }

            }


            mark11:
            if (s11 != s12) {
                if (!is_swapped1) {
                    std::swap(s11, s12);
                    is_swapped1 = true;
                    goto mark1;
                }
            }

        }
    }

    fprintf(output, "Count=%d", count);

  //  of << str << "Count=" << count;
   // printf("%d", s);
    //fclose(output);

}