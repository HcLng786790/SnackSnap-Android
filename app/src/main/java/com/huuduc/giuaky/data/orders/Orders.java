package com.huuduc.giuaky.data.orders;

import com.huuduc.giuaky.data.address.Address;

import java.util.List;

public class Orders {

    public static final int DELIVERY = 1;
    public static final int PICKUP = 2;
    private Long id;
    private List<Address> addressList;

    private double totalPrice;
    private int typeDelivery;

    public Orders(){}

    public Orders(Long id, List<Address> addressList, double totalPrice, int typeDelivery) {
        this.id = id;
        this.addressList = addressList;
        this.totalPrice = totalPrice;
        this.typeDelivery = typeDelivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTypeDelivery() {
        return typeDelivery;
    }

    public void setTypeDelivery(int typeDelivery) {
        this.typeDelivery = typeDelivery;
    }
}
