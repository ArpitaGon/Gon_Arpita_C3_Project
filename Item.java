public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice(){  //return cost per item
        return this.price;
    }

    public String toString(){
        return  this.name + ":" + this.price + "\n" ;
    }
}
