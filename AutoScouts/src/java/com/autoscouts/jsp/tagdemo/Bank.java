package com.autoscouts.jsp.tagdemo;


public class Bank {

    private float cash;
    private int credit;
    private int debit;
    private int pin;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public Bank(int debit, int pin) {
        this.debit = debit;
        this.pin = pin;
    }

    public Bank(float cash) {
        this.cash = cash;
    }

    public Bank(int credit) {
        this.credit = credit;
    }
    
    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getDebit() {
        return debit;
    }    
}
