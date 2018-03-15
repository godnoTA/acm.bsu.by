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
        else:
            return

    w = Node(x)

    if parent is None:
        tree.root = w
    elif x < parent.key:
        parent.left = w
    elif x > parent.key:
        parent.right = w


def del2(x):
    tree.root=delete_recursively(tree.root, x)


def delete_recursively(v, x):
    if v is None:
        return None

    if x < v.key:
        v.left = delete_recursively(v.left, x)
        return v
    elif x > v.key:
        v.right = delete_recursively(v.right, x)
        return v

    if v.left is None:
        return v.right
    elif v.right is None:
        return v.left
    else:
        min_key = find_min(v.right).key
        v.key = min_key
        v.right = delete_recursively(v.right, min_key)
        return v


def find_min(v):
    if v.left is not None:
        return find_min(v.left)
    else:
        return v


def print_tree_file(v, f3):
    while v is not None:
        f3.write(str(v.key) + '\n')
        print_tree_file(v.left, f3)
        print_tree_file(v.right, f3)
        break


tree = Tree()
f = open('input.txt', 'r')
f2 = open('output.txt', 'w')
x2 = int(f.readline())
f.readline()
l = f.readline()
insert(int(l))
for line in f:
    insert(int(line))

del2(x2)
print_tree_file(tree.root, f2)
f.close()
f2.close()