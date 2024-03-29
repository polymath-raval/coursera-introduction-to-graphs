#Uses python3

import sys
from queue import Queue

def acyclic(adj):
    for vertex in range(len(adj)):
        if bfs(adj, vertex):
            return 1
    return 0

def bfs(adj, vertex):
    q = Queue()
    visited = [False] * len(adj)
    q.put(vertex)
    visited[vertex] = True
    while not q.empty():
        current = q.get()
        for neighbour in adj[current]:
            if neighbour == vertex:
                return True
            if not visited[neighbour]:
                visited[neighbour] = True
                q.put(neighbour)
    return False

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
    print(acyclic(adj))
