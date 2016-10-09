package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class TextNote extends Note {

	/**
	 *  load a TextNote from File f.
	 *  
	 *  The tile of the TextNote is the name of the file
	 * 	The content of the TextNote is the content of the file
	 */
	private static final long serialVersionUID = 1L;
	public String content= new String();
	
	public TextNote(File f){
		super(f.getName());
		this.content= getTextFromFile(f.getAbsolutePath());
	}
	
	
	public TextNote(String title) {
		super(title);
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	
	public String getTextFromFile(String absolutePath){
		String result="";
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(absolutePath));
			String line =br.readLine();
			while (line!=null){
				result+=line +'\n';
				line=br.readLine();
			}
			br.close();
		}catch (FileNotFoundException e){
			System.out.println("Sorry! File not found!");
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public void exportTextToFile(String pathFolder){
		
		String filename=pathFolder + File.separator + this.getTitle() + ".txt";
		filename=filename.replaceAll(" ", "_");
		File file=new File(filename);

		try{
			FileWriter fr = new FileWriter(file);
			fr.write(content);
			fr.close();
		}catch (FileNotFoundException ex){
			System.out.println("Sorry! File not found!");
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public Character unknowFunction(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
					if (count.get(c) > b) {
						b = count.get(c);
						r = c;
					}
				}
			}
		}
		return r;
	}


	private String getContent() {
		return content;
	}
}
