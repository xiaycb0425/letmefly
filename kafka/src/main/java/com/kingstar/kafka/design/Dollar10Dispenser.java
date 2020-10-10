package com.kingstar.kafka.design;

/**
 * @author xiayc
 * @date 2020/9/30 14:35
 */
public class Dollar10Dispenser implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 10){
            int num = cur.getAmount()/10;
            int remainder = cur.getAmount() % 10;
            System.out.println("Dispensing "+num+" 10$ note");
            if(remainder !=0) {
                this.chain.dispense(new Currency(remainder));
            }
        }else{
            this.chain.dispense(cur);
        }

    }
}
