class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        pascal_tri = []
        if numRows <= 0:
            return pascal_tri
        elif numRows == 1:
            pascal_tri.append([1])
            return pascal_tri
        elif numRows >= 2:
            pascal_tri.append([1])
            pascal_tri.append([1, 1])
            if numRows == 2:
                return pascal_tri
            else:
                row_len = 2
                for i in range(2, numRows):
                    row_len += 1
                    row = []
                    row.append(1)
                    for r in range(1, row_len-1):
                        row.append(pascal_tri[i-1][r-1] + pascal_tri[i-1][r])
                    row.append(1)
                    pascal_tri.append(row)
                return pascal_tri
