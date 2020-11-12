"""
Other's solution of calculating the length of edges and diagonals
Time: O(1)
Space: O(1)
"""
class Solution:
    def validSquare(self, p1: List[int], p2: List[int], p3: List[int], p4: List[int]) -> bool:
        points = [p1, p2, p3, p4]
        
        distances = collections.Counter()
        for i in range(len(points)):
            for j in range(i+1, len(points)):
                distances[self.getDistance(points[i], points[j])] += 1
        
        # A square should have 4 edges with equal length and 2 diagonals with equal length.
        return len(distances.values()) == 2 and 4 in distances.values() and 2 in distances.values()
    
    def getDistance(self, p1, p2):
        return (p1[0] - p2[0])**2 + (p1[1] - p2[1])**2
