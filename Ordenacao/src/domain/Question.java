package domain;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import handlers.QuestionHandler;

public class Question
{
	private int id;
	private String ownerUserId;
	private LocalDateTime creationDate;
	private int score;
	private String title;
	private QuestionHandler questionHandler;
	
	public Question(String id, String ownerUserId, String creationDate, String score, String title)
	{
		this.id = Integer.parseInt(id);
		this.ownerUserId = ownerUserId;
		this.score = Integer.parseInt(score);
		this.title = title;
		
		LocalDateTime ldt = LocalDateTime.parse(creationDate.substring(0, 19));
		this.creationDate = ldt;
		
		this.questionHandler = new QuestionHandler();
	}

	public Question(String[] question)
	{
		this.id = Integer.parseInt(question[0]);
		this.ownerUserId = question[1];
		LocalDateTime ldt = LocalDateTime.parse(question[2].substring(0, 19));
		this.creationDate = ldt;
		this.score = Integer.parseInt(question[3]);
		this.title = question[4];
		this.questionHandler = new QuestionHandler();
	}
		
	public void writeToCsvFile(File file)
	{
		try(BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file, false)))
		{
			file.createNewFile();
			String header = this.questionHandler.getCsvHeader();
			writer.write(header.getBytes());
			writer.write(this.toCsvString().getBytes());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
		
	public String toString()
	{
		String str = "";
		str += "Id: " + this.id + '\n';
		str += "Owner User Id: " + this.ownerUserId + '\n';
		str += "Creation Date: " + this.questionHandler.getFormattedDate(this.creationDate) + '\n';
		str += "Score: " + this.score + '\n';
		str += "Title: " + this.title + '\n';
		return str;
	}
	
	public String toCsvString(String separator)
	{
		String str = "";
		str += (this.id + separator);
		str += (this.ownerUserId + separator);
		str += (this.questionHandler.getFormattedDate(this.creationDate) + separator);
		str += (this.score + separator);
		str += (this.title + '\n');
		
		return str;
	}
	
	public String toCsvString()
	{
		final String defaultSeparator = ",";
		return this.toCsvString(defaultSeparator);
	}
}
