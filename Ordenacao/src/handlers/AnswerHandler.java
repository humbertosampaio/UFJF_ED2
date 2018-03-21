package handlers;

import java.io.File;
import java.util.List;

import domain.Answer;

public class AnswerHandler implements ICsvHandler<Answer>
{

	@Override
	public List<Answer> getEntries(File file)
	{
		return null;
	}

	@Override
	public String getCsvHeader()
	{
		return "Id,OwnerUserId,CreationDate,ParentId,Score,Body\n";
	}

	@Override
	public void writeToCsvFile(List<Answer> list, File targetFile)
	{
		
	}
	
}
