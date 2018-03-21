package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import handlers.AnswerHandler;

public class Answer
{
	private int id;
	private String ownerUserId;
	private LocalDateTime creationDate;
	private int parentId;
	private int score;
	
	public Answer(String id, String ownerUserId, String creationDate, String parentId, String score)
	{
		this.id = Integer.parseInt(id);
		this.ownerUserId = ownerUserId;
		
		LocalDateTime ldt = LocalDateTime.parse(creationDate.substring(0, 19));
		this.creationDate = ldt;
		
		this.parentId = Integer.parseInt(parentId);
		this.score = Integer.parseInt(score);
	}
	
	Answer(String[] answer)
	{
		this.id = Integer.parseInt(answer[0]);
		this.ownerUserId = answer[1];
		
		LocalDateTime ldt = LocalDateTime.parse(answer[2].substring(0, 19));
		this.creationDate = ldt;
		
		this.parentId = Integer.parseInt(answer[3]);
		this.score = Integer.parseInt(answer[4]);
	}	
	
	public void writeToCsvFile(File file)
	{
		AnswerHandler answerHandler = new AnswerHandler();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			writer.write(answerHandler.getCsvHeader());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
