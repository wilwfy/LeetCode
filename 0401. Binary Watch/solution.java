class Solution {
    List<String> res = new ArrayList<String>(){{
        add("0:00");
    }};
    
    public List<String> readBinaryWatch(int num) {
        if (num == 0) return res;
        res.clear();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 64 + m) == num)
                    res.add(String.format("%d:%02d", h, m));
            }
        }
        return res;
    }
}
