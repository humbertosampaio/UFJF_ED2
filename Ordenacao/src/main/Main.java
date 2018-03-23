package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import domain.Question;
import domain.Tag;
import handlers.QuestionHandler;
import handlers.TagHandler;
import sorters.BubbleSort;
import sorters.SelectionSort;

public class Main
{

	public static void main(String[] args)
	{
		final long startTime = System.nanoTime();
		
		writeQuestionsToCsvFile();
//		writeTagsToCsvFile();
//		bubbleSort();
//		selectionSort();
		
		final long duration = System.nanoTime() - startTime;
		long totalSegundos = TimeUnit.NANOSECONDS.toSeconds(duration);
		int minutos = (int)(totalSegundos / 60);
		long segundos = minutos == 0 ? totalSegundos : totalSegundos % minutos;
		System.out.println("Tempo decorrido: " + minutos + "m " + segundos + "s");
	}
	
	public static void writeQuestionsToCsvFile()
	{
		File questionFile = new File("E:\\UFJF\\4º Período\\Estruturas de Dados 2\\UFJF_ED2\\Ordenacao\\io_files\\OriginalFiles\\Questions_Original.csv");
		QuestionHandler questionHandler = new QuestionHandler();
		List<Question> questionList = questionHandler.getEntries(questionFile);
		File targetFile = new File("io_files/Questions_Output.csv");
		questionHandler.writeToCsvFile(questionList, targetFile);
	}
	
	public static void writeTagsToCsvFile()
	{
		File tagFile = new File("io_files/Tags.csv");
		TagHandler tagHandler = new TagHandler();
		List<Tag> tagList = tagHandler.getEntries(tagFile);
		File outputFile = new File("io_files/Tags_Output.csv");
		tagHandler.writeToCsvFile(tagList, outputFile);
	}
	
	public static List<Integer> getRandomIntegerList(int listSize)
	{
		Random randomizer = new Random();
		List<Integer> list = new ArrayList<>(listSize);
		
		for(int i = 0; i < listSize; i++)
			list.add(randomizer.nextInt(199) - 100);
		
		return list;
	}
	
	public static void bubbleSort()
	{
		List<Integer> list = getRandomIntegerList(10);
		System.out.println(list.toString());
		
		BubbleSort sorter = new BubbleSort();
		list = sorter.sort(list);
		System.out.println(list.toString());
	}
	
	public static void selectionSort()
	{
		List<Integer> list = getRandomIntegerList(10);
		System.out.println(list.toString());
		
		SelectionSort sorter = new SelectionSort();
		list = sorter.sort(list);
		System.out.println(list.toString());
	}
}
