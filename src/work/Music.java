package work;

public class Music extends Information{

	public enum Status {

		  LISTENED {
		    @Override
		    public String toString() {
		      return "LISTENED";
		    }
		  },
		  WANT_LISTEN {
		    @Override
		    public String toString() {
		      return "WANT LISTEN";
		    }
		  },
		  LISTENING {
		    @Override
		    public String toString() {
		      return "LISTENING";
		    }
		  }
	}

	Music(String name){
		this.name = name;
		}
	
	@Override
	void printName() {
		System.out.println(this.name+" music");
		}
	
	@Override
	 String getStatus() {
	    	return Status.values()[status].toString();
	    	}

	@Override
	void addStatus(String status) {
    	for(Status element : Status.values())
    		if(element.toString().equals(status))
    			this.status = element.ordinal();
    	}
	
	@Override
	void print() {
		System.out.print(name+" music status "+"\""+getStatus()+"\""+"\n");
		};
}
