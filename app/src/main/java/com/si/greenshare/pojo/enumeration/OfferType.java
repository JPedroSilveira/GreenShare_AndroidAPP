package com.si.greenshare.pojo.enumeration;

/**
 * Created by joao.silva.
 */
public enum OfferType {
	Donation(0), Sale(1);

	private int offerType;

	OfferType(int offerType) {
		this.offerType = offerType;
	}

	public int getOfferType() {
		return this.offerType;
	}

	public static boolean exists(int id) {
		for (OfferType e : values()) {
			if (e.getOfferType() == id) {
				return true;
			}
		}
		return false;
	}
}
