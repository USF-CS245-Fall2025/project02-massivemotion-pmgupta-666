public class ListFactory {
    public static <T> List<T> make(String type) {
        switch (type) {
            case "arraylist": return new ArrayList<>();
            case "single": return new SinglyLinkedList<>();
            case "double": return new DoublyLinkedList<>();
            case "dummyhead": return new DummyHeadLinkedList<>();
            default: return new ArrayList<>();
        }
    }
}
