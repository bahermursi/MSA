
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.annotation.*;
 
public class CreateJson{
 
    public String create()throws JsonProcessingException {
 
    	ObjectMapper mapper = new ObjectMapper();
 
    	ObjectNode accessTable = mapper.createObjectNode();
        ArrayNode userArray = mapper.createArrayNode();
        
 
        /**
         * Create three JSON Objects objectNode1, objectNode2, objectNode3
         * Add all these three objects in the array
         */
        /*
 
        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("username", "souvikduttachoudhury32@gmail.com");
        objectNode1.put("password", "lister");
        objectNode1.put("role","SUPER_USER");
 
        ObjectNode objectNode2 = mapper.createObjectNode();
        objectNode2.put("username", "rishi.rk10@gmail.com");
        objectNode2.put("password", "malumnahi");
        objectNode2.put("role","IT_SUPPORT");
 
        ObjectNode objectNode3 = mapper.createObjectNode();
        objectNode3.put("username", "roushankumar.p@listertechnologies.com");
        objectNode3.put("password", "rubyonroad");
        objectNode3.put("role","EMAIL_SUPPORT");
        
        */
 
        /**
         * Array contains JSON Objects
         */
        
        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("createclient", "true");
        objectNode1.put("setstoragepath", "true");
        objectNode1.put("assignbcc","true");
        
        ObjectNode access1=mapper.createObjectNode();
        access1.put("role","SUPER_USER");
        access1.putPOJO("permission", objectNode1);
 
        ObjectNode objectNode2 = mapper.createObjectNode();
        objectNode2.put("createclient", "false");
        objectNode2.put("setstoragepath", "true");
        objectNode2.put("assignbcc","false");
        
        ObjectNode access2=mapper.createObjectNode();
        access2.put("role","IT_SUPPORT");
        access2.putPOJO("permission", objectNode2);
        
 
        ObjectNode objectNode3 = mapper.createObjectNode();
        objectNode3.put("createclient", "false");
        objectNode3.put("setstoragepath", "false");
        objectNode3.put("assignbcc","true");
        
        ObjectNode access3=mapper.createObjectNode();
        access3.put("role","EMAIL_SUPPORT");
        access3.putPOJO("permission", objectNode3);
        
        ArrayNode accessArray=mapper.createArrayNode();
        
        accessArray.add(access1);
        accessArray.add(access1);
        accessArray.add(access1);
        
        ObjectNode tokencount=mapper.createObjectNode();
        //tokencount.put("tokencount", 3);
        accessTable.put("tokencount", "3");
        accessTable.putPOJO("access", accessArray);
        
        ObjectNode content = mapper.createObjectNode();
        //content.putPOJO("UserRepository", userTable);
        
        
        /**
         * We can directly write the JSON in the console.
         * But it wont be pretty JSON String
         */
        //System.out.println(userTable.toString());
 
        /**
         * To make the JSON String pretty use the below code
         */
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accessTable);
 
    }
 
}

