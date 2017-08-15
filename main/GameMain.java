package main;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.*;

/*
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
*/
import java.util.concurrent.ExecutionException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class GameMain extends JFrame 
{
	
		private static String filePath = "./";
		
		int mineCount, rows, columns;
		int flagCount;
		SquareClass rowList[][];
		
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem beginner, intermediate, expert;
		JMenuItem menuItem;
		
		public Timer clock;
		
		int seconds;
		
		int windowWidth, windowHeight;
		
		int revealedCount;
		
		JPanel fieldPanel, statusPanel;
	
		int difficulty;
		
		boolean overFlag; 
		
		final JButton smileButton;
		JLabel clockLabel, hunSecs, tenSecs, oneSecs;
		JLabel mineCountLabel, hunMineCount, tenMineCount, oneMineCount;
	
		public ImageIcon zeroCIMG = new ImageIcon(new ImageIcon(filePath+"zeroC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon oneCIMG = new ImageIcon(new ImageIcon(filePath+"oneC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon twoCIMG = new ImageIcon(new ImageIcon(filePath+"twoC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon threeCIMG = new ImageIcon(new ImageIcon(filePath+"threeC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon fourCIMG = new ImageIcon(new ImageIcon(filePath+"fourC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon fiveCIMG = new ImageIcon(new ImageIcon(filePath+"fiveC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon sixCIMG = new ImageIcon(new ImageIcon(filePath+"sixC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon sevenCIMG = new ImageIcon(new ImageIcon(filePath+"sevenC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon eightCIMG = new ImageIcon(new ImageIcon(filePath+"eightC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));
		public ImageIcon nineCIMG = new ImageIcon(new ImageIcon(filePath+"nineC.jpg").getImage().getScaledInstance(37,65,Image.SCALE_DEFAULT));

		public ImageIcon[] counterList = { zeroCIMG, oneCIMG, twoCIMG, threeCIMG, fourCIMG, fiveCIMG, sixCIMG, sevenCIMG, eightCIMG, nineCIMG };	
	
	
		public ImageIcon regSmileIMG = new ImageIcon(new ImageIcon(filePath+"smileReset.jpg").getImage().getScaledInstance(65,65,Image.SCALE_DEFAULT));
		public ImageIcon loseSmileyIMG = new ImageIcon(new ImageIcon(filePath+"loseSmiley.jpg").getImage().getScaledInstance(65,65,Image.SCALE_DEFAULT));
		public ImageIcon OHsmileyIMG = new ImageIcon(new ImageIcon(filePath+"OHsmiley.jpg").getImage().getScaledInstance(65,65,Image.SCALE_DEFAULT));
		public ImageIcon smileWinIMG = new ImageIcon(new ImageIcon(filePath+"smileWin.jpg").getImage().getScaledInstance(65,65,Image.SCALE_DEFAULT));
		
	public GameMain()
	{
		setLayout(new BorderLayout());
		
		difficulty = 1;
		
		overFlag = false;
		
		setDifficulty();
		
		flagCount = mineCount;
		
		fieldPanel = new JPanel(new GridLayout(rows,columns,0,0));
		statusPanel = new JPanel(new BorderLayout());
		//statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.X_AXIS));
		
		seconds = 0;
		
		clockLabel = new JLabel();
		clockLabel.setLayout(new BoxLayout(clockLabel,BoxLayout.X_AXIS));
		clockLabel.setPreferredSize(new Dimension(111,65));
		
		hunSecs = new JLabel();
		tenSecs = new JLabel();
		oneSecs = new JLabel();
		
		setTimer();


		clock = new Timer(1000, 
				new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						seconds++;
						setTimer();
					}
		}
				);
		
		
		
		// Used to resize text size
		//menuLabel.setFont(menuLabel.getFont().deriveFont(100f));
		mineCountLabel = new JLabel();
		mineCountLabel.setLayout(new BoxLayout(mineCountLabel,BoxLayout.X_AXIS));
		mineCountLabel.setPreferredSize(new Dimension(111,65));
		
		hunMineCount = new JLabel();
		tenMineCount = new JLabel();
		oneMineCount = new JLabel();

		setMineCounter();

		
		rowList = new SquareClass[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				rowList[i][j] = new SquareClass(i,j);				
				fieldPanel.add(rowList[i][j]);
				rowList[i][j].addMouseListener(new SquareListener(rowList[i][j]));
			}			
		}
		
				
		smileButton = new JButton();
		smileButton.setPreferredSize(new Dimension(65,65));
		smileButton.setIcon(regSmileIMG);
		smileButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resetGame();						
					}
				}				
			);
		
		menuBar = new JMenuBar();
		menu = new JMenu("Difficulty");
		beginner = new JMenuItem("Beginner");
		beginner.addActionListener(
				new changeDifficulty(1)				
				);
		intermediate = new JMenuItem("Intermediate");
		intermediate.addActionListener(
				new changeDifficulty(2)				
				);
		expert = new JMenuItem("Expert");
		expert.addActionListener(
				new changeDifficulty(3)				
				);
		
		menu.add(beginner);
		menu.add(intermediate);
		menu.add(expert);
		
		menuBar.add(menu);
		
		statusPanel.add(clockLabel,BorderLayout.EAST);
		statusPanel.add(mineCountLabel, BorderLayout.WEST);
		statusPanel.add(smileButton, BorderLayout.CENTER);
		fieldPanel.setPreferredSize(new Dimension(columns*50,rows*50));
		add(statusPanel, BorderLayout.NORTH);
		add(fieldPanel, BorderLayout.SOUTH);
		
		windowWidth = columns * 50;
		windowHeight = (rows * 50) + 200;
		
		setJMenuBar(menuBar);
		
		setSize(windowWidth,windowHeight);
		setVisible(true);	
		
		}
	
	public void resetGame() {
		SquareClass.gameBegin = false;
		overFlag = false;
		
		clock.stop();
		
		for (int i = 0; i < rowList.length; i++) {
			for (int j = 0; j < rowList[i].length; j++) {
				fieldPanel.remove(rowList[i][j]);
			}
		}
		
		rowList = new SquareClass[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				rowList[i][j] = new SquareClass(i,j);				
				fieldPanel.add(rowList[i][j]);
				rowList[i][j].addMouseListener(new SquareListener(rowList[i][j]));
			}			
		}
		fieldPanel.setPreferredSize(new Dimension(columns*50,rows*50));
		fieldPanel.setLayout(new GridLayout(rows,columns,0,0));
		fieldPanel.invalidate();
		fieldPanel.validate();
		fieldPanel.repaint();
		
		seconds = 0;
		setTimer();
		
		flagCount = mineCount;
		setMineCounter();
		
		revealedCount = (rows * columns) - mineCount;
		
		smileButton.setIcon(regSmileIMG);
		
		windowWidth = columns * 50;
		windowHeight = (rows * 50) + 200;
		
		setSize(windowWidth,windowHeight);
		
	}
	
	public void setMineCounter() {
		hunMineCount.setIcon(counterList[flagCount / 100]);
			mineCountLabel.add(hunMineCount);
		tenMineCount.setIcon(counterList[(flagCount % 100) / 10]);
			mineCountLabel.add(tenMineCount);
		oneMineCount.setIcon(counterList[(flagCount % 10)]);
			mineCountLabel.add(oneMineCount);
	}
	
	public void setDifficulty() {
		if (difficulty == 1) {		
			rows = 9;
			columns = 9;		
			mineCount = 10;
		}
		else if (difficulty == 2) {
			rows = 16;
			columns = 16;		
			mineCount = 40;
		}
		else if (difficulty == 3) {
			rows = 16;
			columns = 30;		
			mineCount = 99;
		}
		revealedCount = (rows * columns) - mineCount;
		flagCount = mineCount;
	}
	
	class changeDifficulty implements ActionListener {
		int difficultyNew; 
		changeDifficulty(int d) {
			difficultyNew = d;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			difficulty = difficultyNew;
			setDifficulty();
			resetGame();
			System.out.println("finished, rows ="+rows+" , columns ="+columns+" , difficulty = "+difficulty);
		}
		
	}
	
	public void setTimer() {
		hunSecs.setIcon(counterList[seconds / 100]);
			clockLabel.add(hunSecs);
		tenSecs.setIcon(counterList[(seconds % 100) / 10]);
			clockLabel.add(tenSecs);
		oneSecs.setIcon(counterList[seconds % 10]);
			clockLabel.add(oneSecs);
	}		
	
	class SquareListener implements MouseListener {
		SquareClass s;
		SquareListener(SquareClass square) {
			s = square;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			if (!overFlag){
				if (e.getButton()==MouseEvent.BUTTON1) {
					if (!SquareClass.gameBegin) {
						SquareClass.gameBegin = true;
						clock.start();
						int minesPlaced = 0;
						
						while (minesPlaced < mineCount) {
							int randRow = (int)(Math.random() * rows);
							int randCol = (int)(Math.random() * columns);
							if (!rowList[randRow][randCol].mineFlag && (randRow != s.location[0] && randCol != s.location[1])) {
								rowList[randRow][randCol].mineFlag = true;
								minesPlaced++;
								System.out.println("Mine created at x = "+Integer.toString(randCol)+", y = "+Integer.toString(randRow));
							}			
						}
					}
					
					if (!s.flagFlag) {
						s.revealedFlag = true;
						if (s.mineFlag) {
							overFlag = true;
							clock.stop();
							for (int i = 0; i < rows; i++){
								for (int j = 0; j < columns; j++) {
									if (rowList[i][j].mineFlag) {
										rowList[i][j].setIcon(rowList[i][j].mineIMG);
									}
									if (!rowList[i][j].mineFlag && rowList[i][j].flagFlag) rowList[i][j].setIcon(rowList[i][j].wrongFlagIMG);
									
								}
							}
							s.setIcon(s.redMineIMG);
							smileButton.setIcon(loseSmileyIMG);
						}
						else {
							checkAdjacent(s);
							System.out.println("Only "+revealedCount+"tiles left to reveal");
							if (revealedCount == 0) {
								overFlag = true;
								smileButton.setIcon(smileWinIMG);
							}
						}
					}
				}
				else if (e.getButton()==MouseEvent.BUTTON3) {
					if (!s.flagFlag) {
						s.flagFlag = true;
						s.setIcon (s.flagIMG);
						s.revealableFlag = false;
						flagCount--; 
						setMineCounter();
						
					}
					else {
						s.flagFlag = false;
						s.setIcon (s.unclickedIMG);
						s.revealableFlag = true;
						flagCount++;
						setMineCounter();
					}
				}
			}
		}
		
		public void checkAdjacent(SquareClass s) {
			int adjacentList[][] = { 
					
				{s.location[0] - 1 ,s.location[1] - 1}, 	{s.location[0]     ,s.location[1] - 1},		{s.location[0] + 1 ,s.location[1] - 1},		
				{s.location[0] - 1 ,s.location[1]    },													{s.location[0] + 1 ,s.location[1]    },
				{s.location[0] - 1 ,s.location[1] + 1},		{s.location[0]     ,s.location[1] + 1},		{s.location[0] + 1 ,s.location[1] + 1}
					
					
				};
			
			System.out.println("Checking: " + s.location[0] + " , " + s.location[1] );
			
			int minesAdjacent = 0;
			
			for (int i = 0; i < adjacentList.length; i++) {
				if (adjacentList[i][0] >= 0 && adjacentList[i][1] >= 0 && adjacentList[i][0] < rows && adjacentList[i][1] < columns) {
					if (rowList[adjacentList[i][0]][adjacentList[i][1]].mineFlag) {
						minesAdjacent++;
					}					
				}
			}
			
			s.revealedFlag = true;
			revealedCount--;
			
			if (minesAdjacent == 0) {
				for (int i = 0; i < adjacentList.length; i++) {
					if (adjacentList[i][0] >= 0 && adjacentList[i][1] >= 0 && adjacentList[i][0] < rows && adjacentList[i][1] < columns) {
						if (!rowList[adjacentList[i][0]][adjacentList[i][1]].revealedFlag && !rowList[adjacentList[i][0]][adjacentList[i][1]].flagFlag) {
							checkAdjacent(rowList[adjacentList[i][0]][adjacentList[i][1]]);
						}
					}
				}
			}

			s.setIcon(s.imageList[minesAdjacent]);
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			smileButton.setIcon(OHsmileyIMG);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if (!overFlag)	smileButton.setIcon(regSmileIMG);
			else smileButton.setIcon(loseSmileyIMG);
			
		}
		
		
		
	}

	
	public static void main(String[] args)
	{
		GameMain application = new GameMain();
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


