import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class bgMat2Json {

	private static String readFile(String fp) throws Exception
	{
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader is = new java.io.InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(is);

		String str;
		String everything = "";    
		while ((str = br.readLine()) != null) {
		    everything += str+"\n";
		}
		        
        br.close();
        is.close();
        fis.close();
	    
        return everything;

	}
	
	private static String usingBufferedReader(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
	
public static void main(String[] args) throws Exception {

	String saveDirRoot = null;
	String inputDir = null;
	String inputFile = null;
	
	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	if (saveDirRoot == null) {
		System.out.print("Enter the saveDir:");
		saveDirRoot = console.readLine();
	}

	if (inputDir == null) {
		System.out.print("Enter inputDir:");
		inputDir = console.readLine();
	}
	if (inputFile == null)
	{
		inputFile = console.readLine();
	}

	bgMat2Json bj = new bgMat2Json();
	buildToJson(saveDirRoot, inputDir, inputFile);
}

public static void buildToJson(String saveDirRoot, String inputDir, String inputFile) throws Exception {
	
	if (saveDirRoot == null)
		saveDirRoot = "c:/dogstar/bgex";
	if (inputDir == null)
		inputDir = "C:\\Users\\DennisMcFarlin\\Documents\\eXtremeGammon\\";
	if (inputFile == null)
		inputFile = "DJM-XG1.txt";
	//String saveDirRoot = "c:/dogstar/bgex";
	//String inputDir = "C:\\Users\\DennisMcFarlin\\Documents\\eXtremeGammon\\";
	//String inputFile = "DJM-XG1.txt";
	
	String PLAYER1NAME = "";
	String PLAYER2NAME = "";
	String GAMEDATE= "";
	String GAMECOMMENT = "";
	String SEQTITLE = "game";
	String GAMENUMBER="";
	String TOTALGAMES="";
	String POSTCOMMENT = "";
	String PLAYER1SCORE= "";
	String PLAYER2SCORE = "";
	String MATCHLENGTH = "";
	String MATCHEVENT = "";
	//==========================

	String PLAYERACTING = "";
	String MOVENUMBER = "";
	String DIE1VALUE = "";
	String DIE2VALUE = "";
	String CHECKERPLAY = "";
	String CUBEVALUE = "";
	String CUBEACTIONRESULT = "";

	String ACTIONARRAY = "";
	// The action array is composed of rollitems and cubeitems
	String ROLLITEM = "	{\"type\":\"roll\",\"player\":\""+PLAYERACTING+"\",\"moveNumber\":\""+MOVENUMBER+"\",\"dice\":{\"value1\":"+DIE1VALUE+",\"value2\":"+DIE2VALUE+"},\"move\":\""+CHECKERPLAY+"\"},";
	// Note that the final entry in an action array will have n trailing comma
	String CUBEITEM = "	{\"type\":\"cube\",\"player\":\""+PLAYERACTING+"\",\"moveNumber\":"+MOVENUMBER+",\"cubeValue\":"+CUBEVALUE+",\"move\":\""+CUBEACTIONRESULT+"\"},";

	String JSONGame = "{\n" +
	"   \"player1\":\"PLAYER1NAME\",\n" +
	"   \"player2\":\"PLAYER2NAME\",\n" +
	"   \"gameDate\":\"GAMEDATE\",\n" +
	"   \"comment\":\"GAMECOMMENT\",\n" +
	"   \"sequence\":{\"title\":\"SEQTITLE\",\"number\":GAMENUMBER,\"outof\":TOTALGAMES},\n" +
	"   \"postcomment\":\"POSTCOMMENT\",\n" +
	"   \"currentcube\":{\"player\":\"none\",\"value\":0},\n" +
	"   \"MatchScore\":{\"player1\":PLAYER1SCORE,\"player2\":PLAYER2SCORE},\n" +
	"   \"MatchLength\":\"MATCHLENGTH\",\n" +
	"   \"actions\":[\n" +
	"	 \n" +
	"   GAMEACTIONARRAY\n" +
	"    \n" +
	"	],\n" +
	"   \"player1Checkers\":[\n" +
	"      {\n" +
	"         \"point\":6,\n" +
	"         \"value\":5\n" +
	"      },\n" +
	"      {\n" +
	"         \"point\":8,\n" +
	"         \"value\":3\n" +
	"      },\n" +
	"      {\n" +
	"         \"point\":13,\n" +
	"         \"value\":5\n" +
	"      },\n" +
	"      {\n" +
	"         \"point\":24,\n" +
	"         \"value\":2\n" +
	"      }\n" +
	"   ],\n" +
	"   \"player2Checkers\":[\n" +
	"      {\n" +
	"         \"point\":6,\n" +
	"         \"value\":5\n" +
	"      },\n" +
	"      {\n" +
	"         \"point\":8,\n" +
	"         \"value\":3\n" +
	"      },\n" +
	"      {\n" +
	"         \"point\":13,\n" +
	"         \"value\":5\n" +
	"      },\n" +
	"      {\n" +
	"         \"point\":24,\n" +
	"         \"value\":2\n" +
	"      }\n" +
	"   ]\n" +
	"}";
	
	String DIRECTORYJson = "{\n" +
	"	\"maxElements\": MAXELEMENTS,\n" +
	"	\"directoryDescription\": \"MATCHDESC\",\n" +
	"	\"isCategoryAllowed\": false,\n" +
	"	\"isBgTestAllowed\": false,\n" +
	"	\"minElements\": 1\n" +
	"}";
	
Character [] pos = new Character[26];
//Character [] pos2 = new Character[26];
for (int i = 0; i < 26; i++)
{
	pos[i] = "-".charAt(0);
	//pos2[i] = "-".charAt(0);
}
//C:\Users\DennisMcFarlin\Documents\eXtremeGammon\Exports
//String textMatch = usingBufferedReader("C:\\Users\\DennisMcFarlin\\Documents\\eXtremeGammon\\dm-jf-7-31-2019.txt");
String textMatch = readFile(inputDir+inputFile);
//System.out.println(textMatch);

String arry [] = textMatch.split("\n");
//System.out.println(arry.length);

TOTALGAMES = textMatch.substring(textMatch.lastIndexOf(" Game "),textMatch.lastIndexOf(" Game ")+9).trim().split(" ")[1];
String gameDir = saveDirRoot+"/"+inputFile.substring(0,inputFile.lastIndexOf(".")).replace(" ", "")+"/";
new File(gameDir).mkdirs();
//System.out.println("-->" + TOTALGAMES);
for (int i =0; i < arry.length; i++)
{
	String item = arry[i];
	
	// Note that XG Reverses the Player1 and Player2 roles putting player2 in column1 and vice versa.
	// So we will adjust the name here
	if (item.contains("; [Player 2 \""))
	{
		PLAYER1NAME = item.split("\"")[1]; //Note reversal for XG
	}
	else if (item.contains("; [Player 1 \""))
	{
		PLAYER2NAME = item.split("\"")[1]; //Note reversal for XG
	}
	else if (item.contains(" point match"))
	{
		MATCHLENGTH = item.trim().split(" ")[0];
	}
	else if (item.contains("; [EventDate "))
	{
		GAMEDATE = item.split("\"")[1];
	}
	else if (item.contains("; [Event \""))
	{
		MATCHEVENT = item.split("\"")[1];
	}
	else if (item.startsWith(" Game "))
	{ 	
		GAMENUMBER = item.split(" ")[2];
		String thisGameToEndOfMatch = textMatch.substring(textMatch.indexOf(" Game "+GAMENUMBER));
		thisGameToEndOfMatch = thisGameToEndOfMatch.substring(0,thisGameToEndOfMatch.indexOf(" Wins ")+12);
		
		//System.out.println(thisGameToEndOfMatch);
		//System.out.println(thisGameToEndOfMatch.lastIndexOf("\n"));
		String winnerLine = thisGameToEndOfMatch.substring(thisGameToEndOfMatch.lastIndexOf("\n")+1);
		
		//System.out.println("DJM -"+winnerLine);
		if (winnerLine.indexOf("Wins") > 10)
		{
			String winValue = winnerLine.trim().split(" ")[1];
			POSTCOMMENT = PLAYER2NAME +" "+winValue+" points.";
		}
		else if (winnerLine.indexOf("Wins") < 10)
		{
			String winValue = winnerLine.trim().split(" ")[1];
			POSTCOMMENT = PLAYER1NAME +" "+winValue+" points.";
		}
		
		PLAYER1SCORE = arry[i+1].split(":")[1].split(" ")[1]; //replace(" ","");
		PLAYER2SCORE = arry[i+1].split(":")[2].replace(" ","");
		GAMECOMMENT = "Game "+GAMENUMBER+" : "+PLAYER1NAME+" ("+PLAYER1SCORE+") "+PLAYER2NAME+" ("+PLAYER2SCORE+")";
		ACTIONARRAY = getActionArray(arry,i+2);
		String game = JSONGame.replace("GAMEACTIONARRAY",ACTIONARRAY).replace("PLAYER1NAME",PLAYER1NAME).replace("PLAYER2NAME", PLAYER2NAME).replace("GAMEDATE",GAMEDATE).replace("SEQTITLE", "game").replace("GAMENUMBER", GAMENUMBER).replace("GAMECOMMENT", GAMECOMMENT).replace("POSTCOMMENT", POSTCOMMENT).replace("TOTALGAMES",TOTALGAMES).replace("MATCHLENGTH", MATCHLENGTH).replace("PLAYER1SCORE", PLAYER1SCORE).replace("PLAYER2SCORE", PLAYER2SCORE).replace("POSTCOMMENT", POSTCOMMENT);
		writeToFile(game, gameDir+SEQTITLE+GAMENUMBER+".json");
	}
}

writeToFile(DIRECTORYJson.replace("MAXELEMENTS", TOTALGAMES).replace("MATCHDESC",MATCHEVENT + " "+inputFile), gameDir+"directory.json");
/*
; [Site "Las Vegas Backgammon Club"]
; [Match ID "-597769588"]
; [Event "Weekly Tournament"]
; [Round "Semi Final"]
; [Player 1 "Jim Fleming"]
; [Player 2 "Dennis McFarlin"]
; [Player 1 Elo "1600.00/0"]
; [Player 2 Elo "1600.00/0"]
; [EventDate "2019.07.30"]
; [EventTime "23.00"]
; [Variation "Backgammon"]
; [Unrated "Off"]
; [Crawford "On"]
; [CubeLimit "1024"]
; [Transcriber "DJM"]
*/

 

}


public static void writeToFile(String text,String path) 
{                
    try 
    {   
    	//System.out.println(path);
    	//if (1 == 1) return;
        BufferedWriter bw = new BufferedWriter
        	    (new OutputStreamWriter(new FileOutputStream(new File(path),false), StandardCharsets.UTF_8));
        bw.write(text);                        
        bw.newLine();                        
        bw.close();                
    } 
    catch (Exception e) 
    {      }
}  

	private static int countOccurences(
		  String someString, char searchedChar, int index) {
		    if (index >= someString.length()) {
		        return 0;
		    }
		     
		    int count = someString.charAt(index) == searchedChar ? 1 : 0;
		    return count + countOccurences(
		      someString, searchedChar, index + 1);
	}

	private static String getActionArray(String [] arry, int i)
	{
		//String PLAYER1SCORE = "";
		//String PLAYER2SCORE = "";
		
		String PLAYERACTING = "";
		int MOVENUMBER=0;
		String DIE1VALUE = "";
		String DIE2VALUE="";
		String CHECKERPLAY = "";
		String CUBEVALUE="";
		String CUBEACTIONRESULT="";
		
		String ACTIONARRAY = "";
		// The action array is composed of rollitems and cubeitems
		String ROLLITEM = "	{\"type\":\"roll\",\"player\":\"PLAYERACTING\",\"moveNumber\":\"MOVENUMBER\",\"dice\":{\"value1\":DIE1VALUE,\"value2\":DIE2VALUE},\"move\":\"CHECKERPLAY\"},";
		// Note that the final entry in an action array will have n trailing comma
		String CUBEITEM = "	{\"type\":\"cube\",\"player\":\"PLAYERACTING\",\"moveNumber\":\"MOVENUMBER\",\"cubeValue\":CUBEVALUE,\"move\":\"CUBEACTIONRESULT\"},";

		
		int actCnt =0;
		boolean gameWon = false;
		while ((i < arry.length) && !(arry[i].contains("Wins")) && !gameWon)
		{			
			String item = arry[i];
			if (item.contains(")"))
			{
				int colonCnt = countOccurences(item, ':', 0);
				if (colonCnt == 2)
				{
					//We have two roll/moves on this line
					//Get player1 dice
					//System.out.println(item);
					int colPos1 = item.indexOf(":");
					String p1die1=item.substring(colPos1-2,colPos1-1);
					String p1die2=item.substring(colPos1-1,colPos1);
					//System.out.println(p1die1 +"  and " +p1die2);
					int colPos2 = item.lastIndexOf(":");
					String p2die1=item.substring(colPos2-2,colPos2-1);
					String p2die2=item.substring(colPos2-1,colPos2);
					String checkPlay1 = item.substring(colPos1+1,colPos1+31).trim().replace(" ",";");					
					String checkPlay2 = item.substring(colPos2+1).trim().replace(" ",";");
					if (checkPlay1.contains("Cannot"))
						checkPlay1 = "Dances";
					if (checkPlay2.contains("Cannot"))
						checkPlay2 = "Dances";
					//System.out.println(checkPlay1 +"  and " +checkPlay2);
					actCnt++;
					ACTIONARRAY+=ROLLITEM.replace("PLAYERACTING", "player1").replace("MOVENUMBER", Integer.toString(actCnt)).replace("DIE1VALUE", p1die1).replace("DIE2VALUE", p1die2).replace("CHECKERPLAY", checkPlay1);
					ACTIONARRAY+="\n";
					actCnt++;
					ACTIONARRAY+=ROLLITEM.replace("PLAYERACTING", "player2").replace("MOVENUMBER", Integer.toString(actCnt)).replace("DIE1VALUE", p2die1).replace("DIE2VALUE", p2die2).replace("CHECKERPLAY", checkPlay2);
					ACTIONARRAY+="\n";
					
				}
				else if (colonCnt == 1)
				{
					//System.out.println("colCnt: "+colonCnt);
					//		a) One roll/move on this line by player2
					//           i) a take by player1 and a roll
					//			ii) An game starting roll by player2 
					//		b) A final roll/move on this line by player1 that won.
					//		c) A roll by player1 followed by a double by player2
					if (item.contains("Doubles"))
					{
						//p1 has rolled
						//p2 has doubled
						// doubled to what?
						// look ahead to see the take
						//System.out.println(item);
						//System.out.println("Double: ");
						int colPos1 = item.indexOf(":");
						String p1die1=item.substring(colPos1-2,colPos1-1);
						String p1die2=item.substring(colPos1-1,colPos1);
						String checkPlay1 = item.substring(colPos1+1,colPos1+12).trim().replace(" ",";");
						if (checkPlay1.contains("Cannot"))
							checkPlay1 = "Dances";
						actCnt++;
						ACTIONARRAY+=ROLLITEM.replace("PLAYERACTING", "player1").replace("MOVENUMBER", Integer.toString(actCnt)).replace("DIE1VALUE", p1die1).replace("DIE2VALUE", p1die2).replace("CHECKERPLAY", checkPlay1);
						ACTIONARRAY+="\n";
						String doubleValue = item.substring(item.indexOf(">")+1).trim();
						//System.out.println(doubleValue);
						//Have to read ahead to get the result
						String DOUBLEResult = "Take";
						if (arry[i+1].contains("Drop"))
						{
							DOUBLEResult = "Pass";
							gameWon=true;
						}
						actCnt++;
						
						//System.out.println("Arr: "+CUBEITEM.replace("PLAYERACTING", "player2").replace("MOVENUMBER", Integer.toString(actCnt)).replace("CUBEVALUE", doubleValue).replace("CUBEACTIONRESULT", DOUBLEResult));
						ACTIONARRAY+=CUBEITEM.replace("PLAYERACTING", "player2").replace("MOVENUMBER", Integer.toString(actCnt)).replace("CUBEVALUE", doubleValue).replace("CUBEACTIONRESULT", DOUBLEResult);
						ACTIONARRAY+="\n";			
					}
					if (!gameWon)
					{
						// Some of this is accounted for in above
						// if player2 DROPS 
						if (item.contains("Wins"))
						{
							//System.out.println("Wins: ");
							//Player 1 wins by virtue of a drop
							gameWon=true;
							//int colPos1 = item.indexOf(":");
							//String p1die1=item.substring(colPos1-2,1);
							//String p1die2=item.substring(colPos1-1,1);
							int colPos1 = item.indexOf(":");
							String p1die1=item.substring(colPos1-2,colPos1-1);
							String p1die2=item.substring(colPos1-1,colPos1);
							String checkPlay1 = item.substring(colPos1+1,30).trim().replace(" ",";");
							actCnt++;
							ACTIONARRAY+=ROLLITEM.replace("PLAYERACTING", "player1").replace("MOVENUMBER", Integer.toString(actCnt)).replace("DIE1VALUE", p1die1).replace("DIE2VALUE", p1die2).replace("CHECKERPLAY", checkPlay1);
							ACTIONARRAY+="\n";
						}
						else
						{
							// a) player2 is starting
							// b) player1 finishes bearoff
							
							int colPos = item.indexOf(":");
							if (colPos > 20)
							{
								//System.out.println("P2 Starts: ");
								String p2die1=item.substring(colPos-2,colPos-1);
								String p2die2=item.substring(colPos-1,colPos);
								String checkPlay2 = item.substring(colPos+1).trim().replace(" ",";");
								actCnt++;
								ACTIONARRAY+=ROLLITEM.replace("PLAYERACTING", "player2").replace("MOVENUMBER", Integer.toString(actCnt)).replace("DIE1VALUE", p2die1).replace("DIE2VALUE", p2die2).replace("CHECKERPLAY", checkPlay2);
								//System.out.println("--> "+ROLLITEM.replace("PLAYERACTING", "player2").replace("MOVENUMBER", Integer.toString(actCnt)).replace("DIE1VALUE", p2die1).replace("DIE2VALUE", p2die2).replace("CHECKERPLAY", checkPlay2));
								ACTIONARRAY+="\n";
							}
							else
							{
								//System.out.println("P1 finished bearoff");
								String p1die1=item.substring(colPos-2,colPos-1);
								String p1die2=item.substring(colPos-1,colPos);
								String checkPlay1 = item.substring(colPos+1).trim().replace(" ",";");
								actCnt++;
								ACTIONARRAY+=ROLLITEM.replace("PLAYERACTING", "player1").replace("MOVENUMBER", Integer.toString(actCnt)).replace("DIE1VALUE", p1die1).replace("DIE2VALUE", p1die2).replace("CHECKERPLAY", checkPlay1);
								ACTIONARRAY+="\n";
							}
						}
					}
				}
				else if (colonCnt == 0)
				{
					// 		a) Double by player1 and take action by player2
					//		b) Take action by player1 where dropped and player2 wins (already accounted for in colCnt=1)
					// p1 doubles to what
					// does p2 take
					String doubleValue = item.substring(item.indexOf(">")+2, item.indexOf(">")+6).trim();
					//Have to read ahead to get the result
					String DOUBLEResult = "Pass";
					if (item.contains("Takes"))
					{
						DOUBLEResult = "Take";
					}
					actCnt++;
					ACTIONARRAY+=CUBEITEM.replace("PLAYERACTING", "player1").replace("MOVENUMBER", Integer.toString(actCnt)).replace("CUBEVALUE", doubleValue).replace("CUBEACTIONRESULT", DOUBLEResult);
					ACTIONARRAY+="\n";
				}	
				i++;
			}
			
			// Might not even care about the win
			//if (!gameWon && item.contains("Wins"))
			//{
			//	if (indexOf("Wins") > 5)
			//}
			}
			ACTIONARRAY = ACTIONARRAY.substring(0,ACTIONARRAY.length() - 2);
			//System.out.println(ACTIONARRAY);
		return ACTIONARRAY;
	}

}
