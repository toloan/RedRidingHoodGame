package game.Userface;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import game.Object.MainChar;

public class Memory {
	public MainChar getMain(){
		MainChar main;
		List<String> fileArray = null;
		Path file=Paths.get("src/sprite/memory.txt");
		try {
			 fileArray=(List<String>) Files.readAllLines(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		main=new MainChar(0, 0);
		main.setSpeed(Integer.parseInt(fileArray.get(1)));
		main.setCoin(Integer.parseInt(fileArray.get(2)));
		main.setMaxHP(Integer.parseInt(fileArray.get(3)));
	return main;		
	}
	public int getCurrentLevel(){
		List<String> fileArray = null;
		Path file=Paths.get("src/sprite/memory.txt");
		try {
			 fileArray=(List<String>) Files.readAllLines(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Integer.parseInt(fileArray.get(0));
		
	}
	public void save(int currentLevel,MainChar main){
		Path file=Paths.get("src/sprite/memory.txt");
		List<String> input = new ArrayList<String>();
		input.add(Integer.toString(currentLevel));
		input.add(Integer.toString(main.getSpeed()));
		input.add(Integer.toString(main.getCoin()));
		input.add(Integer.toString(main.getMaxHP()));
		try {
			Files.write(file,input,StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
