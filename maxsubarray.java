class Solution {
    public int maxSubArray(int[] nums) 
    {
        //kadanes algo
        //if sum of prev array is less than zero, set currsum to zero
        
        // if all nums are positive, it will add up all values
        // if all nums are negative, it needs to pick the largest element
        // -3 -4 -7 -1 -6
        // result = -1
        
        int max = Integer.MIN_VALUE;
        int currsum = 0;
        for(int i=0;i<nums.length;i++)
        {
            currsum = currsum + nums[i];
            
            max = Math.max(max, currsum);
            
            //we have already compared sum so far in previous line
            // say currsum is negative right now.
            // if next element is negative, there is no point of adding, currsum will go down only
            // if next element is positive, currsum will go up, but why not just take the next element only.
            if(currsum < 0)
            {
                currsum = 0;
            }
        }
        
        return max;
    }
    
    
}
