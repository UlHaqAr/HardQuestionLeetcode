/*
* https://leetcode.com/problems/house-robber-iii/submissions/
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    //                        _ _ _ 3 _ _ _ _ _                             3                
    //                     /                     \ 
    //                   4                         5                        9,3 = 9
    //            /          \             /               \                
    //           1           2             1                1               5+3,9 = 9
    //         /    \      /    \       /    \           /      \
    //        1     1     1     1       1     1         1        1          8+9, 9 = 17
    //               \           \            \        /
    //               9           9             9      9                     36+9, 17 = 45
    //
    //  res = max (row1+row3+ro5, row2+row4, row2+row5) = row2+row5
    //
    //                  1
    //               /    \
    //              1      5
    //            /.  \.  /. \
    //           1.   5.  1.  1
    // 
    //. res = 1+1+5+1+1 , 1+5 = 9, 6
    //. but wait, 5 and 5 are also not adjacent. so we can rob 5+5 = 10
    //. the significant difference is if we take 5 and 5, we cannot consider root 1
    //
    //                  a
    //               /    \
    //              b      c
    //            /.  \.  /. \
    //           d     e  f   g
    // 
    // res[a] = X = if a is included -> res[b] with b not included + res[c] with c not included + a
    //        = Y = if a is not inlcluded -> res[b] with b included + res[c] with c included
    //        = max of X, Y
    // if you see Y, its not mandatory that b has to be included. i mean if "res[b] with b not included" comes out 
    // to be greater than "res[b] with b included", we can take the former one.
    // so,
    // res[a] = X = if a is included -> res[b] with b not included + res[c] with c not included + a
    //        = Y = if a is not inlcluded -> res[b] with/without b + res[c] with/without c 
    //        = max of X, Y
    //
    
    public int rob(TreeNode root) 
    {
        HousePair res = HouseRobber(root);
        return Math.max( res.includeElem, res.excludeElem);
    }

    public static  HousePair HouseRobber(TreeNode root) 
    {
        if(root == null) return new HousePair();

        HousePair left = HouseRobber(root.left);
        HousePair right = HouseRobber(root.right);

        HousePair pair = new HousePair();
        pair.includeElem = left.excludeElem+ root.val + right.excludeElem;
        pair.excludeElem = Math.max(left.includeElem,left.excludeElem)+ Math.max(right.includeElem, right.excludeElem);

        return pair;
    }
    
    public static class HousePair
    {
        int includeElem = 0;
        int excludeElem = 0;
    }
}
