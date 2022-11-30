class Solution {
    
    // no duplicates allowed
    public int removeDuplicates1(int[] nums) 
    {
        // 0 0 1 1 1 2 2 3 4 5 5
        // once we find a different element, we have to push it next to its smaller element. (array is sorted)
        // we dont care what happens to duplicate elements
        // lets take 0 and 1
        // 0 0 1 1 1 -> 0 1  1 1 1 -> since index 2 was different than index 1, we just made index 1 same as index 2
        // we dont need to swap values coz no one cares about duplicate values , just like private employees
        // the first 2 elements are the answer. who cares about others 
        // XX (next to insert will be at index 2)
        // lets put '2' in picture now
        // 0 1 1 1 1 2 2
        // 0 1 2 1 1 2 2 (at index 5 we found a diff element. we put it to index 2. see XX )
        
        int nextToInsertIndex = 1;
        for (int i =1; i<nums.length;i++)
        {
            if(nums[i] != nums[i-1])
            {
                nums[nextToInsertIndex] = nums[i];
                nextToInsertIndex++;
            }
        }
        
        return nextToInsertIndex;
    }
  
    // max of two duplicates allowed
    public int removeDuplicates2(int[] nums) 
    {
        if (nums.length == 0) return 0;
        
        int count = 1;
        int nextInsert = 1;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                count ++;
            } else {
                count = 1;
            }
            
            if (count <= 2) {
                nums[nextInsert] = nums[i];
                nextInsert++;
            }
        }
        return nextInsert;
        
        // example 0 0 0 1 1 2 2 2 3 3 3 4 , we will collect the answer digits. who cares about duplicates.
        // we take the first two '1s', push it left
        // we take the first two '2s', push it left
        // we take the first two '3s', push it left
        // by pushing left, we mean setting 'nextToInsert' index.
        // In part 1 of this problem, 'Remove Duplicates from Sorted Array', we used to push left only once.
        // here we will push left for two times. coz max two occurences are allowed.
        
        // i 1
        // count 2
        // nextInsert 2
        // 0 0 0 1 1 2 2 2 3 3 3 4
        //
        // i 2
        // count 3
        // 0 0 0 1 1 2 2 2 3 3 3 4
        //
        // i 3
        // count 1
        // 0 0 1 1 1 2 2 2 3 3 3 4
        // insertindex 3
        //
        // i 4
        // count 2
        // 0 0 1 1 1 2 2 2 3 3 3 4 
        // insertindex 4
        //
        // i 5
        // count 1
        // 0 0 1 1 2 2 2 2 3 3 3 4
        // insertindex 5
        //
        // i 6
        // count 2
        // 0 0 1 1 2 2 2 2 3 3 3 4
        // insertindex 6
        //
        // i 7
        // count 3
        // 0 0 1 1 2 2 2 2 3 3 3 4
        //
        // i 8
        // count 1
        // 0 0 1 1 2 2 3 2 3 3 3 4
        // insertindex 7
        //
        // i 9
        // count 2
        // 0 0 1 1 2 2 3 3 3 3 3 4
        // insertindex 8
        //
        // i 10
        // count 3
        // 0 0 1 1 2 2 3 3 3 3 3 4
        //
        // i 11 
        // count 1
        // 0 0 1 1 2 2 3 3 4 3 3 4
        // insertindex 9
        

    }
    
}
