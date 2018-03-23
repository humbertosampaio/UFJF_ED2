package handlers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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

		try (FileInputStream fileInputStream = new FileInputStream(file))
		{
			// Inicializa o leitor e o array de bytes temporario
			FileChannel ch = fileInputStream.getChannel();
			byte[] byteArray = new byte[1024 * 1024];
			ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
			int bytesReadQuantity;
			
			// Inicializa padroes de leitura do arquivo
			int i = 0;
			int newLineCharacterCode = 10;
			String[] obj = new String[6];
			StringBuilder tempString = new StringBuilder(byteArray.length);
			int objPosition = 0;
			int quotationMarksCount = 0;
			long registriesCount = 0;
			
			// Ignora o cabecalho
			bytesReadQuantity = ch.read(byteBuffer);
			for(i = 1; i < byteArray.length && byteArray[i-1] != newLineCharacterCode; ++i);

			do
			{				
				for (; i < bytesReadQuantity; i++)
				{
					char c = (char)byteArray[i];

					if (c != ',' && c != '\n')
					{
						if (c == '"') 
							quotationMarksCount++;
						tempString.append(c);
					}
					else if (quotationMarksCount % 2 == 0)
					{
						obj[objPosition] = tempString.toString();
						tempString.setLength(0);
						objPosition++;
					}
					
					if(objPosition > 5)
					{
						question = new Question(obj);
						questionsList.add(question);
						registriesCount++;

						if (registriesCount % 50000 == 0)
							System.out.println("Registros lidos: " + registriesCount);
						
						objPosition = 0;
						quotationMarksCount = 0;
						obj = new String[obj.length];
					}
				}
				byteBuffer.clear();
				i = 0;
			} while ((bytesReadQuantity = ch.read(byteBuffer)) != -1);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
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
