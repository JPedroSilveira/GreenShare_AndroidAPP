package com.si.greenshare.pojo.enumeration;

/**
 * Created by joao.silva.
 */
public enum OfferStatus {
	Active(0), 
	Closed(1);
	
	private int offerStatus;
	
	OfferStatus(int offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getValue() { 
        return this.offerStatus;
    }
    
    public static boolean exists(int id) {
        for(OfferStatus e : values()) {
            if (e.getValue() == id) {
                return true;
            }
        }
        return false;
    }
}
