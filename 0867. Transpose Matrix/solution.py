'''
 The zip() function of Python inbuilt libraries returns an iterator of tuples based on the iterable object.
 In order to get the transpose of the matrix first, we need to unzip the list using * operator then zip it.
'''
class Solution:
    def transpose(self, A: List[List[int]]) -> List[List[int]]:
        #return [list(i) for i in zip(*A)]
        return zip(*A)
