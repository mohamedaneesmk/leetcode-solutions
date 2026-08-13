class Solution {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {

            List<Integer> current = new ArrayList<>();

            long value = 1;

            for (int col = 0; col <= row; col++) {
                current.add((int) value);

                value = value * (row - col) / (col + 1);
            }

            result.add(current);
        }

        return result;
    }
}