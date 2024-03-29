#Uses python3

import sys
import queue

def number_of_components(adj):
    captionOfConnectedComponents = 0
    visited = [False] * len(adj)
    
    for vertex in range(0, len(adj)):
        if not visited[vertex]:
            captionOfConnectedComponents = captionOfConnectedComponents + 1
            q = queue.Queue(maxsize = len(adj))
            q.put(vertex)
            visited[vertex] = True
            while not q.empty():
                current = q.get()
                for neighbour in adj[current]:
                    if not visited[neighbour]:
                        q.put(neighbour)
                        visited[neighbour] = True

    return captionOfConnectedComponents

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
    print(number_of_components(adj))
