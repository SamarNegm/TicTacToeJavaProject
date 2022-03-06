/*
    This class is used by the server logic to handle json objects and json arrays
    the class is abstract with static methods.
 */

package Shared;


import Models.Game;
import Models.Player;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Hossam Khalil
 */
public abstract class JSONHandeling {
    
    private static JSONObject jsonObj;
    private static JSONArray jsonArray;
    private static JSONParser parser ;
    
    public static JSONObject constructJSONResponse(JSONObject jsonObj, String type)
    {   
        //add the relavent fields
        jsonObj = addToJSONObject(jsonObj, "type", type);
        jsonObj = addToJSONObject(jsonObj, "responseStatus", true);
        jsonObj = addToJSONObject(jsonObj, "errorMsg", "");
        
        return jsonObj;
    }
    
    public static JSONObject errorToJSON(String type, String errorMsg)
    {
        //Construct the json object
        jsonObj = new JSONObject();
        
        //add the relavent fields
        jsonObj = addToJSONObject(jsonObj, "type", type);
        jsonObj = addToJSONObject(jsonObj, "responseStatus", false);
        jsonObj = addToJSONObject(jsonObj, "errorMsg", errorMsg);
        
        return jsonObj;
    }
    
    public static JSONObject addToJSONObject (JSONObject jsonObj , String key , Object value)
    {
        jsonObj.put(key,value);
        return jsonObj;
    }

    public static JSONObject parseStringToJSON(String jsonStr) throws ParseException
    {
        parser = new JSONParser();
        jsonObj = new JSONObject();
        
        jsonObj = (JSONObject)parser.parse(jsonStr);
        
        return jsonObj;
    }
    
    public static JSONObject playerToJSON(Player player)
    {   
        jsonObj = new JSONObject();

        //add the player attributes fields
        jsonObj = addToJSONObject(jsonObj,"username", player.getUsername());
        jsonObj = addToJSONObject(jsonObj,"status", player.getStatus().toString());
        jsonObj = addToJSONObject(jsonObj,"score",  player.getScore());
  

        return jsonObj;
    }

    public static JSONArray playerListToJSONArray(Vector <Player> playerList)
    {
        //Construct json array
        jsonArray = new JSONArray();
        
        playerList.forEach((playerInfo) -> {
            jsonArray.add(playerToJSON(playerInfo));
        });
        
        return jsonArray;
    }
    
    public static JSONObject gameToJSON(Game game)
    {   

        jsonObj = new JSONObject();
        
        //create  game board
        String gameBoardStr = game.getState();
        
        JSONArray boardJSONArray = new JSONArray();
        
      

        //add the player attributes fields

        jsonObj = addToJSONObject(jsonObj,"nextMove", game.getTurn().toString());
        
        jsonObj = addToJSONObject(jsonObj,"PlayerX", game.getPlayerX());
        jsonObj = addToJSONObject(jsonObj,"PlayerO", game.getPlayerO()); 

        jsonObj = addToJSONObject(jsonObj,"gameboard",boardJSONArray);

        
        return jsonObj;
    }
    
    public static JSONObject gameSelectedToJSON(Game game , String username)
    {   
        jsonObj = new JSONObject();


        
        if (username.equals(game.getPlayerX()))
        {
            jsonObj = addToJSONObject(jsonObj,"player", game.getPlayerO());
        }
        
        else
        {
            jsonObj = addToJSONObject(jsonObj,"player", game.getPlayerX());
        }

        return jsonObj;
    }
    
    public static JSONArray gameListToJSONArray(Vector <Game> gameList , String username)
    {
        //Construct json array
        jsonArray = new JSONArray();
       
        gameList.forEach((gameInfo) -> {
            jsonArray.add(gameSelectedToJSON(gameInfo,username));
        });
        
        return jsonArray;
    }  
}
