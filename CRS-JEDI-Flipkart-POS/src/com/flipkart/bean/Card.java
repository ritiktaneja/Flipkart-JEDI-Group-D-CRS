package com.flipkart.bean;

import com.flipkart.constants.CardType;

public class Card extends  PaymentOnline{

    private long cardNumber;
    private CardType cardType;

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
}
