package com.kingstar.kafka.design;

/**
 * @author xiayc
 * @date 2020/9/30 14:34
 */
public class Currency {
    private int amount;

    public Currency(int amt){
        this.amount=amt;
    }

    public int getAmount(){
        return this.amount;
    }
}
