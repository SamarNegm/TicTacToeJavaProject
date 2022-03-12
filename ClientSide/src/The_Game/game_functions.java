/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package The_Game;

import java.util.Random;
import javafx.scene.control.Button;

/**
 *
 * @author Mohamed Hamdy
 */
public class game_functions {
    
  public static Random  rand = new Random();
  public static int difficulty = -1 ;

    public static String check(Button[][] a)
    {
        
        int i;
        for(i=0;i<3;i++)
        {
            if(a[i][0].getText().equals(a[i][1].getText()) && a[i][1].getText().equals(a[i][2].getText()) && !a[i][2].getText().equals(""))
                return(a[i][0].getText());
            else if(a[0][i].getText().equals(a[1][i].getText()) && a[1][i].getText().equals(a[2][i].getText()) && !a[2][i].getText().equals(""))
                return(a[0][i].getText());
        }
        if((a[0][0].getText().equals(a[1][1].getText()) && a[1][1].getText().equals(a[2][2].getText())) || (a[0][2].getText().equals(a[1][1].getText()) && a[1][1].getText().equals(a[2][0].getText())) && !a[1][1].getText().equals(""))
            return(a[1][1].getText());
        return("");
    }
    
    public static void fillO(Button btn[][])
    {
       if (difficulty==2){
           
       
    	int a, b, xc = 0, oc = 0, oCount = 0;
    	for(a = 0; a < 3; a++)
    		for(b = 0; b < 3; b++)
    			if(btn[a][b].getText().equals("O"))
    				oCount++;
   	if(oCount < 4 ) //&& lastWin == 'X') || (oCount < 5 && lastWin == 'O')
    	{
    		for(a = 0; a < 3; a++)
	    	{
	    		xc = oc = 0;
	    		for(b = 0; b < 3; b++)
	    			if(btn[a][b].getText().equals("O"))
	    				oc++;
	    			else if(btn[a][b].getText().equals("X"))
	    				xc++;
	    		if(oc==2)
	    			break;
	    	}
	    	if(oc==2 && xc==0)
	    	{
	    		for(b = 0; b <3; b++)
	    			if(btn[a][b].getText().equals(""))
	    			{
	    				btn[a][b].setText("O");
	    				btn[a][b].setId("O");
	    			}
	    	}
	    	else
	    	{
		    	for(a = 0; a < 3; a++)
		    	{
		    		xc = oc = 0;
		    		for(b = 0; b < 3; b++)
		    			if(btn[b][a].getText().equals("O"))
		    				oc++;
		    			else if(btn[b][a].getText().equals("X"))
		    				xc++;
		    		if(oc==2)
		    			break;
		    	}
		    	if(oc==2 && xc==0)
		    	{
		    		for(b = 0; b <3; b++)
		    			if(btn[b][a].getText().equals(""))
		    			{
		    				btn[b][a].setText("O");
		    				btn[b][a].setId("O");
		    			}
		    	}
		    	else
		    	{
		    		xc = oc = 0;
		    		for(a = 0; a < 3; a++)
		    			if(btn[a][a].getText().equals("O"))
		    				oc++;
		    			else if(btn[a][a].getText().equals("X"))
		    				xc++;
		    		if(oc==2 && xc==0)
		    		{
		    			for(a = 0; a < 3; a++)
		    				if(btn[a][a].getText().equals(""))
		    				{
		    					btn[a][a].setText("O");
		    					btn[a][a].setId("O");
		    				}
		    		}
		    		else
		    		{
		    			xc = oc = 0;
			    		for(a = 0; a < 3; a++)
			    			if(btn[a][2-a].getText().equals("O"))
			    				oc++;
			    			else if(btn[a][2-a].getText().equals("X"))
			    				xc++;
			    		if(oc==2 && xc==0)
			    		{
			    			for(a = 0; a < 3; a++)
			    				if(btn[a][2-a].getText().equals(""))
			    				{
			    					btn[a][2-a].setText("O");
			    					btn[a][2-a].setId("O");
			    				}
			    		}
			    		else
			        	{
			    			for(a = 0; a < 3; a++)
			    	    	{
			    	    		xc = oc = 0;
			    	    		for(b = 0; b < 3; b++)
			    	    			if(btn[a][b].getText().equals("O"))
			    	    				oc++;
			    	    			else if(btn[a][b].getText().equals("X"))
			    	    				xc++;
			    	    		if(xc==2)
			    	    			break;
			    	    	}
			    	    	if(xc==2 && oc==0)
			    	    	{
			    	    		for(b = 0; b <3; b++)
			    	    			if(btn[a][b].getText().equals(""))
			    	    			{
			    	    				btn[a][b].setText("O");
			    	    				btn[a][b].setId("O");
			    	    			}
			    	    	}
			    	    	else
			    	    	{
			    		    	for(a = 0; a < 3; a++)
			    		    	{
			    		    		xc = oc = 0;
			    		    		for(b = 0; b < 3; b++)
			    		    			if(btn[b][a].getText().equals("O"))
			    		    				oc++;
			    		    			else if(btn[b][a].getText().equals("X"))
			    		    				xc++;
			    		    		if(xc==2)
			    		    			break;
			    		    	}
			    		    	if(xc==2 && oc==0)
			    		    	{
			    		    		for(b = 0; b <3; b++)
			    		    			if(btn[b][a].getText().equals(""))
			    		    			{
			    		    				btn[b][a].setText("O");
			    		    				btn[b][a].setId("O");
			    		    			}
			    		    	}
			    		    	else
			    		    	{
			    		    		xc = oc = 0;
			    		    		for(a = 0; a < 3; a++)
			    		    			if(btn[a][a].getText().equals("O"))
			    		    				oc++;
			    		    			else if(btn[a][a].getText().equals("X"))
			    		    				xc++;
			    		    		if(xc==2 && oc==0)
			    		    		{
			    		    			for(a = 0; a < 3; a++)
			    		    				if(btn[a][a].getText().equals(""))
			    		    				{
			    		    					btn[a][a].setText("O");
			    		    					btn[a][a].setId("O");
			    		    				}
			    		    		}
			    		    		else
			    		    		{
			    		    			xc = oc = 0;
			    			    		for(a = 0; a < 3; a++)
			    			    			if(btn[a][2-a].getText().equals("O"))
			    			    				oc++;
			    			    			else if(btn[a][2-a].getText().equals("X"))
			    			    				xc++;
			    			    		if(xc==2 && oc==0)
			    			    		{
			    			    			for(a = 0; a < 3; a++)
			    			    				if(btn[a][2-a].getText().equals(""))
			    			    				{
			    			    					btn[a][2-a].setText("O");
			    			    					btn[a][2-a].setId("O");
			    			    				}
			    			    		}
			    			    		else
			    			        	{
			    			        		do
			    			        		{
			    			        			a = rand.nextInt(3);
			    			        			b = rand.nextInt(3);
			    			        		}while(btn[a][b].getText().equals("X") || btn[a][b].getText().equals("O"));
			    			        		btn[a][b].setText("O");
			    			        		btn[a][b].setId("O");
			    			        	}
			    		    		}
			    		    	}
			    	    	}
			        	}
		    		}
		    	}
	    	}
    	}
    }
       else {
           int a , b ; 
           
        do
        {
                a = rand.nextInt(3);
                b = rand.nextInt(3);
        }while(btn[a][b].getText().equals("X") || btn[a][b].getText().equals("O"));
        btn[a][b].setText("O");
        btn[a][b].setId("O");

       }
    
//    public void set_difficulty(int _diff){
//        difficulty = _diff ; 
//    }
    }
}
