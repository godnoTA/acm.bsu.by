
def comb(n, k):
    if(k > n / 2):
        return comb(n, n - k)
    if(k < 0):
        return 0
    else:
        numerator = 1
        denominator = 1
        num_multiplier = n
        denom_multiplier = 1
        while(denom_multiplier <= k):
            numerator *= num_multiplier
            denominator *= denom_multiplier
            num_multiplier -= 1
            denom_multiplier += 1
        return numerator / denominator

def find_nearest_degree_of_two(number):
    if(number <= 0):
        return -1
    power = 1
    degree = -1
    while(power <= number):
        power <<= 1
        degree += 1
    return degree

def find_solution(limit, num_ones):
    current_limit = limit
    nearest = find_nearest_degree_of_two(current_limit)
    remaining_ones = num_ones
    count = 0
    while (nearest != -1):
        #print "Solving for", current_limit, remaining_ones
        count += comb(nearest, remaining_ones)
        remaining_ones -= 1
        current_limit -= 1 << nearest
        nearest = find_nearest_degree_of_two(current_limit)
    return count

def main():
    [A, B, K] = map(long, open('input.txt', 'r').read().split())
    open('output.txt', 'w').write(str(find_solution(B + 1, K) - find_solution(A, K)))

main()
    
    