class Solution {
  
  // https://leetcode.com/problems/edit-distance/description/
  // video https://www.youtube.com/watch?v=MiqoA-yF-0M&t=22s
  
  // say string s1 = benyam , s2 = benyax 
  // case1 : replace
  // lets say you replace last character of s1 to get s2. this means previous all characters of s1 need to match previous all characters of s2
  // s1[6] -> s2[6] = replace + (s1[5] -> s2[5])
  
  // say string s1 = benyam , s2 = benyamx 
  // case2 : insert
  // lets say you insert a character in s1 to make it s2. this means previous all characters of s1 need to match previous all characters of s2,
  // and one character was extra/added in s2
  // s1[6] -> s2[7] = insert + (s1[6] -> s2[6])
  
  // say string s1 = benyam , s2 = benya 
  // case2 : deletion
  // lets say you delete a character in s1 to make it s2. this means previous all characters of s1 need to match previous all characters of s2,
  // and one character was extra/added in s1
  // s1[6] -> s2[5] = insert + (s1[5] -> s2[5])
  
  // say string s1 = benyam , s2 = benyam 
  // case2 : deletion/insertion/replace
  // you dont need to do insert/delete or replace any character. just lose the character (last) and calculate for remaining string
  // s1[6] -> s2[6] = s1[5] -> s2[5]
  
  // the words insert, delete, replace indicate cost, which is 1 for us in this question

  // math.min( 1+a, 1+b) = math.min(a,b) + 1
  
  public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int dp[][] = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i=0;i<=m;i++){
            dp[i][0] = i;
        }
        for(int j=0;j<=n;j++){
            dp[0][j] = j;
        }
        return recur(s1,s2,m,n,dp);
    }

    // traverse from end 
    public int recur(String s1, String s2, int m, int n, int[][] dp){

        if(dp[m][n] != -1) return dp[m][n];
        if(s1.charAt(m-1) == s2.charAt(n-1)){
            return dp[m][n] = recur(s1,s2,m-1,n-1,dp);
        }else{
            return dp[m][n] = 1 + Math.min( recur(s1,s2,m-1,n,dp), Math.min(recur(s1,s2,m,n-1,dp),recur(s1,s2,m-1,n-1,dp)));
        }
    }
}
