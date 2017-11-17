package jsongsonmenu;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonGsonMenuTest {

    public static void main(String[] args) {
        System.out.println("-------以下做序列化的動作，意思是做出Json字串--------");
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

        MealData md = new MealData(34,"火腿漢堡",60,"好吃的漢堡","hamburg","英數組合字串的圖片");
        ClientInsertMealRequest m = new ClientInsertMealRequest(2,"insert",md);
        String jsonSerialization = gson.toJson(m);//序列化，製作一個Json字串
        System.out.println(jsonSerialization+"\n");//印出序列化的Json字串
        
        System.out.println("-------以下做反序列化，反序列化意思為取出Json中的值--------");
        ClientInsertMealRequest enums = gson.fromJson(jsonSerialization, ClientInsertMealRequest.class);//jsonSerialization為原始的json字串，此行把json字串放進物件之中
        
        String thisMealName = enums.getMealData().getMealName();//把物件中json的值取出來，先透過第一層再取第二層
        int thisMealPrice = enums.getMealData().getMealPrice();
        String thisMealDescription = enums.getMealData().getMealDescription();
        String thisMealCategory = enums.getMealData().getMealCategory();
        String thisMealImage = enums.getMealData().getMealImage();
        
        System.out.println(thisMealName);//印出
        System.out.println(thisMealPrice);
        System.out.println(thisMealDescription);
        System.out.println(thisMealCategory);
        System.out.println(thisMealImage);
        
        System.out.print("\n");
        System.out.println("-------以下為陣列的反序列化--------");
        
        String serverSideResponseMealListJson = "[\n" +
"    {\n" +
"        \"mealNumber\":45,\n" +
"        \"mealName\":\"火腿漢堡\",\n" +
"        \"mealPrice\":55,\n" +
"        \"mealDescription\":\"好吃的漢堡\",\n" +
"        \"mealCategory\":\"hamburg\",\n" +
"        \"mealImage\":\"英數組合字串\"\n" +
"    },\n" +
"    {\n" +
"        \"mealNumber\":33,\n" +
"        \"mealName\":\"鮪魚三明治\",\n" +
"        \"mealPrice\":30,\n" +
"        \"mealDescription\":\"好吃三明治\",\n" +
"        \"mealCategory\":\"sandwich\",\n" +
"        \"mealImage\":\"英數組合字串\"\n" +
"    },\n" +
"    {\n" +
"        \"mealNumber\":88,\n" +
"        \"mealName\":\"起司蛋餅\",\n" +
"        \"mealPrice\":35,\n" +
"        \"mealDescription\":\"好吃的蛋餅\",\n" +
"        \"mealCategory\":\"omelet\",\n" +
"        \"mealImage\":\"英數組合字串\"\n" +
"    }\n" +
"]";

        ServerSideResponseMenuList[] enums2 = gson.fromJson(serverSideResponseMealListJson, ServerSideResponseMenuList[].class);
        for(ServerSideResponseMenuList b : enums2){
            System.out.println(b.getMealName());
            System.out.println(b.getMealPrice());
            System.out.println(b.getMealDescription());
            System.out.println(b.getMealCategory());
            System.out.println(b.getMealImage()+"\n");
        }
    }
}
