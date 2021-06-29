import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.*;
public class SnakeGame implements KeyListener
{
    int p,q;
    boolean targetHit = false;
    int x = 15,y = 17;
    JTextField t;
    String s;
    char status = 'R';
    JButton[][] b;
    int score = 0;
    public void main()throws Exception
    {
        score = 0;
        x = 15;y = 17;
        b = new JButton[30][30];
        JFrame f = new JFrame("Game");
        JPanel pp = new JPanel();
        s = "15$14 15$15 15$16 15$17 ";
        f.setLayout(null);
        t = new JTextField();
        int x = 0,y = 0;
        f.add(pp);
        pp.setLayout(new GridLayout(30,30));
       
        f.setLocation(350,50);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
        p1.add(t);
        f.add(p1);
        pp.setBounds(0,0,550,550);
        p1.setBounds(550,0,80,600);
        pp.setBorder(BorderFactory.createLineBorder(Color.black,2));
        f.setVisible(true);f.setSize(600,600);
        for(int i = 0;i<30;i++)
        {
            for(int j = 0;j<30;j++)
            {
                b[i][j] = new JButton();
                pp.add(b[i][j]);
                b[i][j].setBackground(Color.black);
                b[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
            }
        }
       
        t.addKeyListener(this);
        p = (int)(Math.random()*29);
        q = (int)(Math.random()*29);
        b[p][q].setBackground(Color.yellow);
        
        TimeUnit.SECONDS.sleep(5);
        move2();
    }
    public void keyPressed(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
        int a = e.getKeyCode();
        switch(a)
        {
            case 39:status = (status=='L')?'L':'R';
            break;
            case 38:status = (status=='D')?'D':'U';
            break;
            case 40:status = (status=='U')?'U':'D';
            break;
            case 37:status = (status=='R')?'R':'L';
            break;
               
        }
    }
    public void keyTyped(KeyEvent e)
    {
    }  
    public void move2()
    {
        try
        {
            while(true)
            {
                b[x][y].setBackground(Color.blue);
                switch(status)
                {
                    case 'R':
                        y = (y==29)?0:y+1;
                    break;
                    case 'U':
                        x = (x==0)?29:x-1;
                    break;
                    case 'D':
                        x = (x==29)?0:x+1;
                    break;
                    case 'L':
                        y = (y==0)?29:y-1;
                    break;
                   
                }
                               
                if(target())
                {
                    score+=10;
                    t.setText(""+score);
                    s = s+x+"$"+y+" ";
                    while(s.contains(p+"$"+q))
                    {
                        p = (int)(Math.random()*29);
                        q = (int)(Math.random()*29);
                    }
                    b[p][q].setBackground(Color.yellow);
                }
                else
                {
                    erase();
                    s = s.substring(s.indexOf(' ')+1)+x+"$"+y+" ";
                }
                move();
                if((s.trim().contains(" "+x+"$"+y+" ")))
                {
                    gameOver();
                    break;
                }
                b[x][y].setBackground(Color.red);
                TimeUnit.MILLISECONDS.sleep(40);
            }
        }
        catch(Exception e)
        {
            gameOver();
        }
    }
    public void check()
    {
    }
    public boolean target()
    {
        if(x==p&&y==q)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
       
    public void move()
    {
        String m = s.substring(0,s.length()-1);
        String k = m.substring(m.lastIndexOf(' ')+1,m.length());
        int x = Integer.parseInt(k.substring(0,k.indexOf('$')));
        int y = Integer.parseInt(k.substring(k.indexOf('$')+1));
        b[x][y].setBackground(Color.blue);
    }
    public void erase()
    {
        String m = s.substring(0,s.indexOf(' '));
        int x = Integer.parseInt(m.substring(0,m.indexOf('$')));
        int y = Integer.parseInt(m.substring(m.indexOf('$')+1));
        b[x][y].setBackground(Color.black);
    }
    public void gameOver()
    {
            Frame newFrame = new JFrame("Game Over");
            JPanel newPanel = new JPanel();newFrame.add(newPanel);
            JLabel l1 = new JLabel();
            JLabel l2 = new JLabel();
            l2.setText("GAME OVER");newPanel.add(l2);
            l1.setText("Score = "+score);newPanel.add(l1);
            newFrame.setVisible(true);
            newFrame.setLocation(600,300);
            newFrame.setSize(100,105);
            JButton b2 = new JButton("  Exit  ");
            newPanel.add(b2);
            b2.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
            newFrame.setResizable(false);
    }
}         