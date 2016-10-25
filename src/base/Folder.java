package base;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Folder implements Comparable<Folder>,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		this.name=name;
		notes=new ArrayList<Note>();
	}
	
	public void addNote(Note note){
		notes.add(note);
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Note> getNotes(){
		return this.notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int nText=0;
		int nImage=0;
		
		for (Note f : this.notes){
			if (f instanceof TextNote)
				nText+=1;
			if (f instanceof ImageNote)
				nImage+=1;			
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder o) {
		int comp=this.name.compareTo(o.getName());
		if (comp>0)
			return 1;
		else if (comp<0)
			return -1;
		else 
			return 0;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		String[] keys=keywords.split(" ");
		List<Note> lists = new ArrayList<Note>();

		
		for (Note n : this.notes){
			boolean Flag=true;
			
			if (n instanceof TextNote){
				int i=0;
				
				while ((i<keys.length)&&(Flag)) {
					Flag=false; 
					do {
						if (keys[i].equalsIgnoreCase("or"))
							i++;
						if ((n.getTitle().toLowerCase().indexOf(keys[i].toLowerCase())>=0)||(((TextNote) n).content.toLowerCase().indexOf(keys[i].toLowerCase())>=0))
							{Flag=true;}
						i++;
					} while ((i<keys.length)&&(keys[i].equalsIgnoreCase("or")));

				}
				if (Flag)
					lists.add(n);		
			}
			
			if (n instanceof ImageNote) {
				int i=0;
				
				while ((i<keys.length)&&(Flag)) {
					Flag=false; 
					do {
						if (keys[i].equalsIgnoreCase("or"))
							i++;
						if ((n.getTitle().toLowerCase().indexOf(keys[i].toLowerCase())>=0))
							{Flag=true;}
						i++;
					} while ((i<keys.length)&&(keys[i].equalsIgnoreCase("or")));

				}
				if (Flag)
					lists.add(n);				
			}
		}
		return lists;
	}
	
	public boolean removeNotes(String title){
		int n1=notes.indexOf(new TextNote(title));
		if (n1>=0){
			notes.remove(n1);
			return true;

		}
		
		return false;
	}
}
