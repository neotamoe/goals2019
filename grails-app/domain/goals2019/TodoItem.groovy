package goals2019

class TodoItem {

    Long id
    String task

    static constraints = {
        task blank: false, maxSize: 255

    }
}
