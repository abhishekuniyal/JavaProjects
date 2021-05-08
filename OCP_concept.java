interface IClaim{
   boolean isValidClaim();
}

class ChequesClaim() implements ICliam{
	boolean isValidClaim(){
		//claim logic for chq
		return true;
	}
}


class StubClaim() implements ICliam{
	boolean isValidClaim(){
		//claim logic for chq
		return true;
	}
}


void claimManager(IClaim iClaim){
	if(iClaim.isValidClaim()){
		//start processing the claim
	}
}



**************************88
Iclaim chequesClaim = new ChequesClaim();
Iclaim stubClaim = new StubClaim();

Claim.claimManager(chequesClaim);
Claim.claimManager(stubClaim);



*******************************************************Visitor DP*******************************************************

interface Visitor{
	double visit(Liquor);
	double visit(Neccesity);
}

class TaxVisitorImpl imlements Visitor{
	public double visit(Liquor l){
	//logic to calculate Tax
	return liquorTax;
	}

	public double visit(Neccesity nc){
	//logic to calculate Tax
	return neccesityTax;
	}
}



interface Visitable{
	void accept(Visitor);
}

class Liquor implements Visitable{
	int price;
	Liquor(int price){
	  this.price = price;
	}
	
	double accept(Visitor visitor){
		visitor.visit(this);
	}

}

class Neccesity implements Visitable{
	int price;
	Neccesity(int price){
	  this.price = price;
	}
	
	double accept(Visitor visitor){
		visitor.visit(this);
	}

}


class Main{

	TaxCalculator taxCalc = new TaxCalculator();

	Liquor liquor = new Liquor(100);
	Neccesity milk = new Neccesity(30);

	//show me tax calulated on liquor
	liqour.accept(taxCalc);
	milk.accept(taxCalc);
}




