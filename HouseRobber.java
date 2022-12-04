class Solution {
    public int rob(int[] nums) 
    {
        // 2 4 9 3 1 11 
        // 2 9 1 = 12
        // 4 3 11 = 18
        // 2 9 11 = 22
        //                  x
        //              /       \
        //            2          4
        //         / / \. \     /  \  \
        //       9. 3  1. 11    3   1  11
        //    /  \. |           |.        
        //   1.  11 11          11
        //
        // res = max( take0, take1)
        // take0 = 2 + max(take2, take3, take4, take5)
        // take1 = 4 + max(take3, take4, take5)
        // if we take above approach, we loop till end, hit a base condition, then return in the loop 
        // to populate res building all the sub results.
        
        // Other way is to keep building the results while we loop and just return at end.
        // take0 = 2
        // take1 = 4 OR take0
        // take2 = 9 + take0 OR take1 (whichever is max. we completely ignore the value at index2 if it doesnt give max)
        // take3 = 3 + take1 OR take2 (whichever is max)
        // you see how in above approach, we are building the result as we go. 
        
        if(nums.length == 1) 
        { 
            return nums[0]; 
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for(int i=2; i<nums.length; i++) 
        {
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        
        return dp[nums.length-1];
     
    }
}
