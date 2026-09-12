class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);   // minimum capacity must be at least the heaviest package
            right += w;                 // maximum capacity is sum of all packages
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, days, mid)) {
                right = mid;  // try smaller capacity
            } else {
                left = mid + 1;  // need larger capacity
            }
        }
        return left;
}

    private boolean canShip(int[] weights, int days, int capacity) {
        int usedDays = 1;
        int currentLoad = 0;

        for (int w : weights) {
            if (currentLoad + w > capacity) {
                usedDays++;
            currentLoad = 0;
            }
        currentLoad += w;
        }
        return usedDays <= days;
    }

}