package com.bodhi.arithmetictestdemo.practice;

import android.util.Property;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/25 21:25
 * desc :贪心问题 安排会议的场次最多
 *
 * 【题目】给你每一个项目开始的时间和结束的时间间(给你一个数组，里面是一个个具体的项目)安排宣讲的日程，
 * 要求会议室进行的宣讲的场次最多. 会议室不能同时容纳两个项目的宣讲。返回这个最多的宣讲场次.
 *
 * 贪心策略思路：
 * 1.首先按项目的结束时间那个结束时间早 先安排那个项目开始
 * 2.然后排除掉项目开始时其他不能进行的项目
 * 3.当项目宣讲结束后 继续按结束时间早的安排 然后重复下去
 *
 */
public class GreedyPractice_MoreMeetingArrange {
    public static class Program{
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 按项目结束时间排序 结束早的排在前面
     */
    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs,int curTime){
        //按项目结束时间排序
        Arrays.sort(programs,new ProgramComparator());
        //一共安排的项目场次
        int result =0;

        for (int i = 0; i < programs.length; i++) {
            //项目的开始时间大于当前时间 才可以安排
            if(curTime<=programs[i].start){
                result++;
                //宣讲完的当前时间
                curTime=programs[i].end;
            }
        }
        return result;
    }




}
