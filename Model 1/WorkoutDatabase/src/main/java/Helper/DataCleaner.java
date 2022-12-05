package Helper;

public class DataCleaner {
    public static String cleanData(String input, int key){
        //If not the info column
        if(key != 9 ){
            //If the length is zero
            if(input.length() == 0){
                return "";
            }

            if(key == 1)//for date, keep the slashes
                input = input.replaceAll("[^a-zA-Z0-9/]", "");//Removes all non alphanumeric values
            else
                input = input.replaceAll("[^a-zA-Z0-9]", "");//Removes all non alphanumeric values
            input = input.toLowerCase();//Everything is lower case
        }

        return input;
    }

    public static String validateData(String input, int key){
            //If the value this string is not there, then return an error character
            if(key != 9 && input.length() < 1){
                return "@";
            }


            if((key == 0 || key == 5) || (key == 6 || key == 7)){//For only numbered characters
                //If not a number, error
                if(input.matches("\\D")){
                    return "@";
                }
            }

            //TODO add more validation conditions

            return input;
    }
}
