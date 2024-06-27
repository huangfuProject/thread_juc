package com.five;

import java.util.*;

/**
 * @author huangfukexing
 * @date 2023/11/2 17:47
 */
public class ChildThreadLocal {
    private final static ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
//        THREAD_LOCAL.set(1);
//        System.out.println("线程：" + Thread.currentThread().getName() + "获取到数据为:" + THREAD_LOCAL.get());
//        //获取主线程的值
//        Integer integer = THREAD_LOCAL.get();
//        new Thread(()->{
//            //设置到子线程
//            THREAD_LOCAL.set(integer);
//            System.out.println("线程：" + Thread.currentThread().getName() + "获取到数据为:" + THREAD_LOCAL.get());
//        }, "子线程").start();

        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.remove(0));
        System.out.println(obj.remove(0));
        System.out.println(obj.insert(0));
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(0));
        System.out.println(obj.insert(0));
        productExceptSelf(new int[]{1,2,3,4,5,6});
    }

    //维护两个变量，beforeSum表示前缀和，afterSum表示后缀和
    public static int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        Arrays.fill(ans,1);
        int beforeSum=1;
        int afterSum=1;
        for(int i=0,j=n-1;i<n;i++,j--){
            ans[i] = ans[i] * beforeSum;
            ans[j]= ans[j] * afterSum;
            beforeSum = beforeSum * nums[i];
            afterSum = afterSum * nums[j];
        }
        return ans;
    }

    static class RandomizedSet {

        Random random;
        List<Integer> list;
        Map<Integer, Integer> indexMap;

        public RandomizedSet() {
            random = new Random();
            list = new ArrayList<Integer>();
            indexMap = new HashMap<>();
        }

        public boolean insert(int val) {
            if(indexMap.containsKey(val)) {
                return false;
            }

            int lastIndex = list.size();
            indexMap.put(val, lastIndex);
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if(!indexMap.containsKey(val)) {
                return false;
            }

            // 获取当前值的索引数据
            int valIndex = indexMap.remove(val);

            // 获取当前列表的最后一个数据
            int lastVal = list.get(list.size() - 1);

            // 将最后一个数据替换到对应的索引列表
            list.set(valIndex, lastVal);
            // 重新建立索引
            indexMap.put(lastVal, valIndex);

            // 删除最后一个数据
            list.remove(list.size() - 1);

            return true;
        }


        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

}
