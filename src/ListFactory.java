public class ListFactory {
    public static <T> List<T> make(String kind) {
        if (kind == null) kind = "arraylist";
        switch (kind.toLowerCase()) {
            case "single":    return new SinglyLinkedList<>();
            case "double":    return new DoublyLinkedList<>();
            case "dummyhead": return new DummyHeadLinkedList<>();
            case "arraylist":
            default:          return new ArrayList<>();
        }
    }
}
