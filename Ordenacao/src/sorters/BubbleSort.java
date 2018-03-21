package sorters;

import java.util.List;

public class BubbleSort implements ISorter
{
	@Override
	public List<Integer> sort(List<Integer> list)
	{
		Integer temp;
		
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = i+1; j < list.size(); j++)
			{
				if(list.get(i) > list.get(j))
				{
					temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		
		return list;
	}
}
