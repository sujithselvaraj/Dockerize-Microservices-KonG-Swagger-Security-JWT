package Sujith.EmployeeService.Entity;

public class Token {
    private  String Token;

    private  static  Token instance;
    private  Token(){

    }

    public  static  Token getInstance(){
        if(instance ==null){
            instance  = new Token();
        }
        return instance;
    }
    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
