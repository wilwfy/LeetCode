/**
 * Official solution of Regex
 *
 * Time complexity: O(1) because the patterns to match have constant length.
 * Space complexity: O(1).
 */
import java.util.regex.Pattern;
class Solution {
  String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
  Pattern pattenIPv4 =
          Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");

  String chunkIPv6 = "([0-9a-fA-F]{1,4})";
  Pattern pattenIPv6 =
          Pattern.compile("^(" + chunkIPv6 + "\\:){7}" + chunkIPv6 + "$");

  public String validIPAddress(String IP) {
    if (pattenIPv4.matcher(IP).matches()) return "IPv4";
    return (pattenIPv6.matcher(IP).matches()) ? "IPv6" : "Neither";
  }
}


/**
 * Official solution of Divide and Conquer
 *
 * Time complexity:  O(N) because to count number of dots requires to parse the entire input string.
 * Space complexity: O(1).
 */
class Solution {
    public String validIPAddress(String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3)
            return validateIPv4(IP);
        
        if (IP.chars().filter(ch -> ch == ':').count() == 7)
            return validateIPv6(IP);
        
        return "Neither";
    }
    
    public String validateIPv4(String IP) {
        String[] nums = IP.split("\\.", -1);
        for (String num: nums) {
            // Validate integer in range (0, 255):
            // 1. length of chunk is between 1 and 3
            if (num.length() == 0 || num.length() > 3) return "Neither";
            // 2. no extra leading zeros
            if (num.charAt(0) == '0' && num.length() != 1) return "Neither";
            // 3. only digits are allowed
            for (char ch: num.toCharArray()) {
                if (!Character.isDigit(ch)) return "Neither";
            }
            // 4. less than 255
            if (Integer.parseInt(num) > 255) return "Neither";
        }
        return "IPv4";
    }
    
    public String validateIPv6(String IP) {
        String[] nums = IP.split("\\:", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String num: nums) {
            // Validate hexadecimal in range (0, 2**16):
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (num.length() == 0 || num.length() > 4) return "Neither";
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (Character ch: num.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }
}


/**
 * Other's solution of Divide and Conquer
 *
 * Time complexity:  O(N) because to count number of dots requires to parse the entire input string.
 * Space complexity: O(1).
 */
 class Solution {
     public static String validIPAddress(String IP) {
        String[] ipv4 = IP.split("\\.",-1);
        String[] ipv6 = IP.split("\\:",-1);
        if(IP.chars().filter(ch -> ch == '.').count() == 3){
            for(String s : ipv4) if(isIPv4(s)) continue;else return "Neither"; return "IPv4";
        }
        if(IP.chars().filter(ch -> ch == ':').count() == 7){
            for(String s : ipv6) if(isIPv6(s)) continue;else return "Neither";return "IPv6";
        }
        return "Neither";
    }
    
	public static boolean isIPv4 (String s) {
         try{ return String.valueOf(Integer.valueOf(s)).equals(s) && Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= 255;}
         catch (NumberFormatException e){return false;}
    }
	
    public static boolean isIPv6 (String s) {
        if (s.length() > 4) return false;
        try {return Integer.parseInt(s, 16) >= 0  && s.charAt(0) != '-';}
        catch (NumberFormatException e){return false;}
    }
}
