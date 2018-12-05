
package nooran.giftwish.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import nooran.giftwish.domain.Gift;
import nooran.giftwish.domain.User;

/**
 *
 * @author vino
 */
public class FileGiftDao implements GiftDao {

    private List<Gift> gifts;
    private String file;

    public FileGiftDao(String file, UserDao users) throws Exception {
        this.gifts = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);
                User user = users.getAll().stream().filter(u -> u.getUserName().equals(parts[4])).findFirst().orElse(null);
                Gift gift = new Gift(id, parts[1], parts[2], done, user);
                gifts.add(gift);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Gift gift : gifts) {
                writer.write(gift.getId() + ";" + gift.getName() + ";"+ gift.getContent() + ";" + gift.isDone() + ";" + gift.getUser().getUserName() + "\n");
            }
        }
    }

    private int generateId() {
        return gifts.size() + 1;
    }

    @Override
    public List<Gift> getAll() {
        return gifts;
    }

    @Override
    public Gift create(Gift gift) throws Exception {
        gift.setId(generateId());
        gifts.add(gift);
        save();
        return gift;
    }

    @Override
    public void setDone(int id) throws Exception {
        for (Gift g : gifts) {
            if (g.getId() == id) {
                g.setDone();
            }
        }
        save();
    }

}
