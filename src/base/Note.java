package base;
import java.util.Date;

public class Note implements Comparable<Note>{
	
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
		// TODO Auto-generated method stub
		int comp=this.date.compareTo(o.getDate());
		if (comp<0)
			return 1;
		else if (comp>0)
			return -1;
		else 
			return 0;
		
	}

}
