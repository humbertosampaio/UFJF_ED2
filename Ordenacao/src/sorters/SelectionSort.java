package sorters;

import java.util.List;

public class SelectionSort implements ISorter
{

	@Override
	public List<Integer> sort(List<Integer> list)
	{
		int size = list.size();
		Integer smallestElementIndex;
		Integer temp;
		for(int i = 0; i < size-1; i++)
		{
			smallestElementIndex = i;
			for(int j = i+1; j < size; j++)
			{
				if(list.get(j) < list.get(smallestElementIndex))
					smallestElementIndex = j;
			}
			temp = list.get(i);
			list.set(i, list.get(smallestElementIndex));
			list.set(smallestElementIndex, temp);
		}
		
		return list;
	}

}
