package base;
import java.io.Serializable;
import java.util.Date;

public class Note implements Comparable<Note>,Serializable{
	
	
	/**
	 * Default ID
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String title;
	
	public Note(String title){
		this.title=title;
		date=new Date(System.currentTimeMillis());
	}
	
	public String getTitle(){
		return this.title;
	}
	public Date getDate(){
		return this.date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int compareTo(Note o) {
		long comp=this.date.getTime()-o.getDate().getTime();
		if (comp<0)
			return 1;
		else if (comp>0)
			return -1;
		else 
			return 0;
		
	}

	@Override
	public String toString() {
		return date.toString() + "\t" + title;
	}

}
