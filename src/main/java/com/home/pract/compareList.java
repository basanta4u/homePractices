package com.home.pract;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class compareList implements ArgumentList {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		System.out.println("Execution start Time -- " + startTime + "  --");
		// TODO Auto-generated method stub
		compareList cl = new compareList();
		int usrInput = cl.takeUserInput("compare");
		switch (usrInput) {
		case 1:
			cl.comparePredefinedList(cl);
			break;
		case 2:
			cl.compareUserInputList(cl);
			break;
		case 3:
			cl.compareFileInputList(cl);
			break;
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Execution End Time -- " + endTime + "  --");
		long totalTime = startTime - endTime;
		System.out.println("Total Time taken in milliseconds -- " + totalTime + "  --");

		Duration duration = Duration.ofMillis(totalTime);

		Long minutes = duration.toMinutes();
		Long seconds = duration.minusMinutes(minutes).getSeconds();

		System.out.println("Total Time taken - " + minutes + "min " + seconds + "sec");

	}

	private void compareUserInputList(compareList cl) {
		// TODO Auto-generated method stub
		System.out.println("#       Please provide User Input L1                  #");
		List<String> L1 = takeUserInputList();
		System.out.println("#       Please provide User Input L2                  #");
		List<String> L2 = takeUserInputList();

		int usrInput = cl.takeUserInput("show");
		switch (usrInput) {
		case 1:
			cl.printListItem(cl.getCommonFromList(L1, L2));
			break;
		case 2:
			cl.printListItem(cl.getDiffFromL1(L1, L2));
			break;
		case 3:
			cl.printListItem(cl.getDiffFromL2(L1, L2));
			break;
		}

	}

	private void compareFileInputList(compareList cl) {
		// TODO Auto-generated method stub

		try {

			List<String> L1 = readFile("com/company/resource/ListOne.txt");
			List<String> L2 = readFile("com/company/resource/ListTwo.txt");

			System.out.println("Total Element in List One  =" + L1.size());
			System.out.println("Total Element in List Two  =" + L2.size());
			int usrInput = cl.takeUserInput("show");
			switch (usrInput) {
			case 1:
				cl.printListItem(cl.getCommonFromList(L1, L2));
				break;
			case 2:
				cl.printListItem(cl.getDiffFromL1(L1, L2));
				break;
			case 3:
				cl.printListItem(cl.getDiffFromL2(L1, L2));
				break;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> readFile(String filePath) throws FileNotFoundException {
		ArrayList<String> L1 = new ArrayList<String>();

		ClassLoader.getSystemClassLoader();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePath);
		InputStreamReader streamReader;
		try {
			streamReader = new InputStreamReader(inputStream, "UTF-8");

			BufferedReader in = new BufferedReader(streamReader);

			for (String line; (line = in.readLine()) != null;) {
				// do something with the line
				String temp = line;
				System.out.println("   >>>  " + temp + "   <<<");
				L1.add(temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return L1;
	}

	private int takeUserInput(String str) {
		int userInput = -1;
		// create a scanner so we can read the command-line input
		Scanner scanner = new Scanner(System.in);
		System.out.println("#######################################################");
		System.out.println("#       Please provide User Input                     #");
		if (str.trim().equalsIgnoreCase("COMPARE")) {
			System.out.println(COMPARE_TYPE1);
			System.out.println(COMPARE_TYPE2);
			System.out.println(COMPARE_TYPE3);
		}
		if (str.trim().equalsIgnoreCase("SHOW")) {
			System.out.println(SHOW_TYPE1);
			System.out.println(SHOW_TYPE2);
			System.out.println(SHOW_TYPE3);
		}
		System.out.println("#######################################################");

		// get the age as an int
		int seq = scanner.nextInt();
		boolean flag = true;

		do {

			if (seq > 0 && seq < 4) {
				userInput = seq;
				flag = false;
				break;
			} else {
				System.out.println("#   Invalid Input Please provide User Input >>        #");
				seq = scanner.nextInt();
			}
		} while (flag);
		return userInput;
	}

	private List<String> takeUserInputList() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("#######################################################");
		System.out.println("#   Please provide List with comma Separate User Input Ex a,b #");
		String str = scanner.nextLine();
		String[] strTemp = str.split(",");
		List<String> l2 = new ArrayList<String>(Arrays.asList(str.split(",")));
		System.out.println("#######################################################");

		return l2;
	}

	private void comparePredefinedList(compareList cl) {
		ArrayList<String> L1 = new ArrayList<String>();
		L1.add("abc");
		L1.add("bcd");
		L1.add("pdf");

		String[] str = new String[] { "abb", "bcd", "ppp" };
		ArrayList<String> L2 = new ArrayList<String>(Arrays.asList(str));

		int usrInput = cl.takeUserInput("show");
		switch (usrInput) {
		case 1:
			cl.printListItem(cl.getCommonFromList(L1, L2));
			break;
		case 2:
			cl.printListItem(cl.getDiffFromL1(L1, L2));
			break;
		case 3:
			cl.printListItem(cl.getDiffFromL2(L1, L2));
			break;
		}

	}

	private List<String> getCommonFromList(List<String> l1, List<String> l2) {
		List<String> result = new ArrayList<String>();

		// For Each Loop for iterating ArrayList
		for (String i : l1) {
			boolean tempFlag = l2.contains(i);
			if (tempFlag)
				result.add(i);
		}
		return result;

	}

	private List<String> getDiffFromL1(List<String> l1, List<String> l2) {
		List<String> result = new ArrayList<String>();

		// For Each Loop for iterating ArrayList
		for (String i : l1) {
			boolean tempFlag = l2.contains(i);
			if (!tempFlag)
				result.add(i);
		}
		return result;

	}

	private List<String> getDiffFromL2(List<String> l1, List<String> l2) {
		List<String> result = new ArrayList<String>();

		// For Each Loop for iterating ArrayList
		for (String i : l2) {
			boolean tempFlag = l1.contains(i);
			if (!tempFlag)
				result.add(i);
		}
		return result;

	}

	private void printListItem(List<String> result) {
		int counter = 1;
		System.out.println("==========================================================");
		System.out.println("=======Total Diff Observed ============" + result.size());
		System.out.println("** **		**		**	**		**		**		**		**");
		// For Each Loop for iterating ArrayList
		for (String i : result) {

			System.out.print(i);
			System.out.print("\t");

			if (counter % 3 == 0)
				System.out.println("");
			counter++;
		}
		System.out.println("");
		System.out.println("===========================Finish=========================");
		System.out.println("==========================================================");
	}

}
