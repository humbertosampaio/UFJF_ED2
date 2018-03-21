package handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import domain.Tag;

public class TagHandler implements ICsvHandler<Tag>
{
	@Override
	public List<Tag> getEntries(File file)
	{
		System.out.println("Obtendo registros do arquivo \"" + file.getName() + "\"...");
		List<Tag> list = new LinkedList<Tag>();
		Tag tag;
		String line;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			reader.readLine();
			
			while((line = reader.readLine()) != null)
			{
				tag = new Tag(line.split(","));
				list.add(tag);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public String getCsvHeader()
	{
		return "Id,Tag\n";
	}

	@Override
	public void writeToCsvFile(List<Tag> list, File targetFile)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile)))
		{
			String header = this.getCsvHeader();
			writer.write(header);
			for(Tag t : list)
				writer.write(t.toCsvString());
			
			System.out.println("Tags salvas com sucesso no arquivo \"" + targetFile.getAbsolutePath() + "\"!");
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
