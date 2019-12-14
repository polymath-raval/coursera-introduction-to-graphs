#Uses python3
import sys
import math
import heapq 

def minimum_distance(xs, ys):
    result = 0.
    points = [Point(xs[idx], ys[idx]) for idx in range(len(xs))]
    return result

def cretate_edges(points):
	edges = []
	idx = 0
	for i in range(0, len(points)):
		for j in range(i + 1, len(points)):
			edges[idx] = Edge(points[i], points[j])
			edges[idx] = (edge[idx].distance, Edge(points[i], points[j]))
			idx+=1
	return heapq.heapify(edges)

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n = data[0]
    x = data[1::2]
    y = data[2::2]
    print("{0:.9f}".format(minimum_distance(x, y)))


class Point():
	def __init__(self, x, y):
		self.x = x
		self.y = y

	def __eq__(self, other):
		return (self.x == other.x) and (self.y == other.y)


def Edge():
	def __init__(self, p1, p2):
		self.p1 = p1
		self.p2 = p2
		self.distance = sqrt(((p1.x - p2.x) ** 2) + ((p1.y - p2.y) ** 2))

