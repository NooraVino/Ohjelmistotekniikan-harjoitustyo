
package nooran.giftwish.domain;

/**
 *
 * Lahjatoivetta kuvaava luokka.
 */
public class Gift {
    private int id;
    private String name;
    private String content;
    private boolean done;
    private User user;
    
    public Gift(int id, String name, String content, boolean done, User user) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.done = done;
        this.user = user;
    }
    
    public Gift(String name, String content, User user) {
        this.name = name;
        this.content = content;
        this.done = false;
        this.user = user;   
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDone() {
        this.done = true;
    }
    
    public String getContent() {
        return this.content;
    }

    public User getUser() {
        return this.user;
    }

    public int getId() {
        return this.id;
    }

    public boolean isDone() {
        return this.done;
    }

    public void writeContent(String content) {
        this.content = content;
    }

    public String getName(String name) {
        return this.name;
    }

    public String getContent(String content) {
        return this.content;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Gift)) {
            return false;
        }
        Gift other = (Gift) obj;
        return id == other.id;
    }

    
}
