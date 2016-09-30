package base;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable {
	/**
	 * default ID
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	/**
	 * This method save the Notebook instance to file. 
	 * @param file The file to save
	 * @return	
	 */
	public boolean save(String file){
		
		FileOutputStream fos=null;
		ObjectOutputStream out=null;
		
		try{
			fos=new FileOutputStream(file);
			out=new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Constructor of an object NoteBook from an object serialization on disk 
	 * @param file, the path of the file for loading the object serialization
	 */
	public NoteBook(String file){
		FileInputStream fis=null;
		ObjectInputStream in=null;
		
		try{
			fis=new FileInputStream(file);
			in =new ObjectInputStream(fis);
			NoteBook n =(NoteBook)in.readObject();
			folders=n.folders;
			in.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public NoteBook(){
		folders=new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title){
		TextNote note=new TextNote(title);
		return insertNote(folderName,note);
	}
	
	public boolean createTextNote(String foldername, String title, String content){
		TextNote note=new TextNote(title,content);
		return insertNote(foldername,note);
		
	}
	
	public boolean createImageNote(String folderName, String title){
		ImageNote note=new ImageNote(title);
		return insertNote(folderName,note);
	} 
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}

	private boolean insertNote(String folderName, Note note){
		Folder f=null;
		for (Folder f1: folders){
			if (f1.equals(new Folder(folderName))){
				f=f1;
				break;
			}
		}
		
		if (f==null){
			f=new Folder(folderName);
			folders.add(f);
		}
		
		for (Note n: f.getNotes()){
			if (n.equals(note)){
				System.out.println("Creating note " + note.getTitle()+" under folder "+ folderName + " failed");
				return false;
			}			
		}
		
		f.getNotes().add(note);
		
		return true;
	}
	
	public void sortFolders() {
		Collections.sort(folders);
		
		for (Folder f: folders){
			f.sortNotes();
		}
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> lists = new ArrayList<Note>();
		
		for (Folder f1: folders){
			lists.addAll(f1.searchNotes(keywords));
		}
		
		return lists;
	}
}
