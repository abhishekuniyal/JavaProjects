package bankaccountapp;

public interface IBaseRate {
	
	default double baseRate() {
		return 2.25;
	}
}
