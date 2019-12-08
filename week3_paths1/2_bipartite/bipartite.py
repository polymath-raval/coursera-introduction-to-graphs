#Uses python3

import sys
import queue

def bipartite(adj):
    parts = [-1] * len(adj)
    q = queue.Queue()
    q.put(0)
    parts[0] = 0
    while not q.empty():
        current = q.get()
        for neighbour in adj[current]:
            if parts[current] == parts[neighbour]:
                return 0
            elif parts[neighbour] == -1:
                parts[neighbour] = (parts[current] + 1) % 2
                q.put(neighbour)
    return 1

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
        adj[b - 1].append(a - 1)
    print(bipartite(adj))
