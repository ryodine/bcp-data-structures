import java.util.ArrayList;

public class lol {
    public static void main (String[] args) {
        /*ArrayList<Pet> l = new ArrayList<Pet>();
        l.add(new LoudDog("Fido"));
        l.add(new Dog("little dog"));
        l.add(new Cat("a cat"));*/
        Dog d = new Dog();
        System.out.println("lelelelele:" + d.speak());
        Kennel k = new Kennel(l);
        k.allSpeak();
    }
}

abstract class Pet {
    private String myName;
    
    public Pet(String name) {
        myName = name;
    }
    
    public String getName() {
        return myName;
    }
    
    public abstract String speak();
}

class Dog extends Pet {
    public Dog(String name) {
        super(name);
    }
    
    public String speak() {
        return "dog-sound";
    }
}

class Cat extends Pet {
    public Cat(String name) {
        super(name);
    }
    
    public String speak() {
        return "meow";
    }
}

class LoudDog extends Dog {
    public LoudDog(String name){
        super(name);
    }
    
    public String speak() {
        return super.speak() + " " + super.speak();
    }
}

class Kennel {
    private ArrayList<Pet> petList;
    
    public void allSpeak() {
        for (Pet p : petList) {
            System.out.println(p.getName() + ", " + p.speak());
        }
    }
    
    public Kennel(ArrayList<Pet> list) {
        petList = list;
    }
}