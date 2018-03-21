package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import handlers.TagHandler;

public class Tag
{
	private int id;
	private String tag;
	private TagHandler tagHandler;
	
	public Tag(int id, String tag)
	{
		this.id = id;
		this.tag = tag;
		this.tagHandler = new TagHandler();
	}
	
	public Tag(String[] tag)
	{
		this.id = Integer.parseInt(tag[0]);
		this.tag = tag[1];
		this.tagHandler = new TagHandler();
	}
	
	public void writeToCsvFile(File file)
	{
		try (BufferedWriter writter = new BufferedWriter(new FileWriter(file)))
		{
			String header = this.tagHandler.getCsvHeader();
			writter.write(header);
			writter.write(this.toCsvString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		String str = "";
		str += ("Id: " + this.id + "\n");
		str += ("Tag: " + this.tag + "\n");
		return str;		
	}

	public String toCsvString(String separator)
	{
		String str = "";
		str += this.id + separator + this.tag + "\n";
		return str;
	}
	
	public String toCsvString()
	{
		return this.toCsvString(",");
	}
}
