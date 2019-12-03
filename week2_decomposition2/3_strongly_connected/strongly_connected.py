#Uses python3

import sys
from heapq import *

sys.setrecursionlimit(200000)


def number_of_strongly_connected_components(adj):
    postClock = computePostClock(adj)
    heap = createHeap(postClock)
    return compute_strong_components(adj, heap)

def compute_strong_components(adj, heap):
    no_of_components = 0
    visited = [False] * len(adj)
    for (postclock, vertex) in heap:
        if not visited[vertex]:
            no_of_components = no_of_components + 1
            dfs_without_clock(adj, visited, vertex)
    return no_of_components


def createHeap(postClock):
    heap = []
    for vertex in range(len(postClock)):
        heap.append((postClock[vertex] * -1, vertex))
    heapify(heap)
    return [heappop(heap) for i in range(len(heap))]

def computePostClock(adj):
    r_adj = reverse(adj)
    visited = [False] * len(r_adj)
    pre = [0] * len(r_adj)
    post = [0] * len(r_adj)
    clock = 0
    for vertex in range(len(r_adj)):
        clock = dfs(r_adj, visited, vertex, pre, post, clock)
    return post

def reverse(adj):
    reverse =  [[] for _ in range(len(adj))]
    for vertex in range(len(adj)):
        for neighbour in adj[vertex]:
            reverse[neighbour].append(vertex)
    return reverse

def dfs_without_clock(adj, visited, vertex):
    if not visited[vertex]:
        visited[vertex] = True
        for neighbour in adj[vertex]:
            dfs_without_clock(adj, visited, neighbour)

def dfs(adj, visited, vertex, pre, post, clock):
    if visited[vertex]:
        return clock
    else:
        visited[vertex] = True
        clock = clock + 1
        pre[vertex] = clock
        for neighbour in adj[vertex]:
            clock = dfs(adj, visited, neighbour, pre, post, clock)
        clock = clock + 1
        post[vertex] = clock
        return clock


if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n, m = data[0:2]
    data = data[2:]
    edges = list(zip(data[0:(2 * m):2], data[1:(2 * m):2]))
    adj = [[] for _ in range(n)]
    for (a, b) in edges:
        adj[a - 1].append(b - 1)
    print(number_of_strongly_connected_components(adj))
