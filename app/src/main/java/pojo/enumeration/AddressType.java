package pojo.enumeration;

/**
 * Created by joao.silva.
 */
public enum AddressType {
	Apartment(1), House(2), Commercial(3);

	private int addressType;

	AddressType(int addressType) {
		this.addressType = addressType;
	}

	public int getValue() {
		return this.addressType;
	}

	public static boolean exists(int id) {
		for (AddressType e : values()) {
			if (e.getValue() == id) {
				return true;
			}
		}
		return false;
	}
}
