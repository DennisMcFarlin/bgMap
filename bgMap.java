import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class bgMap {

	
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

Character [] pos = new Character[26];
//Character [] pos2 = new Character[26];
for (int i = 0; i < 26; i++)
{
	pos[i] = "-".charAt(0);
	//pos2[i] = "-".charAt(0);
}

String json = usingBufferedReader("C:\\dogstar\\vegasbg\\WIP\\lvpos3.json");
//Method 1: parsing into a JSON element
JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
JsonArray player1Checkers = jsonObject.getAsJsonArray("player1Checkers");

for(JsonElement pa : player1Checkers){
	int point = Integer.parseInt(pa.getAsJsonObject().get("point").toString());
	int value = Integer.parseInt(pa.getAsJsonObject().get("value").toString());
	if (value == 0 || point == 0)
		continue;
    //System.out.println("point: "+ point +"   value: "+value);
    //System.out.println(point +"  "+Character.toString((char) (64 + value)));
    pos[point] =(char) (64 + value);
    
}

JsonArray player2Checkers = jsonObject.getAsJsonArray("player2Checkers");

for(JsonElement pa : player2Checkers){
	int point = Integer.parseInt(pa.getAsJsonObject().get("point").toString());
	int value = Integer.parseInt(pa.getAsJsonObject().get("value").toString());
	if (value == 0 || point == 0)
		continue;
    //System.out.println("point: "+ (25 - point) +"   value: "+value);
    //System.out.println((25 - point) +"  "+Character.toString((char)(96 + value)));
    pos[25-point] = (char) (96 + value);
    //pos2[25-point] = (char) (96 + value);
}

String apos = "";
for (int i =0; i < pos.length; i++)
{
	apos += pos[i]; 
}

int cubeValueExp=0;

String cubeHolder = jsonObject.get("currentcube").getAsJsonObject().get("player").getAsString();
String cubeValue = jsonObject.get("currentcube").getAsJsonObject().get("value").getAsString();
int cubevalueExp =0;

if (cubeValue != null && !cubeValue.replace("\"", "").equals("0"))
{
	//System.out.println(Math.log(Integer.parseInt(cubeValue)));
	//System.out.println(Math.log(2));
	//System.out.println(Math.log(Integer.parseInt(cubeValue))/Math.log(2));
	
	cubeValueExp = (int) Math.rint(Math.log(Integer.parseInt(cubeValue))/Math.log(2));	
	if (cubeHolder != null && cubeHolder.endsWith("1"))
	{
		cubeValueExp = -1 * cubeValueExp;
	}
}

System.out.println("; [Site \""+jsonObject.get("comment").getAsString()+"\"]");
System.out.println("; [Match ID \""+System.currentTimeMillis()+"\"]");
System.out.println("; [Player 1 \""+jsonObject.get("player1").getAsString()+"\"]");
System.out.println("; [Player 2 \""+jsonObject.get("player2").getAsString()+"\"]");
System.out.println("; [Player 1 Elo \"1600.00\"]");
System.out.println("; [Player 2 Elo \"1600.00\"]");
//; [EventDate "2018.06.09"]
//; [EventTime "04.58"]
//; [Variation "Backgammon"]
//; [Unrated "Off"]
//; [Jacoby "On"]
//; [Beaver "On"]
//; [CubeLimit "1024"]
System.out.println();
System.out.println(jsonObject.get("MatchLength").getAsString() +" point match");
System.out.println();
System.out.println("Game 1");
System.out.println(("Player 1 : "+jsonObject.get("MatchScore").getAsJsonObject().get("player1")+"                    Player 2 : "+jsonObject.get("MatchScore").getAsJsonObject().get("player2")).replace("\"", ""));
System.out.println();

//System.out.println("pos=01234567890123456789012345");
System.out.println("; Set Pos="+apos+"/"+cubeValueExp);

/*
apos = "";

for (int i =0; i < pos.length; i++)
{
	apos += pos2[i]; 
}
*/
	
//System.out.println("pos=01234567890123456789012345");
//System.out.println("pos="+apos);

//for (int i =0; i < player1Checkers.size(); i++)
//{
//	JsonObject obj = (JsonObject) player1Checkers.get(i);
//	print(obj.getAsString("point"));
//	
//}
//System.out.println(jsonObject.get("MatchLength").getAsString());

//Method 2: parsing into a Java Object
//User user = new Gson().fromJson(json, User.class);
//System.out.println(user.id);


/*
for (int i = 65; i < 120; i++)
{
	System.out.println(i +"  "+Character.toString((char)i));
}
*/
}

}
