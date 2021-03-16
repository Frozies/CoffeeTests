public class Main {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println(tea.cream);
        System.out.println(tea.ingredients);
        tea.expiration = true;
        System.out.println(tea.checkExpired());
    }
}
