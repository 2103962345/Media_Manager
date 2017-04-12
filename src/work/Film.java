package work;

public class Film extends Information{
	
	public enum Status {
		  WATCHED {
		    @Override
		    public String toString() {
		      return "WATCHED";
		    }
		  },
		  WANT_WATCH {
		    @Override
		    public String toString() {
		      return "WANT WATCH";
		    }
		  },
		  WATCHING {
		    @Override
		    public String toString() {
		      return "WATCHING";
		    }
		  }
	}

	Film(String name) {
		this.name = name;
		}

	@Override
	void printName() {
		System.out.println(this.name+" film");
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
			System.out.print(name+" film status "+"\""+getStatus()+"\""+"\n");
		};
		
}
