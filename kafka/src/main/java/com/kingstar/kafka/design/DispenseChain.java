package com.kingstar.kafka.design;

/**
 * @author xiayc
 * @date 2020/9/30 14:34
 */
public interface DispenseChain {

    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}
