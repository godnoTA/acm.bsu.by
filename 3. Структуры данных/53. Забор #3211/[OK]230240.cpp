#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <stdio.h>
#include <time.h>

using namespace std;

const int Max = 1048576;
vector<bool> vec_left1(2 * Max);
vector<bool> vec_right1(2 * Max);
vector<bool> vec_left2(2 * Max);
vector<bool> vec_right2(2 * Max);
vector<bool> label1(2 * Max);
vector<bool> label2(2 * Max);
vector<bool> label(2 * Max);
struct dat {
    int sum, pref, suf;
};
struct datBig {
    dat some;
    bool left, right, lab;
};
dat t[2 * Max];
dat d[2 * Max];
dat combine(dat l, dat r, int left, int right, int res, int lab) {
    dat result;
    if (lab == 1) {
        if (vec_left1[left]) {
            if (vec_right1[left]) {
                if (vec_left1[right]) {
                    if (vec_right1[right]) {
                        if (label1[left]) {
                            if (label1[right]) {
                                result.sum = (max(l.sum + r.sum, r.pref + l.suf));
                                result.suf = l.suf + r.suf;
                                result.pref = l.pref + r.pref;
                                vec_left1[res] = true;
                                vec_right1[res] = true;
                                label1[res] = true;
                                return result;
                            } else {
                                result.sum = max(max(l.sum, r.sum),  (max(l.suf+r.pref, r.suf)));
                                result.suf = r.suf;
                                result.pref = l.pref + r.pref;
                                vec_left1[res] = true;
                                vec_right1[res] = true;
                                label1[res] = false;
                                return result;
                            }
                        } else {
                            if (label1[right]) {
                                result.sum = max(max(l.sum, r.sum),(max(l.pref, r.pref + l.suf)));
                                result.suf = r.suf + l.suf;
                                result.pref = l.pref;
                                vec_left1[res] = true;
                                vec_right1[res] = true;
                                label1[res] = false;
                                return result;
                            } else {
                                result.sum = max(max(l.sum, r.sum), max(r.suf, (max(l.pref, r.pref + l.suf))));
                                result.suf = r.suf;
                                result.pref = l.pref;
                                vec_left1[res] = true;
                                vec_right1[res] = true;
                                label1[res] = false;
                                return result;
                            }
                        }
                    } else {
                            if (label1[left]) {
                                result.sum = max(max(l.sum, r.sum), l.suf + r.pref);
                                result.pref = l.pref + r.pref;
                                result.suf = 0;
                                vec_left1[res] = true;
                                vec_right1[res] = false;
                                label1[res] = false;
                                return result;
                            } else {
                                result.sum = max(max(l.sum, r.sum), max(l.pref,l.suf + r.pref));
                                result.pref = l.pref ;
                                result.suf = 0;
                                vec_left1[res] = true;
                                vec_right1[res] = false;
                                label1[res] = false;
                                return result;
                            }
                    }
                } else {
                    if (vec_right1[right]) {
                        result.sum = max(l.suf, max(max(l.sum, r.suf), max(l.pref, r.sum)));
                        result.pref = l.pref;
                        result.suf = r.suf;
                        vec_left1[res] = true;
                        vec_right1[res] = true;
                        label1[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.sum, r.sum), max(l.suf, l.pref));
                        result.pref = l.pref;
                        result.suf = 0;
                        vec_left1[res] = true;
                        vec_right1[res] = false;
                        label1[res] = false;
                        return result;
                    }
                }
            } else {
                if (vec_left1[right]) {
                    if (vec_right1[right]) {
                        result.sum = max(r.pref, max(max(l.pref, r.sum), max(r.suf, l.sum)));
                        result.pref = l.pref;
                        result.suf = r.suf;
                        vec_left1[res] = true;
                        vec_right1[res] = true;
                        label1[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.pref, r.pref), max(l.sum, r.sum));
                        result.pref = l.pref;
                        result.suf = 0;
                        vec_left1[res] = true;
                        vec_right1[res] = false;
                        label1[res] = false;
                        return result;
                    }
                } else {
                    if (vec_right1[right]) {
                        result.sum = max(max(l.pref, r.suf), max(l.sum, r.sum));
                        result.pref = l.pref;
                        result.suf = r.suf;
                        vec_left1[res] = true;
                        vec_right1[res] = true;
                        label1[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.sum, l.pref), r.sum);
                        result.pref = l.pref;
                        result.suf = 0;
                        vec_left1[res] = true;
                        vec_right1[res] = false;
                        label1[res] = false;
                        return result;
                    }
                }
            }
        } else {
            if (vec_right1[left]) {
                if (vec_left1[right]) {
                    if (vec_right1[right]) {
                        if (label1[right]) {
                            result.sum = max(max(r.sum, l.sum), l.suf + r.pref);
                            result.suf = l.suf + r.pref;
                            result.pref = 0;
                            vec_left1[res] = false;
                            vec_right1[res] = true;
                            label1[res] = false;
                            return result;
                        } else {
                            result.sum = max(max(r.sum, l.sum), max(r.suf, l.suf + r.pref));
                            result.suf = r.suf;
                            result.pref = 0;
                            vec_left1[res] = false;
                            vec_right1[res] = true;
                            label1[res] = false;
                            return result;
                        }
                    } else {
                        result.sum = max(max(l.suf + r.pref, l.sum), r.sum);
                        result.pref = 0;
                        result.suf = 0;
                        vec_left1[res] = false;
                        vec_right1[res] = false;
                        label1[res] = false;
                        return result;
                    }
                } else {
                    if (vec_right1[right]) {
                        result.sum = max(max(l.suf, r.suf), max(l.sum, r.sum));
                        result.pref = 0;
                        result.suf = r.suf;
                        vec_left1[res] = false;
                        vec_right1[res] = true;
                        label1[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.suf, r.sum), l.sum);
                        result.pref = 0;
                        result.suf = 0;
                        vec_left1[res] = false;
                        vec_right1[res] = false;
                        label1[res] = false;
                        return result;
                    }
                }
            } else {
                if (vec_left1[right]) {
                    if (vec_right1[right]) {
                        result.sum = max(max(r.suf, r.pref), max(r.sum, l.sum));
                        result.pref = 0;
                        result.suf = r.suf;
                        vec_left1[res] = false;
                        vec_right1[res] = true;
                        label1[res] = false;
                        return result;
                    } else {
                        result.sum = max(r.sum, max(l.sum, r.pref));
                        result.pref = 0;
                        result.suf = 0;
                        vec_left1[res] = false;
                        vec_right1[res] = false;
                        label1[res] = false;
                        return result;
                    }
                } else {
                    if (vec_right1[right]) {
                        result.sum = max(max(r.sum, r.suf), l.sum);
                        result.pref = 0;
                        result.suf = r.suf;
                        vec_left1[res] = false;
                        vec_right1[res] = true;
                        label1[res] = false;
                        return result;
                    } else {
                            result.sum = max(l.sum, r.sum);
                            result.pref = 0;
                            result.suf = 0;
                            vec_left1[res] = false;
                            vec_right1[res] = false;
                            label1[res] = false;
                            return result;
                    }
                }
            }
        }
    }
    if (lab == 2) {
        if (vec_left2[left]) {
            if (vec_right2[left]) {
                if (vec_left2[right]) {
                    if (vec_right2[right]) {
                        if (label2[left]) {
                            if (label2[right]) {
                                result.sum = (max(l.sum + r.sum, r.pref + l.pref));
                                result.suf = l.suf + r.suf;
                                result.pref = l.pref + r.pref;
                                vec_left2[res] = true;
                                vec_right2[res] = true;
                                label2[res] = true;
                                return result;
                            } else {
                                result.sum = max(max(l.sum, r.sum), max(r.suf,l.suf+r.pref));
                                result.suf = r.suf;
                                result.pref = l.suf + r.pref;
                                vec_left2[res] = true;
                                vec_right2[res] = true;
                                label2[res] = false;
                                return result;
                            }
                        } else {
                            if (label2[right]) {
                                result.sum = max(max(l.sum, r.sum),max(l.pref, r.pref + l.suf));
                                result.suf = r.pref + l.suf;
                                result.pref = l.pref;
                                vec_left2[res] = true;
                                vec_right2[res] = true;
                                label2[res] = false;
                                return result;
                            } else {
                                result.sum = max(max(l.sum, r.sum), max(r.suf, (max(l.pref, r.pref + l.suf))));
                                result.suf = r.suf;
                                result.pref = l.pref;
                                vec_left2[res] = true;
                                vec_right2[res] = true;
                                label2[res] = false;
                                return result;
                            }
                        }
                    } else {
                        {
                            if (label2[left]) {
                                result.sum = max(max(l.sum, r.sum), l.suf + r.pref);
                                result.pref = l.pref + r.pref;
                                result.suf = 0;
                                vec_left2[res] = true;
                                vec_right2[res] = false;
                                label2[res] = false;
                                return result;
                            } else {
                                result.sum = max(max(l.sum, r.sum), max(l.pref,l.suf + r.pref));
                                result.pref = l.pref;
                                result.suf = 0;
                                vec_left2[res] = true;
                                vec_right2[res] = false;
                                label2[res] = false;
                                return result;
                            }
                        }
                    }
                } else {
                    if (vec_right2[right]) {
                        result.sum = max(l.suf, max(max(l.sum, r.suf), max(l.pref, r.sum)));
                        result.pref = l.pref;
                        result.suf = r.suf;
                        vec_left2[res] = true;
                        vec_right2[res] = true;
                        label2[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.sum, r.sum), max(l.suf, l.pref));
                        result.pref = l.pref;
                        result.suf = 0;
                        vec_left2[res] = true;
                        vec_right2[res] = false;
                        label2[res] = false;
                        return result;
                    }
                }
            } else {
                if (vec_left2[right]) {
                    if (vec_right2[right]) {
                        result.sum = max(r.pref, max(max(l.pref, r.sum), max(r.suf, l.sum)));
                        result.pref = l.pref;
                        result.suf = r.suf;
                        vec_left2[res] = true;
                        vec_right2[res] = true;
                        label2[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.pref, r.pref), max(l.sum, r.sum));
                        result.pref = l.pref;
                        result.suf = 0;
                        vec_left2[res] = true;
                        vec_right2[res] = false;
                        label2[res] = false;
                        return result;
                    }
                } else {
                    if (vec_right2[right]) {
                        result.sum = max(max(l.pref, r.suf), max(l.sum, r.sum));
                        result.pref = l.pref;
                        result.suf = r.suf;
                        vec_left2[res] = true;
                        vec_right2[res] = true;
                        label2[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.sum, l.pref), r.sum);
                        result.pref = l.pref;
                        result.suf = 0;
                        vec_left2[res] = true;
                        vec_right2[res] = false;
                        label2[res] = false;
                        return result;
                    }
                }
            }
        } else {
            if (vec_right2[left]) {
                if (vec_left2[right]) {
                    if (vec_right2[right]) {
                        if (label2[right]) {
                            result.sum = max(max(r.sum, l.sum), l.suf + r.pref);
                            result.suf = l.suf + r.pref;
                            result.pref = 0;
                            vec_left2[res] = false;
                            vec_right2[res] = true;
                            label2[res] = false;
                            return result;
                        } else {
                            result.sum = max(max(r.sum, l.sum), max(r.suf, l.suf + r.pref));
                            result.suf = r.suf;
                            result.pref = 0;
                            vec_left2[res] = false;
                            vec_right2[res] = true;
                            label2[res] = false;
                            return result;
                        }
                    } else {
                        result.sum = max(max(l.suf + r.pref, l.sum), r.sum);
                        result.pref = 0;
                        result.suf = 0;
                        vec_left2[res] = false;
                        vec_right2[res] = false;
                        label2[res] = false;
                        return result;
                    }
                } else {
                    if (vec_right2[right]) {
                        result.sum = max(max(l.suf, r.suf), max(l.sum, r.sum));
                        result.pref = 0;
                        result.suf = r.suf;
                        vec_left2[res] = false;
                        vec_right2[res] = true;
                        label2[res] = false;
                        return result;
                    } else {
                        result.sum = max(max(l.suf, r.sum), l.sum);
                        result.pref = 0;
                        result.suf = 0;
                        vec_left2[res] = false;
                        vec_right2[res] = false;
                        label2[res] = false;
                        return result;
                    }
                }
            } else {
                if (vec_left2[right]) {
                    if (vec_right2[right]) {
                        result.sum = max(max(r.suf, r.pref), max(r.sum, l.sum));
                        result.pref = 0;
                        result.suf = r.suf;
                        vec_left2[res] = false;
                        vec_right2[res] = true;
                        label2[res] = false;
                        return result;
                    } else {
                        result.sum = max(r.sum, max(l.sum, r.pref));
                        result.pref = 0;
                        result.suf = 0;
                        vec_left2[res] = false;
                        vec_right2[res] = false;
                        label2[res] = false;
                        return result;
                    }
                } else {
                    if (vec_right2[right]) {
                        result.sum = max(max(r.sum, r.suf), l.sum);
                        result.pref = 0;
                        result.suf = r.suf;
                        vec_left2[res] = false;
                        vec_right2[res] = true;
                        label2[res] = false;
                        return result;
                    } else {
                        result.sum = max(l.sum, r.sum);
                        result.pref = 0;
                        result.suf = 0;
                        vec_left2[res] = false;
                        vec_right2[res] = false;
                        label2[res] = false;
                        return result;
                    }
                }
            }
        }
    }
}
datBig combineBig(datBig l, datBig r) {
    datBig result;
    if (l.left) {
        if (l.right) {
            if (r.left) {
                if (r.right) {
                    if (l.lab) {
                        if (r.lab) {
                            result.some.sum = (max(l.some.sum + r.some.sum, r.some.pref + l.some.suf));
                            result.some.suf = l.some.suf + r.some.suf;
                            result.some.pref = l.some.pref + r.some.pref;
                            result.left = true;
                            result.right = true;
                            result.lab = true;
                            return result;
                        } else {
                            result.some.sum =max (max(l.some.sum, r.some.sum), max(r.some.suf, r.some.pref + l.some.suf));
                            result.some.suf = r.some.suf;
                            result.some.pref = l.some.pref + r.some.pref;
                            result.left = true;
                            result.right = true;
                            result.lab = false;
                            return result;
                        }
                    } else {
                        if (r.lab) {
                            result.some.sum = max(max(l.some.sum, r.some.sum), max(l.some.pref, r.some.pref + l.some.suf));
                            result.some.suf = r.some.suf + l.some.suf;
                            result.some.pref = l.some.pref;
                            result.left = true;
                            result.right = true;
                            result.lab = false;
                            return result;
                        } else {
                            result.some.sum = max(max(l.some.sum, r.some.sum), max(r.some.suf, (max(l.some.pref, r.some.pref + l.some.suf))));
                            result.some.suf = r.some.suf;
                            result.some.pref = l.some.pref;
                            result.left = true;
                            result.right = true;
                            result.lab = false;
                            return result;
                        }
                    }
                } else {
                    {
                        if (l.lab) {
                            result.some.sum = max(max(l.some.sum, r.some.sum), l.some.suf + r.some.pref);
                            result.some.pref = l.some.pref + r.some.pref;
                            result.some.suf = 0;
                            result.left = true;
                            result.right = false;
                            result.lab = false;
                            return result;
                        } else {
                            result.some.sum = max(max(l.some.sum, r.some.sum), max(l.some.pref,l.some.suf + r.some.pref));////
                            result.some.pref = l.some.pref;
                            result.some.suf = 0;
                            result.left = true;
                            result.right = false;
                            result.lab = false;
                            return result;
                        }
                    }
                }
            } else {
                if (r.right) {
                    result.some.sum = max(l.some.suf, max(max(l.some.sum, r.some.suf), max(l.some.pref, r.some.sum)));
                    result.some.pref = l.some.pref;
                    result.some.suf = r.some.suf;
                    result.left = true;
                    result.right = true;
                    result.lab = false;
                    return result;
                } else {
                    result.some.sum = max(max(l.some.sum, r.some.sum), max(l.some.suf, l.some.pref));

                    result.some.pref = l.some.pref;
                    result.some.suf = 0;
                    result.left = true;
                    result.right = false;
                    result.lab = false;
                    return result;
                }
            }
        } else {
            if (r.left) {
                if (r.right) {
                    result.some.sum = max(r.some.pref, max(max(l.some.pref, r.some.sum), max(r.some.suf, l.some.sum)));
                    result.some.pref = l.some.pref;
                    result.some.suf = r.some.suf;
                    result.left = true;
                    result.right = true;
                    result.lab = false;
                    return result;
                } else {
                    result.some.sum = max(max(l.some.pref, r.some.pref), max(l.some.sum, r.some.sum));
                    result.some.pref = l.some.pref;
                    result.some.suf = 0;
                    result.left = true;
                    result.right = false;
                    result.lab = false;
                    return result;
                }
            } else {
                if (r.right) {
                    result.some.sum = max(max(l.some.pref, r.some.suf), max(l.some.sum, r.some.sum));
                    result.some.pref = l.some.pref;
                    result.some.suf = r.some.suf;
                    result.left = true;
                    result.right = true;
                    result.lab = false;
                    return result;
                } else {
                    result.some.sum = max(max(l.some.sum, l.some.pref), r.some.sum);
                    result.some.pref = l.some.pref;
                    result.some.suf = 0;
                    result.left = true;
                    result.right = false;
                    result.lab = false;
                    return result;
                }
            }
        }
    } else {
        if (l.right) {
            if (r.left) {
                if (r.right) {
                    if (r.lab) {
                        result.some.sum = max(max(r.some.sum, l.some.sum), l.some.suf + r.some.pref);
                        result.some.suf = l.some.suf + r.some.pref;
                        result.some.pref = 0;
                        result.left = false;
                        result.right = true;
                        result.lab = false;
                        return result;
                    } else {
                        result.some.sum = max(max(r.some.sum, l.some.sum), max(r.some.suf, r.some.pref + l.some.suf));
                        result.some.suf = r.some.suf;
                        result.some.pref = 0;
                        result.left = false;
                        result.right = true;
                        result.lab = false;
                        return result;
                    }
                } else {
                    result.some.sum = max(max(l.some.suf + r.some.pref, l.some.sum), r.some.sum);
                    result.some.pref = 0;
                    result.some.suf = 0;
                    result.left = false;
                    result.right = false;
                    result.lab = false;
                    return result; }
            } else {
                if (r.right) {
                    result.some.sum = max(max(l.some.sum, r.some.sum), max(r.some.suf, l.some.suf));
                    result.some.pref = 0;
                    result.some.suf = r.some.suf;
                    result.left = false;
                    result.right = true;
                    result.lab = false;
                    return result;
                } else {
                    result.some.sum = max(max(l.some.suf, r.some.sum), l.some.sum);
                    result.some.pref = 0;
                    result.some.suf = 0;
                    result.left = false;
                    result.right = false;
                    result.lab = false;
                    return result;
                }
            }
        } else {
            if (r.left) {
                if (r.right) {
                    result.some.sum = max(max(r.some.suf, r.some.pref), max(r.some.sum, l.some.sum));
                    result.some.pref = 0;
                    result.some.suf = r.some.suf;
                    result.left = false;
                    result.right = true;
                    result.lab = false;
                    return result;
                } else {
                    result.some.sum = max(r.some.sum, max(l.some.sum, r.some.pref));
                    result.some.pref = 0;
                    result.some.suf = 0;
                    result.left = false;
                    result.right = false;
                    result.lab = false;
                    return result;
                }
            } else {
                if (r.right) {
                    result.some.sum = max(max(r.some.sum, r.some.suf), l.some.sum);
                    result.some.pref = 0;
                    result.some.suf = r.some.suf;
                    result.left = false;
                    result.right = true;
                    result.lab = false;
                    return result;
                } else {
                    result.some.sum = max(l.some.sum, r.some.sum);
                    result.some.pref = 0;
                    result.some.suf = 0;
                    result.left = false;
                    result.right = false;
                    result.lab = false;
                    return result;
                }
            }
        }
    }

}
inline dat make_dat(int val) {
    dat result;
    result.sum = val;
    result.pref = val;
    result.suf = val;
    return result;
}
inline datBig make_datBig(dat val, bool l, bool r, bool lab) {
    datBig result;
    result.some.sum = val.sum;
    result.some.pref = val.pref;
    result.some.suf = val.suf;
    result.left = l;
    result.right = r;
    result.lab = lab;
    return result;
}
inline datBig make_datBig(int val, bool l, bool r, bool lab) {
    datBig result;
    result.some.sum = val;
    result.some.pref = val;
    result.some.suf = val;
    result.left = l;
    result.right = r;
    result.lab = lab;
    return result;
}
void build() {
    t[0] = make_dat(0);
    d[0] = make_dat(0);
    for (int i = 1; i <= Max; i++) {
        t[Max - 1 + i] = make_dat(0);
        label[Max - 1 + i] = true;
        d[Max - 1 + i] = make_dat(1);
        vec_left2[Max - 1 + i] = true;
        vec_right2[Max - 1 + i] = true;
        label2[Max - 1 + i] = true;
    }
    for (int i = Max - 1; i > 0; i--) {
        t[i] = combine(t[2 * i], t[(2 * i) + 1], (2 * i), ((2 * i) + 1), i, 1);
        d[i] = combine(d[2 * i], d[2 * i + 1], 2 * i, (2 * i) + 1, i, 2);
        label[i] = true;
    }
}
void update(int v, int tl, int tr, int l, int r) {
    if (l > r)
        return;
    if (l == tl && tr == r) {
        if(label[v])
        {
            label[v] = false;
            t[v] = make_dat(r - l + 1);
            vec_left1[v] = true;
            vec_right1[v] = true;
            label1[v] = true;
            d[v] = make_dat(0);
            vec_left2[v] = false;
            vec_right2[v] = false;
            label2[v] = false;
            int in = v / 2;
            while (in > 0) {
                t[in] = combine(t[2 * in], t[2 * in + 1], 2 * in, 2 * in + 1, in, 1);
                d[in] = combine(d[2 * in], d[2 * in + 1], 2 * in, 2 * in + 1, in, 2);
                in = in / 2;
            }
            return;
        }
        else
            return;
    } else {
        if (label[v]) {
            int tm = (tl + tr) / 2;
            update(v * 2, tl, tm, l, min(r, tm));
            update(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r);
        } else
            return;
    }
}
pair<datBig, datBig> query1(int index, int l, int r, int tl, int tr) {
    if (tl > r || l > tr)
        return pair<datBig, datBig>(make_datBig(0, false, false, false), make_datBig(0, false, false, false));
    if (l >= tl && r <= tr) {
        if (label[index])
            return pair<datBig, datBig>(make_datBig(t[index], vec_left1[index], vec_right1[index], label1[index]), make_datBig(d[index], vec_left2[index], vec_right2[index], label2[index]));
        else
            return pair<datBig, datBig>(make_datBig(r - l + 1, true, true, true), make_datBig(0, false, false, false));
    } else {
        int m = (l + r) / 2;
        if (m >= tr && l <= tl) {
            if (label[index])
            return query1(2 * index, l, m, tl, tr);
            else
                return pair<datBig, datBig>(make_datBig(tr - tl + 1, true, true, true), make_datBig(0, false, false, false));
        }
        if (m + 1 <= tl && r >= tr) {
            if (label[index])
                return query1(2 * index + 1, m + 1, r, tl, tr);
            else
            return pair<datBig, datBig>(make_datBig(tr - tl + 1, true, true, true),make_datBig(0, false, false, false));
        } else {
            if (label[index]) {
                pair<datBig, datBig> left_ans = query1(2 * index, l, m, tl, tr);
                pair<datBig, datBig> right_ans = query1(2 * index + 1, m + 1, r, tl, tr);
                return pair<datBig, datBig>(combineBig(left_ans.first, right_ans.first), combineBig(left_ans.second, right_ans.second));
            } else
                return pair<datBig, datBig>(make_datBig(min(r, tr) - max(tl, l) + 1, true, true, true), make_datBig(0, false, false, false));
        }
    }
}
int main() {
    freopen("fence.in", "r", stdin);
    freopen("fence.out", "w", stdout);
    build();
    int a, b;
    char str = -1;
    while (true) {
        scanf("%c", &str);
        if (str == 'W') {
            scanf("%d%d\n", &a, &b);
            pair<datBig, datBig> res = query1(1, 1, Max, a, b);
            printf("%d %d\n", res.second.some.sum, res.first.some.sum);
            continue;
        }
        if (str == 'L') {
            scanf("%d%d\n", &a, &b);
            update(1, 1, Max, a, b);
            continue;
        }
        break;
    }
}