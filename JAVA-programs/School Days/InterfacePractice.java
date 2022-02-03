interface Printable {
    // public abtract method
    void print(String mssg);
}

interface Greetable {
    // static and default methods can have a body
    static void sayHello() {
        System.out.println("Hello user !");
    }
    default void sayBye() {
        System.out.println("Bye user !");
    }
}

interface Shape {
    // final data members
    int length = 12;
    int breadth = 24;
    int height = 33;
}

interface Calculate extends Shape {
    void volume();
}

class Child implements Printable, Greetable, Calculate {
    // public must be specified since methods are public by default in interfaces
    public void print(String mssg) {
        System.out.println(mssg);
    }

    public void volume() {
        System.out.println("Length = " + length + ", Breadth = " + breadth + ", Height = " + height);
        System.out.println("Volume = " + (length*breadth*height));
    }
}

class InterfacePractice {
    public static void main(String[] args) {
        Child ob = new Child();
        ob.print("Namaskaram user !");
        Greetable.sayHello();
        ob.sayBye();
        ob.volume();
    }
}