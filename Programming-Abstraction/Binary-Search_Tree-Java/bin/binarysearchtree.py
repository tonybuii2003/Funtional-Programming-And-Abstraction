class Merger:
    def merge(t1, t2):
        tmp_list = []
        for i in t1:
            tmp_list.append(i)
        for i in t2:
            tmp_list.append(i)
        tmp_list.sort()
        return tmp_list


class BinarySearchTree:
    def __init__(self, name: str, root: 'Node'):
        self.root = root
        self.cursor = Node()
        self.name = name
        self.size = 0

    def cursor_to_root(self):
        self.cursor = self.root

    def cursor_to_left(self):
        self.cursor = self.cursor.left

    def cursor_to_right(self):
        self.cursor = self.cursor.right

    def add_all(self, *lst):
        for i in lst:
            newNode = Node()
            newNode.name = i
            self.add(newNode)
            self.size += 1

    def add(self, newNode: 'Node'):
        if self.root.name == None:
            self.root = newNode
            self.cursor_to_root()
        else:
            if self.cursor.name > newNode.name:
                if (self.cursor.left == None):
                    self.cursor.left = newNode
                    self.cursor_to_root()
                else:
                    self.cursor_to_left()
                    self.add(newNode)

            if self.cursor.name < newNode.name:
                if (self.cursor.right == None):
                    self.cursor.right = newNode
                    self.cursor_to_root()
                else:
                    self.cursor_to_right()
                    self.add(newNode)

    def __iter__(self):
        return self.tree_as_list(self.root)

    def __str__(self):
        s = ''
        self.cursor_to_root()
        tmp_list = self.str_helper(
            self.root, [], '[{}] {}'.format(self.name, self.root.name))
        for i in tmp_list:
            s += "{}".format(i)
        return s

    def str_helper(self, cur: 'Node', lst=[], added_string=''):
        lst.append(added_string)
        if cur.left != None:
            self.str_helper(cur.left, lst, " L:({}".format(cur.left.name))
            lst.append(')')
        if cur.right != None:
            self.str_helper(cur.right, lst, " R:({}".format(cur.right.name))
            lst.append(')')
        return lst

    def tree_as_list(self, root):
        if root != None:

            for x in self.tree_as_list(root.left):
                yield x
            yield root.name
            for x in self.tree_as_list(root.right):
                yield x


class Node:
    def __init__(self):
        self.name = None
        self.left = None
        self.right = None

    def __iter__(self):
        return self.pre_order()

    def pre_order(self):
        if self != None:
            if self.left != None:
                for x in self.left.pre_order():
                    yield x
            yield self.name
            if self.right != None:
                for x in self.right.pre_order():
                    yield x


if __name__ == "__main__":
    t1 = BinarySearchTree(name="Oak", root=Node())
    t2 = BinarySearchTree(name="Birch", root=Node())
    t3 = BinarySearchTree(name="Cornucopia", root=Node())
    t1.add_all(5, 3, 9, 0)
    t2.add_all(1, 0, 10, 2, 7)
    t3.add_all("coconut", "apple", "banana", "plum", "durian",
               "no durians on this tree!", "tamarind")
    for x in t1.root:
        print(x)
    for x in t2.root:
        print(x)
    print(Merger.merge(t1, t2))
