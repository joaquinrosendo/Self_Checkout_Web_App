
package com.autoscouts.jsp.tagdemo;

public class CustomerCheckout {

    private int item;
    private int cash;
    private int cardNumber;
    private int pinNumber;
    private int total;

    public CustomerCheckout(int item, int cash, int cardNumber, int pinNumber, int total) {
        this.item = item;
        this.cash = cash;
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
        this.total = total;
    }
    
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
