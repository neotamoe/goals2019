package goals2019

class TodoItem {

    Long id
    String task
    Date createdOn
    Date updatedOn

    static constraints = {
        task blank: false, maxSize: 255
    }
}
