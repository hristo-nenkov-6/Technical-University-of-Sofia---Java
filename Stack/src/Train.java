import java.util.Stack;

public class Train {
    private Stack<String> wagons;

    public Train() {
        wagons = new Stack<>();
    }

    public void push(String wagon) {
        wagons.push(wagon);
    }

    public String pop() {
        if(wagons.isEmpty()) {
            return null;
        }

        return wagons.pop();
    }

    public String peek() {
        if(wagons.isEmpty()) {
            return null;
        }

        return wagons.peek();
    }
}
