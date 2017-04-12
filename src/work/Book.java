package work;

public class Book extends Information{

	public enum Status {

		  READ {
		    @Override
		    public String toString() {
		      return "READ";
		    }
		  },
		  WANT_READ {
		    @Override
		    public String toString() {
		      return "WANT READ";
		    }
		  },
		  READING {
		    @Override
		    public String toString() {
		      return "READING";
		    }
		  }
	}
	
	Book(String name){
		this.name = name;
		}

	@Override
	void printName() {
		System.out.println(this.name+" book");
		}
	
	@Override
	void addStatus(String status) {
    	for(Status element : Status.values())
    		if(element.toString().equals(status))
    			this.status = element.ordinal();
    	}
	
	@Override
	 String getStatus() {
	    	return Status.values()[status].toString();
	    	}
	
	@Override
	void print() {
		System.out.println(this.name+" book status "+"\""+getStatus()+"\"");
		};
		
}
