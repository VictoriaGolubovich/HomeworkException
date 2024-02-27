public class Main {
    public static void main(String[] args) {
        try {
            Application app = new Application();
            app.dataFormatting();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
