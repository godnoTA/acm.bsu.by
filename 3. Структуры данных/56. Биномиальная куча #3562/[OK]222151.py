with open('output.txt', 'w') as out: out.write('\n'.join([str(i) for i, c in enumerate( reversed(format(int(open('input.txt', 'r').readline() ), 'b' )) ) if c == '1']))
