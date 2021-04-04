class Singleton extends MyClone implements Serializable{

   private volatile Singleton _instance =null;

   private Singleton(){
	if(_instance !=null)
	   throw new IllegalStateException("Object Can't be created via Reflection");
   }

   @override
   protected Object clone() throws CloneNotSupportedException{
      throw new CloneNotSupportedException();
   }

  protected Object readResolve(){
      return _instance; // if deserializing , return the same instance
  }
   public static Singleton getInstance(){
	if(_instance == null){
	   Synchronized(this){
		if(_instance == null){
		   _instance = new Singleton();
		}
	   }
	}
	return _instance;
   }
}

*********************************************************
class MyClone implements Clonable{
   @override
   protected Object clone() throws CloneNotSupportedException{
      super.clone();
   }

}
