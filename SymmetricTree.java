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

/* 
brute force, take row wise data, check palindrome. Works all fine. But too much time.
                1
            2      2
          2  N    2  N
If you take inorder traversal, it will be 2 2 N 1 2 2 N. Now run palindrome function once, so answer is correct. But for below tree, even inorder doesnt work for us.

                        5
             4                        1
        N        1             N            4
               2   N                      2    n
               
Inorder gives N 4 2 1 N 5 N 1 2 4 N
*/
class Solution {

    public boolean isSymmetric(TreeNode root) {
        List<TreeNode> nums = new ArrayList();
        inorder(root, nums);
        return isPalindrome(nums);
    }

    public void inorder(TreeNode node, List<TreeNode> nums)
    {
        if(node.left != null)
        {
            inorder(node.left, nums);
        }
        else if (node.right != null)
        {
            nums.add(null);
        }
       
        nums.add(node);
        
        if(node.right != null)
        {
            inorder(node.right, nums);
        }
        else if(node.left != null)
        {
            nums.add(null);
        }
    }

    public boolean isPalindrome(List<TreeNode> nums)
    {
        int i=0;
        int j=nums.size()-1;
        while(i <= j)
        {
            if(nums.get(i) == null && nums.get(j) == null)
            {
                i++;
                j--;
                continue;
            }
            if(nums.get(i) != null && nums.get(j) != null && nums.get(i).val == nums.get(j).val)
            {
                i++;
                j--;
                continue;
            }
            return false;
        }
        return true;
    }

    // BFS + palindrome
    // public boolean isSymmetric(TreeNode root) {
    //     List<TreeNode> nums1 = new ArrayList();
    //     List<TreeNode> nums2 = new ArrayList();
    //     TreeNode node = root;
    //     nums1.add(root);
    //     nums2.add(root);
    //     while(!nums2.isEmpty())
    //     {
    //         if(!isPalindrome(nums2))
    //         {
    //             return false;
    //         }
    //         nums2.clear();
    //         while(!nums1.isEmpty())
    //         {
    //             if(nums1.get(0) == null)
    //             {
    //                 nums2.add(null);
    //             }
    //             else 
    //             {
    //                 nums2.add(nums1.get(0).left);
    //                 nums2.add(nums1.get(0).right);
    //             }
    //             nums1.remove(0);
    //         }
    //         nums1.addAll(nums2);
    //     }
    //     return true;
        
    // }

    // public boolean isPalindrome(List<TreeNode> nums)
    // {
    //     int i=0;
    //     int j=nums.size()-1;
    //     while(i <= j)
    //     {
    //         if(nums.get(i) == null && nums.get(j) == null)
    //         {
    //             i++;
    //             j--;
    //             continue;
    //         }
    //         if(nums.get(i) != null && nums.get(j) != null && nums.get(i).val == nums.get(j).val)
    //         {
    //             i++;
    //             j--;
    //             continue;
    //         }
    //         return false;
    //     }
    //     return true;
    // }
}

  
