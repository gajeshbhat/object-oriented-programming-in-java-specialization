public class objectCreation{

public static class demoCls{

    public demoCls(){   //Constructer for demoCls
      System.out.println("Constructer Invoked!");
    }

     void sayHello(){
      System.out.println("Hello from demoCls");
    }

}

public static void main(String[] args) {

 demoCls demoObj = new demoCls();
 demoObj.sayHello();

}
}
