package base;

public class TextNote extends Note {

	public String content= new String();
	
	public TextNote(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	

}
