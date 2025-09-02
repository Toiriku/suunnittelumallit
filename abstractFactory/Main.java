package abstractFactory;

public class Main {
    public static void main(String[] args) {
        UIFactory factory = new AFactory();

        Button button = factory.createButton("Click Me");
        TextField textField = factory.createTextField("Enter Name");
        Checkbox checkbox = factory.createCheckbox("I agree");

        button.display();
        textField.display();
        checkbox.display();

        button.setText("Submit");
        textField.setText("Updated Text");
        checkbox.setText("Accept Terms");

        System.out.println("\nAfter updating text:\n");
        button.display();
        textField.display();
        checkbox.display();
    }
}
