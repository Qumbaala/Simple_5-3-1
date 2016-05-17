package com.crookedqueue.simple531.Model.RoundingRules;

/**
 * Created by Jason on 11/30/2015.
 */
public class ImperialRoundDown implements Roundable{

    public ImperialRoundDown() {
    }

    @Override
    public double performCalc(double weight) {
        return (5 * (Math.floor(Math.abs(weight / 5))));
    }
}
