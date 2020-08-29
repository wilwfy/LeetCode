/**
 * Other's solution with Math.random()
 *
 * Diagram:  https://github.com/wilwfy/LeetCode/blob/master/0478.%20Generate%20Random%20Point%20in%20a%20Circle/478_solution.png
 *
 * The point is that we should not use x=rand(len)*cos(rand(degree)), we should use x=sqrt(rand(len))*cos(rand(degree)).
 */
class Solution {
    double radius, x_center, y_center;
    
    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }
    
    // http://www.anderswallin.net/2009/05/uniform-random-points-in-a-circle-using-polar-coordinates/
    // x = sqrt(rand(len)) * cos(rand(degree))
    public double[] randPoint() {
        // Math.random() method returns a pseudorandom double type
        // number greater than or equal to 0.0 and less than 1.0.
        double len = Math.sqrt(Math.random()) * radius;
        double deg = Math.random() * 2 * Math.PI;
        double x = x_center + len * Math.cos(deg);
        double y = y_center + len * Math.sin(deg);
        return new double[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
