/**
 * My solution with split()
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");
        if (ver1.length == 0) ver1 = new String[]{version1};
        if (ver2.length == 0) ver2 = new String[]{version2};
        int i = 0;
        while (i < ver1.length && i < ver2.length) {
            int v1 = Integer.valueOf(ver1[i]);
            int v2 = Integer.valueOf(ver2[i]);
            if (v1 > v2)
                return 1;
            else if (v1 < v2)
                return -1;
            else
                i++;
        }
        while (i < ver1.length) {
            if (Integer.valueOf(ver1[i]) > 0)
                return 1;
            else
                i++;
        }
        while (i < ver2.length) {
            if (Integer.valueOf(ver2[i]) > 0)
                return -1;
            else
                i++;
        }
        return 0;
    }
}


/**
 * Other's solution with split()
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");
        
        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i<length; i++) {
        	Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
        	Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
        	int compare = v1.compareTo(v2);
        	if (compare != 0) {
        		return compare;
        	}
        }
        
        return 0;
    }
}
