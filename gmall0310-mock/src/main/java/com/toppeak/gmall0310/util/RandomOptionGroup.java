package com.toppeak.gmall0310.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomOptionGroup<T> {
    int totalWeight = 0;
    List<RanOpt> optList  = new ArrayList<>();

    public RandomOptionGroup(RanOpt<T>... opts) {
        for (RanOpt opt: opts) {
            totalWeight += opt.getWeight();
            for (int i = 0; i < opt.getWeight(); i++){
                optList.add(opt);
            }

        }
    }

    public RanOpt<T> getRandomOpt(){
        int i = new Random().nextInt(totalWeight);
        return optList.get(i);
    }


    public int getTotalWeight() {
        return totalWeight;
    }

    public List<RanOpt> getOptList() {
        return optList;
    }

    public static void main(String[] args) {
        RanOpt[] opts= {new RanOpt("andriod",20),new RanOpt("ios",30),new RanOpt("sysbian",50)};
        RandomOptionGroup randomOptionGroup = new RandomOptionGroup(opts);
        System.out.println(randomOptionGroup.optList.size());
        for (int i = 0; i <10 ; i++) {
            System.out.println(randomOptionGroup.getRandomOpt().getValue());
        }
    }
}
