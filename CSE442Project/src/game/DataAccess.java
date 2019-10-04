package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataAccess {
	// File variables
	private FileReader fileReader;
	private FileWriter fileWriter;
	private String FilePath;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	// User Data Variables
	private Profile[] Profiles;

	public DataAccess() {

		// Used to open a new Data Storing File to that will be used to Store Program
		// Data

		FilePath = "data/ProgramData.txt";

		// Defines fileReader
		try {
			// Locates Previously Declared Data Storing File
			fileReader = new FileReader(FilePath);
		} catch (FileNotFoundException ex1) {
			// Creates New Data Storing File
			try {
				File file = new File(FilePath);
				try {
					file.createNewFile();
				} catch (IOException ex3) {
					ex3.printStackTrace();
				}
				fileReader = new FileReader(FilePath);

				// Formats
				try {
					// Locates Previously Declared Data Storing File
					fileWriter = new FileWriter(FilePath);
				} catch (IOException ex4) {
					ex4.printStackTrace();
				}

				bufferedWriter = new BufferedWriter(fileWriter);
				try {
					bufferedWriter
							.write("DO NOT EDIT THIS FILE, AS DOING SO WILL CORRUPT THE PROGRAM DATA!!! -Team BadFish");
					bufferedWriter.newLine();
					bufferedWriter.newLine();
					bufferedWriter.write(">>Start - Player Profiles<<");
					bufferedWriter.newLine();
					bufferedWriter.write(">>End<<");
					bufferedWriter.newLine();
					bufferedWriter.close();
				} catch (IOException ex5) {
					ex5.printStackTrace();
				}

			} catch (FileNotFoundException ex2) {
				ex2.printStackTrace();
			}
		}

		Profiles = new Profile[3];

		// Read file and output data

		try {
			// Start Data File Read
			bufferedReader = new BufferedReader(fileReader);
			String CurrentFileLine = bufferedReader.readLine();
			while (CurrentFileLine != null) {
				if (CurrentFileLine.contains(">>Start - Player Profiles<<")) {
					CurrentFileLine = bufferedReader.readLine();
					int count = 0;
					while (CurrentFileLine.contains(">>Start - User<<")) {
						String[] temp = new String[7];
						CurrentFileLine = bufferedReader.readLine();
						boolean hitEnd = false;
						if (!CurrentFileLine.contains(">>End - User<<")) {
							// Profile Input 1
							temp[0] = CurrentFileLine;
							CurrentFileLine = bufferedReader.readLine();
						}
						if (!CurrentFileLine.contains(">>End - User<<")) {
							// Profile Input 2
							temp[1] = CurrentFileLine;
							CurrentFileLine = bufferedReader.readLine();
						}
						if (!CurrentFileLine.contains(">>End - User<<")) {
							// Profile Input 3
							temp[2] = CurrentFileLine;
							CurrentFileLine = bufferedReader.readLine();
						}
						if (!CurrentFileLine.contains(">>End - User<<")) {
							// Profile Input 4
							temp[3] = CurrentFileLine;
							CurrentFileLine = bufferedReader.readLine();
						}
						if (!CurrentFileLine.contains(">>End - User<<")) {
							// Profile Input 5
							temp[4] = CurrentFileLine;
							CurrentFileLine = bufferedReader.readLine();
						}
						if (!CurrentFileLine.contains(">>End - User<<")) {
							// Profile Input 6
							temp[5] = CurrentFileLine;
							CurrentFileLine = bufferedReader.readLine();
						}
						if (!CurrentFileLine.contains(">>End - User<<")) {
							// Profile Input 7
							temp[6] = CurrentFileLine;
							hitEnd = true;
							CurrentFileLine = bufferedReader.readLine();
						}
						if (hitEnd) {
							Profiles[count] = new Profile(temp[0], temp[1], temp[2], Integer.parseInt(temp[3]),
									Boolean.parseBoolean(temp[4]), Boolean.parseBoolean(temp[5]),
									Boolean.parseBoolean(temp[6]));
						}
						count++;
						CurrentFileLine = bufferedReader.readLine();
					}
				}
				CurrentFileLine = bufferedReader.readLine();
			}

			// End Data File Read

		} catch (IOException ex1) {
			ex1.printStackTrace();
		}

	}

	public void saveData() {

		try {
			// Locates Previously Declared Data Storing File
			fileWriter = new FileWriter(FilePath);
		} catch (IOException ex4) {
			ex4.printStackTrace();
		}

		// Writes over previous data
		bufferedWriter = new BufferedWriter(fileWriter);
		try {
			bufferedWriter.write("DO NOT EDIT THIS FILE, AS DOING SO WILL CORRUPT THE PROGRAM DATA!!! -Team BadFish");
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			bufferedWriter.write(">>Start - Player Profiles<<");
			if (Profiles[0] != null) {
				bufferedWriter.newLine();
			}
			for (int i = 0; Profiles[i] != null; i++) {
				bufferedWriter.write(">>Start - User<<");
				bufferedWriter.newLine();
				bufferedWriter.write(Profiles[i].getFirstName());
				bufferedWriter.newLine();
				bufferedWriter.write(Profiles[i].getLastName());
				bufferedWriter.newLine();
				bufferedWriter.write(Profiles[i].getNickName());
				bufferedWriter.newLine();
				bufferedWriter.write(Integer.toString(Profiles[i].getAge()));
				bufferedWriter.newLine();
				if (Profiles[i].getGameStatus(1)) {
					bufferedWriter.write("true");
				} else {
					bufferedWriter.write("false");
				}
				bufferedWriter.newLine();
				if (Profiles[i].getGameStatus(2)) {
					bufferedWriter.write("true");
				} else {
					bufferedWriter.write("false");
				}
				bufferedWriter.newLine();
				if (Profiles[i].getGameStatus(3)) {
					bufferedWriter.write("true");
				} else {
					bufferedWriter.write("false");
				}
				bufferedWriter.newLine();
				bufferedWriter.write(">>End - User<<");
				bufferedWriter.newLine();
				if (i == 2) {
					break;
				}
			}
			bufferedWriter.write(">>End<<");
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException ex5) {
			ex5.printStackTrace();
		}
	}

	public Profile getProfile(int profileNumber) {
		if (profileNumber > -1 && profileNumber < 3) {
			return Profiles[profileNumber];
		} else {
			return null;
		}
	}

	public boolean setProfile(int profileNumber, String firstName, String lastName, String nickName, int age,
			boolean game1, boolean game2, boolean game3) {
		if (profileNumber > -1 && profileNumber < 3) {
			Profiles[profileNumber] = new Profile(firstName, lastName, nickName, age, game1, game2, game3);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateGame(int profileNumber, int gameNumber, boolean TF) {
		if (profileNumber > -1 && profileNumber < 3) {
			return Profiles[profileNumber].setGameStatus(gameNumber, TF);
		} else {
			return false;
		}
	}

}
