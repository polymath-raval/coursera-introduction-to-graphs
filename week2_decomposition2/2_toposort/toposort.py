#Uses python3

import sys

def dfs(adj, visited, order, vertex):
    if not visited[vertex]:
        visited[vertex] = True
        for neighbour in adj[vertex]:
            dfs(adj, visited, order, neighbour)
        order.append(vertex)

def reverse(adj):
    reverse =  [[] for _ in range(len(adj))]
    for vertex in range(len(adj)):
        for neighbour in adj[vertex]:
            reverse[neighbour].append(vertex)
    return reverse

def toposort(adj):
    adj = reverse(adj)
    visited = [False] * len(adj)
    order = []
    for vertex in range(len(adj)):
        dfs(adj, visited, order, vertex)
    return order

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
    order = toposort(adj)
    for x in order:
        print(x + 1, end=' ')

