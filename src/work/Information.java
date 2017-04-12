package work;

public abstract class Information {
	String name = "";
    int status = 1;
   
    abstract void addStatus(String status);

    String getName() {
    	return this.name;
    	}
    
    abstract String getStatus();
    abstract void print();
    abstract void printName();
}
