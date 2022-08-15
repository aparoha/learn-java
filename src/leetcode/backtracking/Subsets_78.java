package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
        Take example of [1,2,3].
([ans],[nums])
                          ([],[1,2,3])
         (choose 1)  /                  \ (don't choose 1)
                    /                    \
              ([1],[2,3])                ([],[2,3])
      (choose 2)/ \(don't choose 2)  (choose 2)/ \(don't choose 2)
               /   \                         /   \
       ([1,2],[3])  ([1],[3])          ([2],[3])   ([],[3])
(choose 3)/ \(don't choose 3) ....................................
         /   \
  ([1,2,3],[])  ([1,2],[]) ([1,3],[]) ([1,3],[]).......
(Base cases when nums is empty)
PS: Please try this on paper and pardon me if the tree doesn't look good :)

 */
public class Subsets_78 {

//    //Approach 1 - backtracking
//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        subsets(nums, 0, new ArrayList<>(), result);
//        return result;
//    }
//
//    public void subsets(int[] nums, int start, List<Integer> currentSubset, List<List<Integer>> result) {
//        if(start == nums.length) {
//            result.add(new ArrayList<>(currentSubset));
//            return;
//        }
//        //for every item in nums, we have 2 choices
//        //choice 1: keep it in current subset
//        currentSubset.add(nums[start]);
//        subsets(nums, start + 1, currentSubset, result);
//
//        //choice 2: dont keep it in current subset
//        currentSubset.remove(currentSubset.size() - 1);
//        subsets(nums, start + 1, currentSubset, result);
//    }


    /*
                                    [1,2,3,4]
          []                    [1]                               [2]                     [3]                     [4]
                    [1,2]           [1,3]   [1,4]       [2,3]       [2,4]               [3,4]
               [1,2,3]  [1,2,4]     [1,3,4]            [2,3,4]
               [1,2,3,4]



     */

//    public static List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        subsets(nums, 0, new ArrayList<>(), result);
//        return result;
//    }
//
//    public static void subsets(int[] nums, int index, List<Integer> currentSubset, List<List<Integer>> result) {
//        result.add(new ArrayList<>(currentSubset));
//        if(index == nums.length)
//            return;
//        for(int i = index; i < nums.length; i++){
//            currentSubset.add(nums[i]);
//            subsets(nums, i + 1, currentSubset, result);
//            currentSubset.remove(currentSubset.size() -1);
//        }
//    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums == null || nums.length == 0){
            return result;
        }
        int s;
        for(int n:nums){
            s = result.size();
            for(int i = 0;i<s;i++){
                List<Integer> set = new ArrayList<>(result.get(i));
                set.add(n);
                result.add(set);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[] {1,2,3,4}));
    }
}
