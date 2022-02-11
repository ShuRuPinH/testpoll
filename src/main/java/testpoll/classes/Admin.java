package testpoll.classes;



import java.time.LocalDateTime;
import java.util.ArrayList;


public class Admin {
    ArrayList<Poll> poolList;
    ArrayList <User> users;
    private static volatile Admin instance;


    private Admin() {
        poolList = new ArrayList<Poll>();
    }

    public static Admin getInstance() {
        if (instance == null) {
            synchronized (Admin.class) {
                instance = new Admin();
            }
        }
        return instance;
    }

    public int add(Poll po) {

        poolList.add(po);
        return poolList.indexOf(po);
    }

    public ArrayList<Poll> getPoolList() {
        return poolList;
    }


    public boolean del(int id) {
        try {
            poolList.remove(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void change(Integer id, String title, LocalDateTime end, String description) {
        poolList.get(id).setTitle(title);
        poolList.get(id).setEnd(end);
        poolList.get(id).setDescription(description);

    }

    public Poll getPoll(int id){
        return poolList.get(id);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addU(User u) {
        users.add(u);

    }

    public User getU(int id){
            for (User u : users ){
                if (u.ID == id) return u;
            }
            return null;
    }

}
