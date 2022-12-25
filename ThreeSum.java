// https://leetcode.com/problems/3sum/description/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            // add operation returns false, if hashset doesnt add the element coz of duplicity
            if (dups.add(nums[i])) {
                twosum(i, nums, seen, res);
            }
        return new ArrayList(res);
    }

    public void twosum(int i, int[] nums, Map<Integer, Integer> seen , Set<List<Integer>> res)
    {
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.containsKey(complement) && seen.get(complement) == i) {
                List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                Collections.sort(triplet);
                res.add(triplet); //wont add if already present, coz hashset
            }
            //the value is i not j. say list= -3 -3 -3 1 1 1 2 2 2, the map will have one entry only for 1,i or 2,i
            seen.put(nums[j], i);
        }
    }

    // twosum
    // target=6, ar= 3 2 5 1 4 5 6
    // when we are at 2 we dont search for 4 (coz 'seen' map wont have value 6)
    // instead when we are 4, we search for 2 (coz 'seen' map wouldve been populated by value '2' by this time)
    // a hashset is enough for 'seen'. but if there are duplicates we need HashMap
    // ar = 3 2 2 2 5 1 4 5 6
    // when you reach 4, ideally you would have 3 entries of '2'. but we will make sure that we dont care about duplicates 
    // meaning, since the key '2' is same for ar[1] ar[2] ar[3], our hashmap only holds one entry (2,1) (number, index)
    // it wont have entry (2,2) and (2,3)
}
