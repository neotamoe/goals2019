package goals2019

class TodoItem {

    Long id
    String task
    Date createdOn
    Date updatedOn
    Boolean isCompleted

    static constraints = {
        task blank: false, maxSize: 255
        createdOn nullable: true
        updatedOn nullable: true
        isCompleted nullable: true
    }
}
