class Node(object):
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None


class Tree(object):
    def __init__(self):
        self.root = None


def insert(x):
    parent = None
    v = tree.root
    while v is not None:
        parent = v
        if x < v.key:
            v = v.left
        elif x > v.key:
            v = v.right
        else:  # x == v.key
            return

    w = Node(x)

    if parent is None:
        tree.root = w
    elif x < parent.key:
        parent.left = w
    elif x > parent.key:
        parent.right = w


def print_tree_file(v, x5):
    while v is not None:
        x5 = x5 + v.key
        x5=print_tree_file(v.left, x5)
        x5=print_tree_file(v.right, x5)
        break
    return x5


tree = Tree()
f = open('input.txt', 'r')
f2 = open('output.txt', 'w')
l = f.readline()
insert(int(l))
for line in f:
    insert(int(line))
x4=0
f2.write(str(print_tree_file(tree.root, x4)))
f.close()
f2.close()