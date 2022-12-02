class Solution {
    
    // below is brute force. time limit exceeds.
//     public static int canCompleteCircuit(int[] gas, int[] cost) {
       
//         for(int i=0 ; i< gas.length; i++)
//         {
//             if(gas[i] < cost[i])
//             {
//                 continue;
//             }
//             if(sol(gas, cost, i) != -1)
//             {
//             	return i;
//             }
//         }
        
//         return -1;
//     }
    
//     public static int sol(int[] refillHere, int[] requiredForNext, int index)
//     {
//         int currCapacity = refillHere[index];
//         int count = 0;
//         int i = index;
        
//         while(currCapacity >= requiredForNext[i])
//         {
            
//             if(i == index) count++;
//             if(count == 2) {return index;}
            
//             currCapacity = currCapacity - requiredForNext[i];
            
//             i++;
            
//             if(i >= refillHere.length) i = i % refillHere.length ;
            
//             currCapacity = currCapacity + refillHere[i];
//         }
        
//         return -1;
//     }
    
    //below is optimized sol
    //it works coz we can have only one solution index
    //why will a soln not work for a given index ? only when at some point in the route, the cost becomes more than gas ? which means 
    //the value gas-cost becomes negative. 
    
    //what are we saving here compared to brute force ?
    //say there are 10 elements. you are checking for 5th index. on its route , it failed at 8th index. 
    //In brute force, you would still go ahead and 
    //evaluate for index 6 7 and 8. Here we dont. we update the 'res' to 8, and 'i' also continues from 8.
    
    //a b c d e f g h i j
    //lets say index a b c d is not possible coz gas < cost
    // at e gas>cost. so we go from e to f to g to h. but at h we lose. somehow gas at h is less than cost at h.
    // now should we now evaluate for next index f, g, h. Or directly move to i.
    // explanation : if movement was possible at e, it means we gave positive fuel to index f. 
    // even with positive fuel coming from behind, movement from f onwards died at h.
    // so it means you have already evaluated for f, g and h. just start now with i.
    // that is how we go from two loops in brute force to one loop in below solution.
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas= 0, sumCost= 0;
        int res=0 , total=0;
        
        //general check to see if a soln exists
        for(int i = 0; i< gas.length; i++){
            sumGas += gas[i];
            sumCost += cost[i];
        }
        if(sumGas< sumCost) return -1;
        
        for(int  i= 0; i< gas.length; i++)
        {
            total += gas[i]- cost[i];
            if(total<0)
            {
                res= i+1;
                total=0;
            }
        }
       return res; 
    }
        
    
}
