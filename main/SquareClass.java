package main;

import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class SquareClass extends JButton{
	public static boolean gameBegin = false;
	
	public boolean mineFlag = false;
	public boolean revealedFlag;
	public boolean revealableFlag;
	public boolean flagFlag;
	
	public int[] location = new int[2];
	
	private static String filePath = "";
		
	public ImageIcon flagIMG = new ImageIcon(new ImageIcon(filePath+"flag.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	public ImageIcon unclickedIMG = new ImageIcon(new ImageIcon(filePath+"unclicked.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	public ImageIcon mineIMG = new ImageIcon(new ImageIcon(filePath+"mine.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	public ImageIcon redMineIMG = new ImageIcon(new ImageIcon(filePath+"redMine.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	public ImageIcon wrongFlagIMG = new ImageIcon(new ImageIcon(filePath+"wrongFlag.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	
		public ImageIcon emptyIMG = new ImageIcon(new ImageIcon(filePath+"empty.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon oneIMG = new ImageIcon(new ImageIcon(filePath+"1tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon twoIMG = new ImageIcon(new ImageIcon(filePath+"2tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon threeIMG = new ImageIcon(new ImageIcon(filePath+"3tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon fourIMG = new ImageIcon(new ImageIcon(filePath+"4tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon fiveIMG = new ImageIcon(new ImageIcon(filePath+"5tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon sixIMG = new ImageIcon(new ImageIcon(filePath+"6tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon sevenIMG = new ImageIcon(new ImageIcon(filePath+"7tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		public ImageIcon eightIMG = new ImageIcon(new ImageIcon(filePath+"8tile.jpg").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	
	public ImageIcon[] imageList = {emptyIMG, oneIMG, twoIMG, threeIMG, fourIMG, fiveIMG, sixIMG, sevenIMG, eightIMG};
	
	
	SquareClass(int i, int j) {
		
		location[0] = i;
		location[1] = j;
		
		revealedFlag = false;
		flagFlag = false;
		revealableFlag = true;
		
		setIcon(unclickedIMG);	
	}



}
