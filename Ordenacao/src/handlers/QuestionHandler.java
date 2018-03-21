package handlers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import domain.Question;

public class QuestionHandler implements ICsvHandler<Question>
{
	@Override
	public List<Question> getEntries(File file)
	{
		System.out.println("Obtendo registros do arquivo \"" + file.getName() + "\"...");
		List<Question> questionsList = new LinkedList<Question>();
		Question question;

		try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file), 500 * 1024))
		{
			final int newLineCharacterCode = 10;
			while (reader.read() != newLineCharacterCode)
				continue;

			String[] obj = new String[6];
			String tempString = "";
			int objPosition = 0;
			int quotationMarksCount = 0;
			long registriesCount = 0;
			
			int i = reader.read();

			while (i != -1)
			{
				while ((objPosition < 6) && (i != -1))
				{
					char c = (char) i;

					if (c != ',' && c != '\n')
					{
						if (c == '"') quotationMarksCount++;
						tempString += c;
					}
					else if (quotationMarksCount % 2 == 0)
					{
						obj[objPosition] = tempString;
						tempString = "";
						objPosition++;
					}

					i = reader.read();
				}
				
				question = new Question(obj);
				questionsList.add(question);
				registriesCount++;
				
				if(registriesCount % 1000 == 0)
					System.out.println("Registros lidos: " + registriesCount);

				objPosition = 0;
				quotationMarksCount = 0;
				obj = new String[obj.length];
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return questionsList;
	}

	@Override
	public String getCsvHeader()
	{
		return "Id,OwnerUserId,CreationDate,Score,Title\n";
	}

	@Override
	public void writeToCsvFile(List<Question> list, File targetFile)
	{
		try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(targetFile, false)))
		{
			targetFile.createNewFile();
			String header = getCsvHeader();
			writer.write(header.getBytes());
			for (Question q : list)
				writer.write(q.toCsvString().getBytes());
			System.out.println("Questões salvas com sucesso no arquivo \"" + targetFile.getAbsolutePath() + "\"!");
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

	public String getFormattedDate(LocalDateTime date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		return date.format(formatter);
//		return date.toString();
	}
}
