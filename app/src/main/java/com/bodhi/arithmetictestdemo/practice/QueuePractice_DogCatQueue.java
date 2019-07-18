package com.bodhi.arithmetictestdemo.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 10:14
 * desc :提供一个宠物类，猫类和狗类 要实现一个队列结构 单独取所有的猫或所有的狗，或按猫狗先后顺序依次取出
 */
public class QueuePractice_DogCatQueue {


    //基础宠物类
    public static class Pet{
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return type == null ? "" : type;
        }
    }

    //基础狗类
    public static class Dog extends Pet{
        public Dog() {
            super("dog");
        }
    }

    //基础猫类
    public static class Cat extends Pet{
        public Cat() {
            super("cat");
        }

    }

    //************************************下面为解法******************************************
    public static class PetEnter{
        private Pet pet;//哪一种宠物
        private long count;//第几个进来的(相当于时间)

        public PetEnter(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }

        public String getEnterPetType(){
            return this.pet.getType();
        }
    }

    public static class DogCatQueue{
        private Queue<PetEnter> dogQ;
        private Queue<PetEnter> catQ;
        private long count;

        public DogCatQueue(){
            this.dogQ = new LinkedList<PetEnter>();
            this.catQ = new LinkedList<PetEnter>();
            this.count = 0;
        }

        public void add(Pet pet){
            if(pet.getType().equals("dog")){
                this.dogQ.add(new PetEnter(pet,this.count++));
            }else if(pet.getType().equals("cat")){
                this.catQ.add(new PetEnter(pet,this.count++));
            }else{
                throw new RuntimeException("err,not dog or cat");
            }
        }

        public Pet pollAll(){
            if (!dogQ.isEmpty()&&!catQ.isEmpty()) {
                if(dogQ.peek().getCount()<catQ.peek().getCount()){
                    return dogQ.poll().getPet();
                }else{
                    return catQ.poll().getPet();
                }
            }else if(!dogQ.isEmpty()){
                return dogQ.poll().getPet();
            } else if (!this.catQ.isEmpty()) {
                return catQ.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        public Dog pollDog(){
            if (!isDogQueueEmpty()) {
                return (Dog)dogQ.poll().getPet();
            }else{
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat(){
            if (!this.isCatQueueEmpty()) {
                return (Cat) this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isEmpty(){
            return this.dogQ.isEmpty()&&this.catQ.isEmpty();
        }

        public boolean isDogQueueEmpty(){
            return this.dogQ.isEmpty();
        }

        public boolean isCatQueueEmpty(){
            return this.catQ.isEmpty();
        }

    }



}
