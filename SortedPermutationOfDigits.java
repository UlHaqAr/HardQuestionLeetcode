// https://leetcode.com/problems/next-permutation/description/
// this problem indicates how you can find the next greater number using digits of a number.
// refer solution diagram here https://leetcode.com/problems/next-permutation/solutions/13994/readable-code-without-confusing-i-j-and-with-explanation/

class Solution {

    //                               - - -  pick digits in sorted fashion at each level
    //                1                    2                        3             
    //         2           3          1         3              1          2
    //     3                2        3           1           2              1
    //.  123              132      213          231         312             321     permutation set

    // lets take 231
    // traverse from right. 1 is less than 3. so among [1,3], 31 is already max
    // next digit. between [2,3] 3 is greater, so we can do 321. but how do we know if it is next to 231 in permutation set ?
    // well once we have swapped 2nd and 3rd place from right, we know that 3-x-x will always be greater than 231.
    // so just take the minimum number formed from x-x digits
    // how do you find minimum ? well how did you reach 3 ? by knowing that every digit on its right was in descendinng order right? just do reverse of those.

    public void nextPermutation(int[] nums) {
        int pivot = indexOfLastPeak(nums) - 1;

        if (pivot != -1) {
            int nextPrefix = lastIndexOfGreater(nums, nums[pivot]); 
            swap(nums, pivot, nextPrefix); 
        }
        reverseSuffix(nums, pivot + 1); 
     }
    
    public int indexOfLastPeak(int[] nums) {
        for (int i = nums.length - 1; 0 < i; --i) {
            if (nums[i - 1] < nums[i]) return i;
        }
        return 0;
    }

    public int lastIndexOfGreater(int[] nums, int threshold) {
        for (int i = nums.length - 1; 0 <= i; --i) {
            if (threshold < nums[i]) return i;
        }
        return -1;
    }

    
    public void reverseSuffix(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
