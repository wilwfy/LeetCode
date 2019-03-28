class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        pascal_tri = []
        if rowIndex <= 0:
            pascal_tri = [1]
        else:
            pascal_tri = [1, 1]
            if rowIndex > 1:
                for i in range(2, rowIndex+1):
                    # Calculate the new row by using previous row elements in inverse order
                    for j in range(i-1, 0, -1):
                        pascal_tri[j] = pascal_tri[j] + pascal_tri[j-1]
                    pascal_tri.append(1)
        return pascal_tri
