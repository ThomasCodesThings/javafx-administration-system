package model.customer;

public aspect ProductAmountControl {
	private boolean range(int amount) {
		return amount > 0;
	}

	void around(int amount): call(void Product.setAmount(..)) && args(amount) {
		if(range(amount)){
			System.out.println(amount);
			proceed(amount);
		}else{
			System.out.println("Invalid number");
		}
	}

}
