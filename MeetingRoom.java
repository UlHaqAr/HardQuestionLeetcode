class Solution {
    // n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
    // 3 rooms empty. start 3 meetings
    // r1 : -1------------------20
    // r2 : --2-------10
    // r3 : ---3-5
    // r3 finishes next. next to assign is [4,9]
    // r3 : ---3-5----10
    // r2 and r3 both finish next. next to assign in [6,8]. we take r2 (lower room id)
    // r2 : --2-------10-12
    // r1=1 meet, r2=2 meet, r3=2 meet . ans = r2 (lower room id)

    // note : we need some structure which can tell min of finish times of each room. if same values, it should give min of room ids
    // amazing how they have used priority queue to do that. 
    // note : say rx gets free at t15. say next meeting is t12-t16
    // so rx will have next meet from t15-(t15+t16-t12) = t15-t19 (next start < curr end)
    // note : say rx gets free at t15. say next meeting is t17-t21
    // so rx will have next meet from t17-t21 (next start >= curr end)

    // meets = [1,9], [3,5], [5,7]. n=3
    // note3 : r2 can do meet2,meet3 back to back (you should not assign first 3 meetings to first 3 rooms blindly)
    // make sure you declare that r2 is free, before going to evaluate for [5,7]
    
    public int mostBooked(int n, int[][] meetings) {
        PriorityQueue<Integer> free = new PriorityQueue<>();
        //sort on end times. if end times are same, sort on index
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) ->  a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // {available time, index}
        int[] counter = new int[n];
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            free.offer(i);
        }

        for (int i = 0; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            //before checking for next meet interval, free up all the busy rooms which have end time <= start 
            //this handles note3 above
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                free.offer(busy.poll()[1]);
            }

            if (!free.isEmpty()) {
                int index = free.poll();
                counter[index] += 1;
                busy.offer(new int[]{end, index});
            }
            else {
                int[] room = busy.poll();
                room[0] += end - start;
                busy.offer(room);
                counter[room[1]] += 1;
            }
        }

        int res = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (counter[i] > max) {
                res = i;
                max = counter[i];
            }
        }
        return res;
    }
}
