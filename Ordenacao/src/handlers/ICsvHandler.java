package handlers;

import java.io.File;
import java.util.List;

public interface ICsvHandler<T>
{
	public List<T> getEntries(File file);
	
	public String getCsvHeader();
	
	public void writeToCsvFile(List<T> list, File targetFile);
}
