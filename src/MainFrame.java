import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MainFrame extends JFrame implements ActionListener, Runnable, KeyListener, MouseListener
{
	JComboBox difficulty;
	JPanel img,currentGame,inst;
	JLabel difficultyLabel,difficultyDisplay,mines,flags,title,nameL,timeImg,timeTxt,instTitle;
	JButton start,quit,leaderBoard,exit,reStart,backToMenu,info,instOk;
	Font f = new Font("Arial", Font.PLAIN,36);
	Font g = new Font("Arial", Font.PLAIN,50);
	Font f1 = new Font("Arial", Font.BOLD,32);
	Font b = new Font("Arial", Font.BOLD,24);
	Font t = new Font("Arial", Font.PLAIN,100);
	JTextField uName;
	JTextArea instBody;
	Thread th;
	int HH=0,Min=0,SS=0;
    int timeCnt = 0;
    ImageIcon clock;
    
	File lb = new File("C:\\Mine Sweeper\\Leaderboards");
	File d;
	
	Border Blackline = BorderFactory.createLineBorder(Color.black,4);
	Border Blackline1 = BorderFactory.createLineBorder(Color.black,6);
	Border blockBordergreen = BorderFactory.createLineBorder(Color.black, 2);
	Levels ll=new Levels();
	LeaderBoardDisplay lbd = new LeaderBoardDisplay();
	
	JLabel bg1;

	void createWindow() throws IOException
	{
		this.setSize(1253,720);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		this.setBounds(-10,0,5600,5000);
//		this.setPreferredSize(new Dimension(5000,5000));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
//		this.getContentPane().setBackground(Color.ORANGE);
		bg1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("grassG.jpg")));
		bg1.setBounds(0,0,1300,800);
		bg1.addMouseListener(this);	
		
		this.setContentPane(bg1);
		this.setTitle("Mine Sweeper");
		
		initialize();
		
		lb.mkdirs();
		d = new File(lb + "\\Easy");
		d.mkdirs();
		d = new File(lb + "\\Medium");
		d.mkdirs();
		d = new File(lb + "\\Hard");
		d.mkdirs();
		
		this.setVisible(true);
	}
	
	void name()
	{
		nameL = new JLabel("Nickname : ");
		nameL.setBounds(150,50,250,50);
		nameL.setForeground(Color.white);
		nameL.setFont(f);
		
		uName = new JTextField();
		uName.setFont(f);
		uName.setBounds(380,50,300,50);
		uName.addKeyListener(this);
		uName.setBorder(blockBordergreen);
		uName.addMouseListener(this);
	}
	
	void labels()
	{
		title = new JLabel("Mine Sweeper");
		title.setBounds(326,5,1100,100);
		title.setFont(t);
		title.setForeground(Color.black);
		
		difficultyLabel = new JLabel("Difficulty   :");
		difficultyLabel.setBounds(150,140,250,50);
		difficultyLabel.setFont(f);
		difficultyLabel.setForeground(Color.white);

		
		difficultyDisplay = new JLabel();
		difficultyDisplay.setBounds(20,30,400,50);
		difficultyDisplay.setFont(f1);
		difficultyDisplay.setForeground(Color.blue);
		difficultyDisplay.setVisible(false);
		
		mines = new JLabel();
		mines.setBounds(20,110,300,50);
		mines.setFont(f1);
		mines.setForeground(Color.BLUE);
		mines.setVisible(false);
		
		flags = new JLabel();
		flags.setBounds(20,190,300,50);
		flags.setFont(f1);
		flags.setForeground(Color.BLUE);
		flags.setVisible(false);
	}
	
	void time() throws IOException
	{
		
		clock = new ImageIcon((getClass().getClassLoader().getResource("clock.jpg")));
//		clock.setImage(ImageIO.read(getClass().getClassLoader().getResource("clock.png")));
		
		
		timeImg = new JLabel(clock);
		timeImg.setBounds(20,350,50,50);
		timeImg.setFont(f1);
		timeImg.setOpaque(true);
		timeImg.setBackground(Color.white);
		timeImg.setForeground(Color.BLUE);
		timeImg.setVisible(false);
		
		timeTxt = new JLabel("Flag");
		timeTxt.setBounds(80,350,170,50);
		timeTxt.setFont(f1);
		timeTxt.setOpaque(true);
		timeTxt.setBorder(Blackline);
//		timeTxt.setBackground(Color.white);
//		timeTxt.setIcon(new ImageIcon(getClass().getClassLoader().getResource("clock.png")));
		timeTxt.setForeground(Color.BLUE);
		timeTxt.setVisible(false);
		
	}
	
	public void run()
	{
		timeCnt=1;
		
		
		while(timeCnt==1)
		{
			timeTxt.setText(String.format(" %02d:%02d:%02d", HH, Min,SS));
			
			try { Thread.sleep(1000); }
			catch(Exception e) { System.out.println(e.getMessage()); } 
			SS++;
			if(SS==60)
			{
				Min++;
				SS=0;
			}
			if(Min==60)
			{
				HH++;
				Min=0;
			}
			System.out.println(("run"));
		}
	}
	void button()
	{
		start = new JButton("Start Game");
		start.setBounds(260,220,310,55);
		start.setFont(f);
		start.setFocusable(false);
		start.addActionListener(this);
		start.setBorder(blockBordergreen);
		start.addMouseListener(this);
		
		leaderBoard = new JButton("Leaderboards");
		leaderBoard.setBounds(260,380,310,55);
		leaderBoard.setFont(f);
		leaderBoard.setFocusable(false);
		leaderBoard.addActionListener(this);
		leaderBoard.setBorder(blockBordergreen);
		leaderBoard.addMouseListener(this);
		
		exit = new JButton("Exit Game");
		exit.setBounds(260,460,310,55);
		exit.setFont(f);
		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setBorder(blockBordergreen);
		exit.addMouseListener(this);
		
		quit = new JButton("Quit");
		quit.setBounds(20,600,250,60);
		quit.setFont(f);
		quit.setFocusable(false);
		quit.addActionListener(this);
		quit.setVisible(false);
		quit.setBorder(blockBordergreen);
		quit.addMouseListener(this);
		
		reStart = new JButton("Restart");
		reStart.setBounds(20,520,250,60);
		reStart.setFont(f);
		reStart.setFocusable(false);
		reStart.addActionListener(this);
		reStart.setVisible(false);
		reStart.setBorder(blockBordergreen);
		reStart.addMouseListener(this);
		
		backToMenu = new JButton("Back");
		backToMenu.setBounds(15,15,100,50);
		backToMenu.setFont(b);
		backToMenu.setFocusable(false);
		backToMenu.addActionListener(this);
		backToMenu.setBorder(blockBordergreen);
		backToMenu.addMouseListener(this);
		
		info = new JButton("Game rules");
		info.setBounds(260,300,310,55);
		info.setFont(f);
		info.setFocusable(false);
		info.addActionListener(this);
		info.setBorder(blockBordergreen);
		info.addMouseListener(this);
		
		instOk = new JButton("OK");
		instOk.setBounds(350,460,100,50);
		instOk.setFont(b);
		instOk.setFocusable(false);
		instOk.addActionListener(this);
		instOk.setBorder(blockBordergreen);
		instOk.addMouseListener(this);
	}
	
	void instPanel()
	{
		instTitle = new JLabel("Game rules");
		instTitle.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		instTitle.setBounds(0,0,800,100);
		instTitle.setFont(g);
		instTitle.setForeground(Color.white);

		instBody = new JTextArea(" 1. 'Left click' on any block to explore it. \n 2. The number on the explored block represents\n     the number of mines adjacent to that block."
				+ "\n 3. 'Right click' on the block to flag or unflag that\n     block.\n 4. You win when all the blocks containing\n     mines are flagged and rest all are explored.\n"
				+ " 5.  You lose if you explore a mine.");
		instBody.setBounds(0,100,800,350);
		instBody.setFont(f);
		instBody.setEditable(false);
		instBody.setFocusable(false);
		instBody.setOpaque(false);
		instBody.setForeground(Color.white);
		instBody.setBackground(Color.cyan);
	}
	
	void difficultyLvl()
	{
		String diff[] = {"Easy","Medium","Hard"};
		difficulty = new JComboBox(diff);
		difficulty.setBounds(380,130,300,50);
		difficulty.setFont(f);
		difficulty.setFocusable(false);
		difficulty.setBorder(blockBordergreen);
		difficulty.addMouseListener(this);
		difficulty.addActionListener(this);
	}
	
	void gridArea()
	{
		img = new JPanel();
		img.setBounds(226,120,810,550);
		img.setBorder(Blackline1);
//		img.setBackground(Color.lightGray);
		img.setBackground(new Color(0,0,0,60));
		img.addMouseListener(this);
		
		img.setVisible(true);
		img.setLayout(null);
		
		inst = new JPanel();
		inst.setBounds(226,120,810,550);
		inst.setBorder(Blackline1);
		inst.setBackground(new Color(0,0,0,60));
		inst.setVisible(false);
		inst.setLayout(null);
		
		currentGame = new JPanel();
		currentGame.setVisible(false);

	}
	void initialize() throws IOException
	{
		difficultyLvl();
		name();
		gridArea();
		labels();
		button();
		time();
		instPanel();

		this.add(img);
		this.add(inst);
		img.add(nameL);	img.add(uName);	img.add(difficultyLabel);	img.add(difficulty);	img.add(start);
		img.add(leaderBoard);	img.add(exit); img.add(info);	
		
		inst.add(instOk);	inst.add(instTitle); inst.add(instBody);

		this.add(timeImg);		this.add(timeTxt);
		this.add(title);
		this.add(difficultyDisplay);
		this.add(mines);
		this.add(flags);
		this.add(quit);
		this.add(reStart);
	}
	
	void quitAction()
	{
		bg1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("grassG.jpg")));

		quit.setVisible(false);
		reStart.setVisible(false);
		
		difficultyDisplay.setVisible(false);
		mines.setVisible(false);
		flags.setVisible(false);
		currentGame.setVisible(false);
		
		
		timeImg.setVisible(false);
		timeTxt.setVisible(false);
		timeCnt=0;
		
		title.setVisible(true);
		img.setVisible(true);
		
		this.setVisible(true);
	}
	
	
	public static void main(String args[]) throws IOException
	{
		MainFrame mf = new MainFrame();
		mf.createWindow();
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource().equals(difficulty))
		{
			img.setBackground(new Color(0,0,0,60));
			bg1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("grassG.jpg")));
//			this.setContentPane(bg1);
			this.setVisible(true);
			System.out.println("in");
		}
		
		if(ae.getSource().equals(start))
		{
//			bg1.setVisible(false);
//			this.getContentPane().setBackground(Color.orange);
			
///			this.setContentPane(bg2);
			bg1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org.jpg")));
			this.setVisible(true);
			
			String nm = uName.getText();
			if(nm.equals(""))
			{
				bg1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("grassG.jpg")));
				JOptionPane.showMessageDialog(null,"Please enter your nickname!","Missing data",JOptionPane.WARNING_MESSAGE);

			}
			else
			{
				
			title.setVisible(false);
			quit.setVisible(true);
			reStart.setVisible(true);
			img.setVisible(false);
			
			
			timeImg.setVisible(true);
			timeTxt.setVisible(true);
			th = new Thread(this);
			HH=0;Min=0;SS=0;
			th.start();
			
			this.remove(currentGame);
//			this.remove(el.shoveEasy);
//			this.remove(ml.shoveMedium);
//			
//			this.remove(el.flagEasy);
			
			
			String difficultyLevel = difficulty.getSelectedItem().toString();
			if(difficultyLevel.equals("Easy"))
			{
				difficultyDisplay.setVisible(true);
				flags.setVisible(true);
				mines.setVisible(true);
				
				img.setVisible(false);
				
				difficultyDisplay.setText("Difficulty      : Easy");
				mines.setText("Total Mines  : 10");
				flags.setText("Flags Left     : 10");
				
				ll = new Levels();					 
				ll.initialize(8,11,10, this);
				
				
				currentGame = ll.playArea;
				
				this.add(currentGame);
				currentGame.setBorder(Blackline);
				currentGame.setVisible(true);
				
			}
			else if(difficultyLevel.equals("Medium"))
			{
				difficultyDisplay.setVisible(true);
				flags.setVisible(true);
				mines.setVisible(true);
				
				img.setVisible(false);
				
				difficultyDisplay.setText("Difficulty      : Medium");
				mines.setText("Total Mines  : 30");
				flags.setText("Flags Left     : 30");
				
				ll = new Levels();					 
				ll.initialize(13,17,30, this);
				
				currentGame = ll.playArea;
				
				this.add(currentGame);
				currentGame.setBorder(Blackline);
				currentGame.setVisible(true);
			}
			else if(difficultyLevel.equals("Hard"))
			{
				difficultyDisplay.setVisible(true);
				flags.setVisible(true);
				mines.setVisible(true);
				
				img.setVisible(false);
				
				difficultyDisplay.setText("Difficulty      : Hard");
				mines.setText("Total Mines  : 50");
				flags.setText("Flags Left     : 50");
				
				ll = new Levels();					 
				ll.initialize(16,21,50, this);
				
				currentGame = ll.playArea;
				
				this.add(currentGame);
				currentGame.setBorder(Blackline);
				currentGame.setVisible(true);
			}
			}
		}
		if(ae.getSource().equals(exit))
		{
			this.dispose();
//			lbd.initialize(this);
		}
		if(ae.getSource().equals(leaderBoard))
		{
//			this.setVisible(false);
	
			lbd.initialize(this);
//			lbd.setVisible(true);
//			lbd.titleP.add(backToMenu);
//			try
//			{
//			Thread.sleep(50);
//			}
//			catch(Exception e) {}
			this.dispose();
//			title.setVisible(false);
//			img.setVisible(false);
		
			
		}
		
		if(ae.getSource().equals(info))
		{
			img.setVisible(false);
			inst.setVisible(true);
		}
		if(ae.getSource().equals(instOk))
		{
			img.setVisible(true);
			inst.setVisible(false);
		}
		if(ae.getSource().equals(quit))
		{
			timeCnt=0;
			int result=JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?\nYour progress will be lost!", "Confirm Quit", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION)
			{
				quitAction();

			}
			else
			{
				th=new Thread(this);
				th.start();
			}
		}
		if(ae.getSource().equals(reStart))
		{
				
				timeCnt=0;
				int result=JOptionPane.showConfirmDialog(null, "Are you sure you want to re-start the game?\nYour progress will be lost!", "Confirm Re-start", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION)
				{
					
				currentGame.setVisible(false);
				
				this.remove(currentGame);
				
				ll = new Levels();					 
				
				if(difficultyDisplay.getText().equals("Difficulty      : Easy"))
				{
					ll.initialize(8,11,10,this);
					flags.setText("Flags Left     : 10");
				}
				else if(difficultyDisplay.getText().equals("Difficulty      : Medium"))
				{
					ll.initialize(13,17,30,this);
					flags.setText("Flags Left     : 30");

				}
				else if(difficultyDisplay.getText().equals("Difficulty      : Hard"))
				{
					ll.initialize(16,21,50,this);			
					flags.setText("Flags Left     : 50");

				}
				
				
				currentGame = ll.playArea;
				
				this.add(currentGame);
				currentGame.setBorder(Blackline);
				currentGame.setVisible(true);
				
//				timeCnt=0;
				HH=0;	Min=0;	SS=0;
				th=new Thread(this);
				th.start();
//				timeCnt=1;
				}
				else
				{
					th=new Thread(this);
					th.start();
				}
			}
			
		}

	@Override
	public void keyTyped(KeyEvent e) {
		
		String name = uName.getText();
		if(name.length()>14)
		{
			uName.setText(name.substring(0,14));
		}
		if(e.getKeyChar()=='\n')
		{
			bg1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org.jpg")));
			this.setVisible(true);
			String nm = uName.getText();
			if(nm.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter your nickname!","Missing data",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				
			title.setVisible(false);
			quit.setVisible(true);
			reStart.setVisible(true);
			img.setVisible(false);
			
			
			timeImg.setVisible(true);
			timeTxt.setVisible(true);
			th = new Thread(this);
			HH=0;Min=0;SS=0;
			th.start();
			
			this.remove(currentGame);
//			this.remove(el.shoveEasy);
//			this.remove(ml.shoveMedium);
//			
//			this.remove(el.flagEasy);
			
			
			
			String difficultyLevel = difficulty.getSelectedItem().toString();
			if(difficultyLevel.equals("Easy"))
			{
				difficultyDisplay.setVisible(true);
				flags.setVisible(true);
				mines.setVisible(true);
				
				img.setVisible(false);
				
				difficultyDisplay.setText("Difficulty      : Easy");
				mines.setText("Total Mines  : 10");
				flags.setText("Flags Left     : 10");
				
				ll = new Levels();					 
				ll.initialize(8,11,10, this);
				
				
				currentGame = ll.playArea;
				
				this.add(currentGame);
				currentGame.setBorder(Blackline);
				currentGame.setVisible(true);
				
			}
			else if(difficultyLevel.equals("Medium"))
			{
				difficultyDisplay.setVisible(true);
				flags.setVisible(true);
				mines.setVisible(true);
				
				img.setVisible(false);
				
				difficultyDisplay.setText("Difficulty      : Medium");
				mines.setText("Total Mines  : 30");
				flags.setText("Flags Left     : 30");
				
				ll = new Levels();					 
				ll.initialize(12,16,30, this);
				
				
				currentGame = ll.playArea;
				
				this.add(currentGame);
				currentGame.setBorder(Blackline);
				currentGame.setVisible(true);
			}
			else if(difficultyLevel.equals("Hard"))
			{
				difficultyDisplay.setVisible(true);
				flags.setVisible(true);
				mines.setVisible(true);
				
				img.setVisible(false);
				
				difficultyDisplay.setText("Difficulty      : Hard");
				mines.setText("Total Mines  : 50");
				flags.setText("Flags Left     : 50");
				
				ll = new Levels();					 
				ll.initialize(16,20,50, this);
				
				
				currentGame = ll.playArea;
				
				this.add(currentGame);
				currentGame.setBorder(Blackline);
				currentGame.setVisible(true);
			}
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(img) || e.getSource().equals(difficulty) || e.getSource().equals(bg1) || e.getSource().equals(start)
				|| e.getSource().equals(info) || e.getSource().equals(leaderBoard) || e.getSource().equals(exit))
		{
			img.setBackground(new Color(0,0,0,60));
			bg1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("grassG.jpg")));
			this.setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(start))
		{
			start.setBorder(Blackline);
		}
		else if(e.getSource().equals(leaderBoard))
		{
			leaderBoard.setBorder(Blackline);

		}
		else if(e.getSource().equals(exit))
		{
			exit.setBorder(Blackline);

		}
		else if(e.getSource().equals(reStart))
		{
			reStart.setBorder(Blackline);

		}
		else if(e.getSource().equals(quit))
		{
			quit.setBorder(Blackline);

		}
		else if(e.getSource().equals(uName))
		{
			uName.setBorder(Blackline);
		}
		else if(e.getSource().equals(difficulty))
		{
			difficulty.setBorder(Blackline);
		}
		else if(e.getSource().equals(backToMenu))
		{
			backToMenu.setBorder(Blackline);

		}
		else if(e.getSource().equals(info))
		{
			info.setBorder(Blackline);

		}
		else if(e.getSource().equals(instOk))
		{
			instOk.setBorder(Blackline);

		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(start))
		{
			start.setBorder(blockBordergreen);
		}
		else if(e.getSource().equals(leaderBoard))
		{
			leaderBoard.setBorder(blockBordergreen);

		}
		else if(e.getSource().equals(exit))
		{
			exit.setBorder(blockBordergreen);

		}
		else if(e.getSource().equals(reStart))
		{
			reStart.setBorder(blockBordergreen);

		}
		else if(e.getSource().equals(quit))
		{
			quit.setBorder(blockBordergreen);

		}
		else if(e.getSource().equals(uName))
		{
			uName.setBorder(blockBordergreen);
		}
		else if(e.getSource().equals(difficulty))
		{
			difficulty.setBorder(blockBordergreen);
		}
		else if(e.getSource().equals(backToMenu))
		{
			backToMenu.setBorder(blockBordergreen);

		}
		else if(e.getSource().equals(info))
		{
			info.setBorder(blockBordergreen);

		}
		else if(e.getSource().equals(instOk))
		{
			instOk.setBorder(blockBordergreen);

		}
		
	}

	
}

class Levels implements ActionListener, MouseListener
{
	int tapCount=0,adjMines=0;
	JLabel blocks[][];
	JPanel playArea;
	int selectedBlockR=-1,selectedBlockC=-1;
	Border Blackline = BorderFactory.createLineBorder(Color.black,4);
	Border defaultBorder;
	Border blockBordergreen = BorderFactory.createLineBorder(Color.black, 2);
	Border blockBorderGray = BorderFactory.createLineBorder(Color.lightGray, 2);
	Border blockBorderStart = BorderFactory.createLineBorder(Color.black, 1);
	Color blockColor = Color.green.darker();
	
	Font f = new Font("Arial", Font.BOLD,30);
	
	File ms = new File("C:\\Mine Sweeper");
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
	int n1,n2,m,flags;
	int[] rows,columns;

	MainFrame mf;
	LeaderBoardDisplay lbd;
	
	void initialize(int n1, int n2,int m, MainFrame mf)
	{
		System.out.println(""+Color.orange.getRed()+", "+Color.orange.getGreen()+", "+Color.orange.getBlue());
		defaultBorder = blockBorderStart;
		this.mf = mf;
		this.n1=n1;
		this.n2=n2;
		this.m=m;
		flags=m;
		mineBlocks();
		gridArea();
		paintMines(); 
		

	}
	void mineBlocks()
	{
		blocks = new JLabel[n1][n2];
		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<n2;j++)
			{
			blocks[i][j] = new JLabel("",JLabel.CENTER);
			blocks[i][j].setBackground(blockColor);
			blocks[i][j].setOpaque(true);
			blocks[i][j].setFocusable(false);
//			blocks[i][j].setAlignmentX(CENTER_ALIGNMENT);
			blocks[i][j].setName("");
//			blocks[i][j].addActionListener(this);
			blocks[i][j].addMouseListener(this);
			}
		}
	}
	void gridArea()
	{
		playArea = new JPanel();
		playArea.setBounds(360,10,870,665);
		playArea.setBorder(Blackline);
		playArea.setBackground(Color.black);
		playArea.setLayout(new GridLayout(n1,n2));
		
		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<n2;j++)
			{
				playArea.add(blocks[i][j]);
			}
		}
	}
	void generateMine(int touchX,int touchY)
	{
		rows = new int[m];
		columns = new int[m]; 
		
		for(int i=0;i<m;)
		{
			boolean repeat=false;
			int x = (int)(Math.random()*n1);
			int y = (int)(Math.random()*n2);
			System.out.println(""+x+","+y);
			
			if((x==touchX && y==touchY) || (x==touchX-1 && y==touchY-1) || (x==touchX-1 && y==touchY) || (x==touchX-1 && y==touchY+1) ||
				(x==touchX && y==touchY-1) || (x==touchX && y==touchY+1) || (x==touchX+1 && y==touchY-1) || (x==touchX+1 && y==touchY) || 
				(x==touchX+1 && y==touchY+1))
			{
				continue;
			}
			
			for(int j=0;j<=i-1;j++)
			{
//				System.out.println(""+n);
				if(rows[j]==x && columns[j]==y)
				{
					repeat=true;
					break;
				}
			}
			
			if(repeat==false)
			{
				rows[i]=x;
				columns[i]=y;
				i++;
			}
		}
		paintMines();
	}
	
	void paintMines()
	{
		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<n2;j++)
			{
			blocks[i][j].setBackground(blockColor);
//			blocks[i][j].setSelectedIcon(new ImageIcon("res\\selected.png"));
			blocks[i][j].setFont(f);
			blocks[i][j].setBorder(blockBorderStart);
//			blocks[i][j].setForeground(Color.red);
			}
		}
		
		if(tapCount>0)
		{
			for(int i=0;i<m;i++)
			{
//				blocks[rows[i]][columns[i]].setText("M");
				blocks[rows[i]][columns[i]].setName("M");
//				blocks[rows[i]][columns[i]].setBackground(Color.red);
			}
		}
	}
	
	void discoverBlocks(int touchX,int touchY)
	{
		adjMines = 0;
//		blocks[touchX][touchY].setEnabled(false);
//		blocks[touchX][touchY].setSelected(false);
		blocks[touchX][touchY].setIcon(null);
		blocks[touchX][touchY].setBackground(Color.lightGray);
//		blocks[touchX][touchY].setBorder(blockBorderGra);
		
		for(int i=touchX-1;i<=touchX+1;i++)
		{
			for(int j=touchY-1;j<=touchY+1;j++)
			{
				if((i>=0 && i<n1) && (j>=0 && j<n2))
				{
					if(blocks[i][j].getName().equals("M"))
					{
						adjMines++;
					}
					
				}
			}
		}
		for(int i=touchX-1;i<=touchX+1;i++)
		{
			for(int j=touchY-1;j<=touchY+1;j++)
			{
				if((i>=0 && i<n1) && (j>=0 && j<n2))
				{
					if(blocks[i][j].getBackground()==Color.blue && adjMines==0)
					{
						blocks[i][j].setIcon(null);
						flags++;
						mf.flags.setText("Flags Left     : "+flags);
						blocks[i][j].setBackground(Color.LIGHT_GRAY);
					}
					
				}
			}
		}
		
		Color c[] = {Color.green.darker(),Color.blue,Color.magenta.darker(),Color.red.darker(),Color.DARK_GRAY,Color.black,Color.black,Color.black};
		if(adjMines!=0)
		{
			blocks[touchX][touchY].setForeground(c[adjMines-1]);
			blocks[touchX][touchY].setText(""+adjMines);
		}
		if(adjMines==0)
		{
			for(int i=touchX-1;i<=touchX+1;i++)
			{
				for(int j=touchY-1;j<=touchY+1;j++)
				{
					if((i>=0 && i<n1) && (j>=0 && j<n2))
					{
						if(blocks[i][j].getBackground()!=Color.LIGHT_GRAY)
						{
						discoverBlocks(i, j);
						}
					}
				}
			}
		}
	}
	
	void gameWon()
	{
		boolean Win=true;
		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<n2;j++)
			{
				if(blocks[i][j].getName().equals("M"))
				{
					if(blocks[i][j].getBackground()!=Color.blue)
					{
						Win=false;
					}
				}
				else
				{
					if(blocks[i][j].getBackground()!=Color.LIGHT_GRAY)
					{
						Win=false;
					}
				}
			}
		}
		
		if(Win==true)
		{
			mf.timeCnt=0;
			JOptionPane.showMessageDialog(null, "Congratulations! You completed the game in time : "+String.format("%02d:%02d:%02d", mf.HH, mf.Min,mf.SS),"Game won!",JOptionPane.INFORMATION_MESSAGE);
			updateLeaderBoards();
			mf.quitAction();
		}
		

	}
	
	void updateLeaderBoards()
	{
		String newRecordTime="",oldRecordTime="";
		int records,rank=1;
		int newSec=0,oldSec=0;
		System.out.println("h");
		File lb = new File(ms + "\\Leaderboards");
		lb.mkdirs();
		
		File d = new File(lb + "\\Easy");
		String[] dList;
		if(mf.difficultyDisplay.getText().equals("Difficulty      : Easy"))
		{			
			d = new File(lb + "\\Easy");
		}
		else if(mf.difficultyDisplay.getText().equals("Difficulty      : Medium"))
		{
			d = new File(lb + "\\Medium");
		}
		else if(mf.difficultyDisplay.getText().equals("Difficulty      : Hard"))
		{
			d = new File(lb + "\\Hard");
		}
		
		d.mkdirs();
		dList = d.list();
		records = dList.length;
//		File newRecord = new File(d + "\\temp");
//		newRecord.mkdirs();
		try
		{
		newRecordTime = mf.timeTxt.getText();
		newSec = Integer.parseInt(newRecordTime.substring(1,3))*3600 + Integer.parseInt(newRecordTime.substring(4,6))*60 + Integer.parseInt(newRecordTime.substring(7,9));
		br.close();
		fr.close();
		}
		catch(Exception e) { }
		
		for(int i=1;i<=d.list().length;i++)
		{
			File timeRead = new File(d+"\\"+i+"\\time.txt");
			try
			{
			fr=new FileReader(timeRead);
			br=new BufferedReader(fr);
			oldRecordTime = br.readLine();
			oldSec = Integer.parseInt(oldRecordTime.substring(1,3))*3600 + Integer.parseInt(oldRecordTime.substring(4,6))*60 + Integer.parseInt(oldRecordTime.substring(7,9));
			br.close();		fr.close();
			}
			catch(Exception e ) { }
			if(newSec>oldSec)
			{
				rank++;
			}
		}
		
		for(int i=d.list().length;i>=rank;i--)
		{
			File r = new File(d + "\\"+i);
			File r1 = new File(d+ "\\" + (i+1));
			r.renameTo(r1);
		}
		File nr = new File(d + "\\" + rank);
		nr.mkdir();
		File name = new File(nr + "\\name.txt");
		File time = new File(nr + "\\time.txt");
		try
		{
		fw = new FileWriter(name);
		fw.write(""+mf.uName.getText());
		fw.close();
		
		fw = new FileWriter(time);
		fw.write(""+mf.timeTxt.getText());
		fw.close();
		}
		catch(Exception e) { } 
		
		File del = new File(d + "\\11");
		if(del.exists())
		{
			for(File subfile : del.listFiles())
			{
				subfile.delete();
			}
			del.delete();
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent ae1) {
//		for(int k=0;k<n1;k++)
//		{
//			for(int j=0;j<n2;j++)
//			{	
//				if(ae1.getSource().equals(blocks[k][j]))
//					blocks[k][j].setSelected(false);
//			}
//		}
			
	}
	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		for(int k=0;k<n1;k++)
		{
			for(int j=0;j<n2;j++)
			{
				if(blocks[k][j].getBorder()==Blackline)
				{
//					blocks[k][j].setSelected(false);   ---->>
					selectedBlockR=k;
					selectedBlockC=j;
					if(me.getButton()==MouseEvent.BUTTON1)
					{

						if(blocks[selectedBlockR][selectedBlockC].getBackground()==Color.blue)
						{
							blocks[selectedBlockR][selectedBlockC].setBorder(Blackline);
							JOptionPane.showMessageDialog(null,"Cannot explore the block that is flagged!", "Illegal move!",JOptionPane.WARNING_MESSAGE);
//							blocks[selectedBlockR][selectedBlockC].setSelected(false);
//							blocks[selectedBlockR][selectedBlockC].setVisible(true);
							
						}
						else
						{
						tapCount++;
						if(tapCount==1)
						{
							generateMine(selectedBlockR,selectedBlockC);
						}
						
						if(blocks[selectedBlockR][selectedBlockC].getName().equals("M"))
						{
							for(int i=0;i<m;i++)
							{
//								blocks[rows[i]][columns[i]].setText("M");
//								blocks[rows[i]][columns[i]].setName("M");
//								blocks[rows[i]][columns[i]].setBackground(Color.red);
								if(mf.difficultyDisplay.getText().equals("Difficulty      : Easy"))
									blocks[rows[i]][columns[i]].setIcon(new ImageIcon(getClass().getClassLoader().getResource("mine_easy.jpg")));
								else if(mf.difficultyDisplay.getText().equals("Difficulty      : Medium"))
									blocks[rows[i]][columns[i]].setIcon(new ImageIcon(getClass().getClassLoader().getResource("mine_medium.jpg")));
								else
									blocks[rows[i]][columns[i]].setIcon(new ImageIcon(getClass().getClassLoader().getResource("mine_hard.jpg")));


							}
							mf.timeCnt=0;
							JOptionPane.showMessageDialog(null,"Oops! You encountered a mine!", "Game lost!",JOptionPane.ERROR_MESSAGE);
							mf.quitAction();
						}
						else
						{
						discoverBlocks(selectedBlockR,selectedBlockC);
						}
						
						blocks[selectedBlockR][selectedBlockC].setBorder(defaultBorder);
						gameWon();
						}
					}
					else if(me.getButton()==MouseEvent.BUTTON3)
					{
						if(tapCount==0)
						{
							JOptionPane.showMessageDialog(null,"Cannot place flags before exploring any block!", "Illegal move!",JOptionPane.WARNING_MESSAGE);
						}
						else
						{

						if(blocks[selectedBlockR][selectedBlockC].getBackground()!=Color.blue)
						{
							if(flags==0)
							{
								JOptionPane.showMessageDialog(null,"No flags left!", "Out of flags!",JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								blocks[selectedBlockR][selectedBlockC].setBackground(Color.blue);
								if(mf.difficultyDisplay.getText().equals("Difficulty      : Easy"))
									blocks[selectedBlockR][selectedBlockC].setIcon(new ImageIcon(getClass().getClassLoader().getResource("flagEasy.jpg")));
								else if(mf.difficultyDisplay.getText().equals("Difficulty      : Medium"))
									blocks[selectedBlockR][selectedBlockC].setIcon(new ImageIcon(getClass().getClassLoader().getResource("flagMedium.jpg")));
								else
									blocks[selectedBlockR][selectedBlockC].setIcon(new ImageIcon(getClass().getClassLoader().getResource("flagHard.jpg")));
								
								blocks[selectedBlockR][selectedBlockC].setBackground(Color.blue);
//								blocks[selectedBlockR][selectedBlockC].setSelected(true); ------->>>
								flags--;
							}
						}
						else if(blocks[selectedBlockR][selectedBlockC].getBackground()==Color.blue)
						{
							blocks[selectedBlockR][selectedBlockC].setBackground(blockColor);
							blocks[selectedBlockR][selectedBlockC].setIcon(new ImageIcon(getClass().getClassLoader().getResource("selected.png")));
							flags++;
//							flagEasy.setText("Flag");
						}
						
						mf.flags.setText("Flags Left     : "+flags);

						
						blocks[selectedBlockR][selectedBlockC].setBorder(Blackline);
						gameWon();
						}
					}
					
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent me) {
		
		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<n2;j++)
			{
				if(me.getSource().equals(blocks[i][j]))
				{
					if(blocks[i][j].getBackground()!=Color.LIGHT_GRAY)
					{
						
						blocks[i][j].setBorder(Blackline);
					}
				}
			}
		}
		
		
		
	}
	@Override
	public void mouseExited(MouseEvent me) {

		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<n2;j++)
			{
				if(me.getSource().equals(blocks[i][j]))
				{
					blocks[i][j].setBorder(defaultBorder);
				}
			}
		}
		
		
	}
}

class LeaderBoardDisplay extends JFrame implements ActionListener,MouseListener
{
	JPanel titleP,left,right,cen;
	JLabel LeaderBoardL,easy,medium,hard;
	JButton backToMenu;
	JTextArea leftRank,cenRank,rightRank, leftName,cenName,rightName, leftTime,cenTime,rightTime;
	MainFrame mf;
	Font t = new Font("Arial", Font.BOLD,50);
	Font d = new Font("Arial", Font.BOLD,27);
	Font d1 = new Font("Arial", Font.BOLD,22);
	Font b = new Font("Arial", Font.BOLD,24);
	File lb = new File("C:\\Mine Sweeper\\Leaderboards");
	Border Blackline = BorderFactory.createLineBorder(Color.black,6);
	Border temp = BorderFactory.createLineBorder(Color.black,1);
	Border blockBordergreen = BorderFactory.createLineBorder(Color.black, 2);
	Border Blackline1 = BorderFactory.createLineBorder(Color.black,4);

	
	FileReader fr;
	BufferedReader br;
	
	void initialize(MainFrame mf)
	{
		this.setSize(1253,720);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		this.setBounds(-10,0,5600,5000);
//		this.setPreferredSize(new Dimension(5000,5000));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
				
		titlePanel();
		mainPanels();
		textArea();
		button();
		generateLeaderboard();
		
		this.add(titleP);
		this.add(left);	this.add(cen);	this.add(right);
		titleP.add(backToMenu);
		

		this.setVisible(true);
	}
	
	void button()
	{
		backToMenu = new JButton("Back");
		backToMenu.setBounds(15,15,100,50);
		backToMenu.setFont(b);
		backToMenu.setFocusable(false);
		backToMenu.addActionListener(this);
		backToMenu.setBorder(blockBordergreen);
		backToMenu.addMouseListener(this);
	}
	void titlePanel()
	{
		titleP = new JPanel();
		titleP.setBounds(-5,0,1305, 80);
		titleP.setLayout(null);
		titleP.setBackground(Color.gray);
		titleP.setBorder(Blackline);
		
		LeaderBoardL = new JLabel("Leaderboards");
		LeaderBoardL.setBounds(475,5,800,70);
		LeaderBoardL.setFont(t);
		LeaderBoardL.setForeground(Color.white);
		titleP.add(LeaderBoardL);
		 
	}
	void mainPanels()
	{
		left = new JPanel();
		left.setBounds(0,74,416,1200);
//		left.setBackground(Color.lightGray);
		left.setBorder(Blackline);
		left.setLayout(null);
		
		cen = new JPanel();
		cen.setBounds(412,74,416,1200);
//		cen.setBackground(Color.lightGray);
		cen.setBorder(Blackline);
		cen.setLayout(null);
		
		right = new JPanel();
		right.setBounds(824,74,416,1200);
//		right.setBackground(Color.lightGray);
		right.setBorder(Blackline);
		right.setLayout(null);
		
		easy = new JLabel("                Difficuty : Easy");
		easy.setBounds(0,-5,416,50);
		easy.setFont(d);
		easy.setBackground(Color.lightGray);
		easy.setBorder(Blackline);
		
		medium = new JLabel("              Difficuty : Medium");
		medium.setBounds(0,-5,416,50);
		medium.setFont(d);
		medium.setBackground(Color.lightGray);
		medium.setBorder(Blackline);
		
		hard = new JLabel("                Difficuty : Hard");
		hard.setBounds(0,-5,416,50);
		hard.setFont(d);
		hard.setBackground(Color.lightGray);
		hard.setBorder(Blackline);
	
		
		left.add(easy);		cen.add(medium);	right.add(hard);
	}
	
	void textArea()
	{
		leftRank = new JTextArea();
		leftRank.setBounds(5,44,75,1200);
		leftRank.setBackground(Color.lightGray);
		leftRank.setFont(d1);
		leftRank.setBorder(temp);
		leftRank.setEditable(false);
		leftRank.setFocusable(false);
		
		cenRank = new JTextArea();
		cenRank.setBounds(5,44,75,1200);
		cenRank.setBackground(Color.lightGray);
		cenRank.setFont(d1);
		cenRank.setBorder(temp);		
		cenRank.setEditable(false);
		cenRank.setFocusable(false);

		rightRank = new JTextArea();
		rightRank.setBounds(5,44,75,1200);
		rightRank.setBackground(Color.lightGray);
		rightRank.setFont(d1);
		rightRank.setBorder(temp);
		rightRank.setEditable(false);
		rightRank.setFocusable(false);

		
		leftName = new JTextArea();
		leftName.setBounds(80,44,220,1200);
		leftName.setBackground(Color.lightGray);
		leftName.setFont(d1);
		leftName.setBorder(temp);
		leftName.setEditable(false);
		leftName.setFocusable(false);

		cenName = new JTextArea();
		cenName.setBounds(80,44,220,1200);
		cenName.setBackground(Color.lightGray);
		cenName.setFont(d1);
		cenName.setBorder(temp);		
		cenName.setEditable(false);
		cenName.setFocusable(false);

		rightName = new JTextArea();
		rightName.setBounds(80,44,220,1200);
		rightName.setBackground(Color.lightGray);
		rightName.setFont(d1);
		rightName.setBorder(temp);
		rightName.setEditable(false);
		rightName.setFocusable(false);

		leftTime = new JTextArea();
		leftTime.setBounds(300,44,112,1200);
		leftTime.setBackground(Color.lightGray);
		leftTime.setFont(d1);
		leftTime.setBorder(temp);
		leftTime.setEditable(false);
		leftTime.setFocusable(false);

		cenTime = new JTextArea();
		cenTime.setBounds(300,44,112,1200);
		cenTime.setBackground(Color.lightGray);
		cenTime.setFont(d1);
		cenTime.setBorder(temp);		
		cenTime.setEditable(false);
		cenTime.setFocusable(false);

		rightTime = new JTextArea();
		rightTime.setBounds(300,44,112,1200);
		rightTime.setBackground(Color.lightGray);
		rightTime.setFont(d1);
		rightTime.setBorder(temp);
		rightTime.setEditable(false);
		rightTime.setFocusable(false);

		left.add(leftRank);	cen.add(cenRank); 	right.add(rightRank);
		left.add(leftName);	cen.add(cenName); 	right.add(rightName);
		left.add(leftTime);	cen.add(cenTime); 	right.add(rightTime);

		
	}
	
	void generateLeaderboard()
	{
		lb.mkdirs();
		
		String Rank = String.format("%5s\n\n", "Rank");
		String Name = String.format("%17s\n\n", "Nickname");
		String Time = String.format("%7s\n\n", "Time");
		
		File easy = new File(lb + "\\Easy");
		easy.mkdir();
		for(int i=1;i<=easy.list().length;i++)
		{
			File nm = new File(easy + "\\"+i+"\\name.txt");
			File tm = new File(easy + "\\"+i+"\\time.txt");
			try
			{
				Rank+=String.format("%5d",i)+"\n\n";
				
				fr = new FileReader(nm);
				br = new BufferedReader(fr);
				Name+=" "+br.readLine()+"\n\n";
				br.close();
				fr.close();
				
				fr = new FileReader(tm);
				br = new BufferedReader(fr);
				Time+=br.readLine()+"\n\n";
				br.close();
				fr.close();
			}
			catch(Exception e) { }
		}
		
		leftRank.setText(Rank);
		leftName.setText(Name);
		leftTime.setText(Time);
		
		
		Rank = String.format("%5s\n\n", "Rank");
		Name = String.format("%17s\n\n", "Nickname");
		Time = String.format("%7s\n\n", "Time");
		File medium = new File(lb + "\\Medium");
		medium.mkdir();
		for(int i=1;i<=medium.list().length;i++)
		{
			File nm = new File(medium + "\\"+i+"\\name.txt");
			File tm = new File(medium + "\\"+i+"\\time.txt");
			try
			{
				Rank+=String.format("%5d",i)+"\n\n";
				
				fr = new FileReader(nm);
				br = new BufferedReader(fr);
				Name+=" "+br.readLine()+"\n\n";
				br.close();
				fr.close();
				
				fr = new FileReader(tm);
				br = new BufferedReader(fr);
				Time+=br.readLine()+"\n\n";
				br.close();
				fr.close();
			}
			catch(Exception e) { }
		}
		
		cenRank.setText(Rank);
		cenName.setText(Name);
		cenTime.setText(Time);
		
		Rank = String.format("%5s\n\n", "Rank");
		Name = String.format("%17s\n\n", "Nickname");
		Time = String.format("%7s\n\n", "Time");
		
		File hard = new File(lb + "\\Hard");
		hard.mkdir();
		for(int i=1;i<=hard.list().length;i++)
		{
			File nm = new File(hard + "\\"+i+"\\name.txt");
			File tm = new File(hard + "\\"+i+"\\time.txt");
			try
			{
				Rank+=String.format("%5d",i)+"\n\n";
				
				fr = new FileReader(nm);
				br = new BufferedReader(fr);
				Name+= " "+br.readLine()+"\n\n";
				br.close();
				fr.close();
				
				fr = new FileReader(tm);
				br = new BufferedReader(fr);
				Time+=br.readLine()+"\n\n";
				br.close();
				fr.close();
			}
			catch(Exception e) { }
		}
		
		
		rightRank.setText(Rank);
		rightName.setText(Name);
		rightTime.setText(Time);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(backToMenu))
		{
			backToMenu.setBorder(Blackline1);

		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(backToMenu))
		{
			backToMenu.setBorder(blockBordergreen);

		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if(ae.getSource().equals(backToMenu))
		{
//			title.setVisible(true);
//			img.setVisible(true);
			try
			{
				mf=new MainFrame();
				mf.createWindow();
			}
			catch(Exception e) { }
			
			this.dispose();
//			lbd.titleP.setVisible(false);
//			lbd.left.setVisible(false);
//			lbd.cen.setVisible(false);
//			lbd.right.setVisible(false);
			
		}
	}
	
}
