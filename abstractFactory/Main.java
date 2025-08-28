package abstractFactory;

public class Main {
    public static void main(String[] args) {
        // Pick a style (A or B)
        UIFactory factory = new AFactory(); // change to new BFactory() for Style B

        Button button = factory.createButton("Click Me");
        TextField textField = factory.createTextField("Enter Name");
        Checkbox checkbox = factory.createCheckbox("I agree");

        // Display elements
        button.display();
        textField.display();
        checkbox.display();

        // Update dynamically
        button.setText("Submit");
        textField.setText("Updated Text");
        checkbox.setText("Accept Terms");

        System.out.println("\nAfter updating text:\n");
        button.display();
        textField.display();
        checkbox.display();
    }
}
